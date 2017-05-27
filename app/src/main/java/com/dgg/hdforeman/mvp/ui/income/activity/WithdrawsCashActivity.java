package com.dgg.hdforeman.mvp.ui.income.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerWithdrawsCashComponent;
import com.dgg.hdforeman.di.module.WithdrawsCashModule;
import com.dgg.hdforeman.mvp.contract.income.WithdrawsCashContract;
import com.dgg.hdforeman.mvp.model.been.User;
import com.dgg.hdforeman.mvp.presenter.income.WithdrawsCashPresenter;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jess.arms.utils.UiUtils;
import com.zhy.autolayout.AutoFrameLayout;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Func1;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class WithdrawsCashActivity extends BacksActivity<WithdrawsCashPresenter> implements WithdrawsCashContract.View {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more_btn)
    ImageButton moreBtn;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.title_lay)
    AutoFrameLayout titleLay;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.bank_img)
    ImageView bankImg;
    @BindView(R.id.bank_name)
    TextView bankName;
    @BindView(R.id.bank_num)
    TextView bankNum;
    @BindView(R.id.project_item)
    TextView projectItem;
    @BindView(R.id.cash)
    EditText cash;
    @BindView(R.id.can_use)
    TextView canUse;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.cash_tv)
    TextView cashTv;

    private String allMoney;
    private WaitingDialog mWaitingDialog;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.withdraws_cash_activity, null, false);
    }

    @Override
    protected void initData() {
        title.setText("提现");
        mWaitingDialog=new WaitingDialog(this);
        mWaitingDialog.setCanceledOnTouchOutside(false);
        mWaitingDialog.setCancelable(false);
        allMoney=getIntent().getStringExtra("allMoney");
        canUse.setText("¥"+ CommonUtil.formatMoney(allMoney,2));
        mPresenter.initUserDataToView();
        confirm.setEnabled(false);
        cash.addTextChangedListener(watcher);
    }

    private TextWatcher watcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Editable editable=cash.getText();
            String withDrawsCashMoney=editable.toString();
            if(!withDrawsCashMoney.isEmpty() && !withDrawsCashMoney.substring(0,1).equals(".") && Double.parseDouble(withDrawsCashMoney)>Double.parseDouble(allMoney)){
                cashTv.setVisibility(View.GONE);
                canUse.setText("输入金额超过可提现余额");
            }else{
                cashTv.setVisibility(View.VISIBLE);
                canUse.setText("¥"+ CommonUtil.formatMoney(allMoney,2));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            String tmp=s.toString().trim();
            if(tmp.length()==0){
                confirm.setEnabled(false);
                confirm.setBackgroundColor(getResources().getColor(R.color.textGray));
            }else{
                confirm.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                confirm.setEnabled(true);
                if(tmp.contains(".")){
                    if(tmp.length()-tmp.indexOf(".")>3){
                        tmp = tmp.substring(0, tmp.length()-1);
                        cash.setText(tmp);
                        cash.setSelection(tmp.length());
                    }
                }
            }
        }
    };


    @OnClick({R.id.confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm://提现
                String withDrawsCashMoney=cash.getText().toString().trim();
                if(allMoney.isEmpty() || allMoney.equals("0")){
                    showMessage("无可提现金额");
                    return;
                }

                if (TextUtils.isEmpty(withDrawsCashMoney)){
                    showMessage("请输入金额");
                    return;
                }

                if(withDrawsCashMoney.equals(".")){
                    showMessage("请输入正确的金额");
                    return;
                }
                if(Double.parseDouble(withDrawsCashMoney)<0.01){
                    showMessage("提现金额不得少于一分钱");
                    return;
                }
                if(Double.parseDouble(withDrawsCashMoney)>Double.parseDouble(allMoney)){
                    showMessage("提现金额不能大于可提现金额");
                    return;
                }
                mPresenter.withdraw(withDrawsCashMoney);
                break;
        }
    }

    @Override
    public void initBankDataToView(User user) {
        bankName.setText(user.getBank());
        Observable.just(user.getBankno())
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return "****"+s.substring(s.length()-4);
            }
        }).subscribe(RxTextView.text(bankNum));
    }

    @Override
    public void updateCanUseMoney(String canUseMoney) {
        if(Double.parseDouble(allMoney)>0){
            cash.setText("");
            allMoney=String.valueOf(Double.parseDouble(allMoney)-Double.parseDouble(canUseMoney));
            String tempMoney=CommonUtil.formatMoney(String.valueOf(Double.parseDouble(allMoney)),2);
            canUse.setText("¥"+tempMoney );
        }
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerWithdrawsCashComponent
                .builder()
                .appComponent(appComponent)
                .withdrawsCashModule(new WithdrawsCashModule(this))//请将WaitForPaymentModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {
        checkNotNull(message);
        UiUtils.makeText(message);
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {
        finish();
    }
}
