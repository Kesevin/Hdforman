package com.dgg.hdforeman.mvp.ui.income.activity;

import android.content.Intent;
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
import com.dgg.hdforeman.di.commponent.DaggerIncomeDetailComponent;
import com.dgg.hdforeman.di.module.IncomeDetailModule;
import com.dgg.hdforeman.mvp.contract.income.IncomeDetailContract;
import com.dgg.hdforeman.mvp.model.been.IncomeDetail;
import com.dgg.hdforeman.mvp.presenter.income.IncomeDetailPresenter;
import com.dgg.hdforeman.mvp.ui.income.adapter.IncomeDetailAdapter;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class IncomeDetailActivity extends BacksActivity<IncomeDetailPresenter> implements IncomeDetailContract.View, SwipeRefreshLayout.OnRefreshListener {

    private static final int PAGE_SIZE = 10;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.income_detail_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.income_detail_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.noData)
    LinearLayout noData;

    private List<IncomeDetail> mData = new ArrayList();
    private IncomeDetailAdapter mIncomeDetailAdapter;
    private boolean isLoadingMoreEnd = false;//加载更多是否完成
    private boolean isLoadEnd = true;//列表数据是否加载完毕
    private Paginate mPaginate;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.income_detail_activity, null, false);
    }

    @Override
    protected void initData() {
        title.setText("收入明细");
        initRecyleView();
        initAdapter();
        initPaginate();
        mPresenter.getIncomeDetailData(false, PAGE_SIZE);
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

    private void initAdapter() {
        mIncomeDetailAdapter = new IncomeDetailAdapter(mData);
        mRecyclerView.setAdapter(mIncomeDetailAdapter);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerIncomeDetailComponent
                .builder()
                .appComponent(appComponent)
                .incomeDetailModule(new IncomeDetailModule(this))//请将WaitForPaymentModule()第一个首字母改为小写
                .build()
                .inject(this);
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
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    /**
     * 上拉加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getIncomeDetailData(false, PAGE_SIZE);
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
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {
        finish();
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
    public void bindDataToView(List<IncomeDetail> list) {
        mData.addAll(list);
        mIncomeDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mData.clear();
        mPresenter.getIncomeDetailData(true, PAGE_SIZE);
    }
}
