package com.dgg.hdforeman.mvp.ui.income.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.TimeUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerMoneyArrivedDetailComponent;
import com.dgg.hdforeman.di.module.MoneyArrivedDetailModule;
import com.dgg.hdforeman.mvp.contract.income.MoneyArrivedDetailContract;
import com.dgg.hdforeman.mvp.model.been.IncomeWaitForPayment;
import com.dgg.hdforeman.mvp.model.been.MoneyArrivedDetail;
import com.dgg.hdforeman.mvp.presenter.income.MoneyArrivedDetailPresenter;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.jess.arms.utils.UiUtils;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;

import static com.dgg.hdforeman.app.utils.TimeUtil.YEAR_MONTH_DAY;
import static com.jess.arms.utils.Preconditions.checkNotNull;


public class MoneyArrivedDetailActivity extends BacksActivity<MoneyArrivedDetailPresenter> implements MoneyArrivedDetailContract.View{

    public static final String MONEYARRIVEDDETAIL="money_arrived_detail";
    public static final String WAITFORPAYMENT = "wait_for_payment";

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more_btn)
    ImageButton moreBtn;
    @BindView(R.id.title_lay)
    AutoFrameLayout titleLay;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.neighbourhoods)
    TextView neighbourhoods;
    @BindView(R.id.floor)
    TextView floor;
    @BindView(R.id.time_lab)
    TextView timeLab;
    @BindView(R.id.will_pay_time)
    TextView willPayTime;
    @BindView(R.id.right_arrow)
    TextView rightArrow;
    @BindView(R.id.will_time_layout)
    AutoLinearLayout willTimeLayout;
    @BindView(R.id.project_name)
    TextView projectName;
    @BindView(R.id.owner)
    TextView owner;
    @BindView(R.id.received_money)
    TextView receivedMoney;
    @BindView(R.id.project_item)
    TextView projectItem;
    @BindView(R.id.item_money)
    TextView itemMoney;
    @BindView(R.id.settlement_time)
    TextView settlementTime;
    @BindView(R.id.lab_kuanxiang)
    TextView labKuanxiang;
private  int state;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.money_arrived_detail_activity, null, false);
    }

    @Override
    protected void initData() {
        title.setText("到款信息");
        IncomeWaitForPayment incomeWaitForPayment= (IncomeWaitForPayment) getIntent().getSerializableExtra(MONEYARRIVEDDETAIL);
        state= getIntent().getIntExtra("state",0);
        if(state==1){
            labKuanxiang.setText("待收项目款");
        }else   if(state==2){
            labKuanxiang.setText("已收项目款");
        }
        mPresenter.getMoneyArrivedDetail(incomeWaitForPayment.getId(),incomeWaitForPayment.getStid());
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMoneyArrivedDetailComponent
                .builder()
                .appComponent(appComponent)
                .moneyArrivedDetailModule(new MoneyArrivedDetailModule(this))//请将WaitForPaymentModule()第一个首字母改为小写
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
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void bindDAtaToView(MoneyArrivedDetail moneyArrivedDetail) {
        neighbourhoods.setText(moneyArrivedDetail.getHousesname());
        floor.setText(moneyArrivedDetail.getHousesaddress());
        projectName.setText(moneyArrivedDetail.getStname()+"项目款");
        owner.setText(moneyArrivedDetail.getCusname());
        receivedMoney.setText("¥"+CommonUtil.formatMoney(moneyArrivedDetail.getProsum(),2));
        projectItem.setText(moneyArrivedDetail.getStname()+"工程款");
        itemMoney.setText("¥"+CommonUtil.formatMoney(moneyArrivedDetail.getMoney(),2));
        settlementTime.setText(TimeUtil.keepTimeYMD(moneyArrivedDetail.getCreate_time(),YEAR_MONTH_DAY));
    }
}
