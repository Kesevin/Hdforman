package com.dgg.hdforeman.mvp.ui.income.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.ToastUtils;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerIncomeComponent;
import com.dgg.hdforeman.di.module.IncomeModule;
import com.dgg.hdforeman.mvp.contract.income.IncomeContract;
import com.dgg.hdforeman.mvp.model.been.Income;
import com.dgg.hdforeman.mvp.presenter.income.IncomePresenter;
import com.dgg.hdforeman.mvp.ui.base.HDFragment;
import com.dgg.hdforeman.mvp.ui.income.activity.WaitForPaymentActivity;
import com.dgg.hdforeman.mvp.ui.income.activity.WithdrawsCashActivity;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.zhy.autolayout.AutoLinearLayout;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.income.activity.MoneyArrivedDetailActivity.WAITFORPAYMENT;


/**
 * Created by Rex on 2016/10/20.
 */

public class IncomeFragment extends HDFragment<IncomePresenter> implements IncomeContract.View,SwipeRefreshLayout.OnRefreshListener{

    @Nullable
    @BindView(R.id.toolbar_back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.all_income)
    TextView allIncome;
    @BindView(R.id.announcement_layout)
    AutoLinearLayout announcementLayout;
    @BindView(R.id.can_use)
    TextView canUse;
    @BindView(R.id.will_get)
    TextView willGet;
    @BindView(R.id.daidaokuan_layout)
    AutoLinearLayout daidaokuanLayout;
    @BindView(R.id.hava_got)
    TextView havaGot;
    @BindView(R.id.yidaokuan_layout)
    AutoLinearLayout yidaokuanLayout;
    @BindView(R.id.money_detail)
    TextView moneyDetail;
    @BindView(R.id.zengkoukuan_layout)
    AutoLinearLayout zengkoukuanLayout;
    @BindView(R.id.income_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Income mIncome;

    @Override
    protected View initView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.income_fragment, null, false);
    }

    @Override
    protected void initData() {
        rightMenu.setText("提现");
        rightMenu.setVisibility(View.VISIBLE);
        title.setVisibility(View.GONE);
        back.setVisibility(View.GONE);
        initSwipeRefreshLayout();
        mPresenter.getIncomeData();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor,
                R.color.colorLightRed, R.color.colorPrimaryDark);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @OnClick({R.id.daidaokuan_layout, R.id.yidaokuan_layout, R.id.zengkoukuan_layout,R.id.right_menu})
    public void onClick(View v) {
        Intent intent =new Intent();
        Class<?> cls=null;
        switch (v.getId()) {
             case R.id.daidaokuan_layout://待到款
                 cls=WaitForPaymentActivity.class;
                 intent.putExtra(WAITFORPAYMENT,1);
                break;
            case R.id.yidaokuan_layout://已到款
                cls=WaitForPaymentActivity.class;
                intent.putExtra(WAITFORPAYMENT,2);
                break;
            case R.id.zengkoukuan_layout://增扣款明细
                cls=WaitForPaymentActivity.class;
                intent.putExtra(WAITFORPAYMENT,3);
                break;
            case  R.id.right_menu://提现
                cls=WithdrawsCashActivity.class;
                intent.putExtra("allMoney","0");
                if(mIncome!=null){
                    intent.putExtra("allMoney",mIncome.getKyye());
                }
                break;
        }
        intent.setClass(getActivity(),cls);
        launchActivity(intent);
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {
        DaggerIncomeComponent
                .builder()
                .appComponent(appComponent)
                .incomeModule(new IncomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void bindDataToView(Income income) {
        this.mIncome=income;
        allIncome.setText("¥"+CommonUtil.formatMoney(income.getZjsr(),2));
        canUse.setText("¥"+CommonUtil.formatMoney(income.getKyye(),2));
        willGet.setText("¥"+CommonUtil.formatMoney(income.getDdk(),2));
        havaGot.setText("¥"+CommonUtil.formatMoney(income.getYdk(),2));
    }

    @Subscriber(tag = "refresh", mode = ThreadMode.MAIN)
    public void updateTab(boolean b) {
        mPresenter.getIncomeData();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showToast(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void onRefresh() {
        mPresenter.getIncomeData();
    }
}
