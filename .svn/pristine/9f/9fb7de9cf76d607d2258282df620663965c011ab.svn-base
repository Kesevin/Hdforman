package com.dgg.hdforeman.mvp.ui.income.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.DividerItemDecoration;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerWaitForPaymentComponent;
import com.dgg.hdforeman.di.module.WaitForPaymentModule;
import com.dgg.hdforeman.mvp.contract.income.WaitForPaymentContract;
import com.dgg.hdforeman.mvp.model.been.IncomeWaitForPayment;
import com.dgg.hdforeman.mvp.presenter.income.WaitForPaymentPresenter;
import com.dgg.hdforeman.mvp.ui.income.adapter.WaitPayAdapter;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.income.activity.MoneyArrivedDetailActivity.MONEYARRIVEDDETAIL;
import static com.dgg.hdforeman.mvp.ui.income.activity.MoneyArrivedDetailActivity.WAITFORPAYMENT;
import static com.jess.arms.utils.Preconditions.checkNotNull;


public class WaitForPaymentActivity extends BacksActivity<WaitForPaymentPresenter> implements WaitForPaymentContract.View, SwipeRefreshLayout.OnRefreshListener, BaseAdapter.OnRecyclerViewItemClickListener {

    private final int PAGE_SIZE = 10;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.payment_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.payment_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.noData)
    LinearLayout noData;

    private WaitPayAdapter mWaitPayAdapter;
    private List<IncomeWaitForPayment> mData = new ArrayList();
    private boolean isLoadingMoreEnd = false;//加载更多是否完成
    private boolean isLoadEnd = true;//列表数据是否加载完毕
    private Paginate mPaginate;
    private int state;//1待到款，2已到款，3增扣款

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerWaitForPaymentComponent
                .builder()
                .appComponent(appComponent)
                .waitForPaymentModule(new WaitForPaymentModule(this))//请将WaitForPaymentModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.wait_for_payment_activity, null, false);
    }

    @Override
    protected void initData() {
        state = getIntent().getIntExtra(WAITFORPAYMENT, 0);
        initRecyleView();
        initAdapter();
        initPaginate();
        if (state == 1) {
            title.setText("待付款");
            mPresenter.getWaitForPaymentData(false, PAGE_SIZE);
        } else if (state == 2) {
            title.setText("已到款");
            rightMenu.setVisibility(View.VISIBLE);
            rightMenu.setText("收入明细");
            mPresenter.getMoneyArrivedData(false, PAGE_SIZE);
        } else {
            title.setText("增扣款");
            mPresenter.getChargebackData(false, PAGE_SIZE);
        }
    }

    private void initAdapter() {
        mWaitPayAdapter = new WaitPayAdapter(mData, state);
        mRecyclerView.setAdapter(mWaitPayAdapter);
        if (state == 1||state == 2)
            mWaitPayAdapter.setOnItemClickListener(this);

    }

    private void initRecyleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
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
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(this, intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    /**
     * 上拉加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    if (state == 1) {
                        mPresenter.getWaitForPaymentData(false, PAGE_SIZE);
                    } else if (state == 2) {
                        mPresenter.getMoneyArrivedData(false, PAGE_SIZE);
                    } else {
                        mPresenter.getChargebackData(false, PAGE_SIZE);
                    }
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMoreEnd;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return isLoadEnd;
                }
            };
            mPaginate = Paginate.with(mRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(2)
//                    .setLoadingListItemCreator(new CustomLoadingListItemCreator(teamAddRecyclerView))
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void startLoadMore() {
        isLoadingMoreEnd = true;
    }

    @Override
    public void endLoadMore() {
        isLoadingMoreEnd = false;
    }

    @Override
    public void setLoadEnd(boolean isLoadEnd) {
        this.isLoadEnd = isLoadEnd;
    }

    @Override
    public void showNoData() {
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hintNodata() {
        noData.setVisibility(View.GONE);
    }

    @Override
    public void bindDataToView(List<IncomeWaitForPayment> list) {
        mData.addAll(list);
        mWaitPayAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.right_menu})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_menu:
                launchActivity(new Intent(this, IncomeDetailActivity.class));
                break;
        }
    }

    @Override
    public void onRefresh() {
        mData.clear();
        if (state == 1) {
            mPresenter.getWaitForPaymentData(true, PAGE_SIZE);
        } else if (state == 2) {
            mPresenter.getMoneyArrivedData(true, PAGE_SIZE);
        } else {
            mPresenter.getChargebackData(true, PAGE_SIZE);
        }
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
        Intent intent = new Intent(this, MoneyArrivedDetailActivity.class);
        intent.putExtra("state", state);
        intent.putExtra(MONEYARRIVEDDETAIL, mData.get(position));
        launchActivity(intent);
    }
}
