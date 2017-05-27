package com.dgg.hdforeman.mvp.ui.mine.activity;

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
import com.dgg.hdforeman.di.commponent.DaggerMineScoreComponent;
import com.dgg.hdforeman.di.module.MineScoreModule;
import com.dgg.hdforeman.mvp.contract.mine.ScoreContract;
import com.dgg.hdforeman.mvp.model.been.ScoreBean;
import com.dgg.hdforeman.mvp.presenter.mine.MineScorePresenter;
import com.dgg.hdforeman.mvp.ui.mine.adapter.ScoreAdapter;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/10/21.
 */

public class ScoreActivity extends BacksActivity<MineScorePresenter> implements ScoreContract.View, SwipeRefreshLayout.OnRefreshListener {
    public static final int PAGESIZE = 10;

    @BindView(R.id.score_recyclerView)
    RecyclerView scoreRecyclerView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.score_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.noData)
    LinearLayout noData;

    private ScoreAdapter scoreAdapter;
    private List<ScoreBean> mData = new ArrayList();
    private Paginate mPaginate;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean isLoadingMoreEnd=false;//加载更多是否完成
    private boolean isLoadEnd = true;//列表数据是否加载完毕

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.mine_score_activity, null);
    }

    @Override
    protected void initData() {
        title.setText("我的评分");
        initRecylerView();
        scoreAdapter = new ScoreAdapter(this);
        scoreAdapter.addListData(mData);
        scoreRecyclerView.setAdapter(scoreAdapter);
        mPresenter.getScoreListData(false, PAGESIZE);
        initPaginate();
    }

    private void initRecylerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        scoreRecyclerView.setItemAnimator(new DefaultItemAnimator());
        scoreRecyclerView.setHasFixedSize(true);
        scoreRecyclerView.setLayoutManager(mLinearLayoutManager);
        scoreRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor,
                R.color.colorLightRed, R.color.colorPrimaryDark);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @OnClick(R.id.toolbar_back)
    public void onClick() {
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
    public void setLoadEnd(boolean b) {
        this.isLoadEnd = b;
    }

    @Override
    public void bindDataToRecylerView(List<ScoreBean> list) {
        mData.addAll(list);
        scoreAdapter.notifyDataSetChanged();
    }

    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getScoreListData(false, PAGESIZE);
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
            mPaginate = Paginate.with(scoreRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
//                    .setLoadingListItemCreator(new CustomLoadingListItemCreator(mRecyclerView))
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMineScoreComponent
                .builder()
                .appComponent(appComponent)
                .mineScoreModule(new MineScoreModule(this))
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
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void onRefresh() {
        mData.clear();
        mPresenter.getScoreListData(true, PAGESIZE);
    }

    @Override
    public void showNoData() {
        noData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hintNodata() {
        noData.setVisibility(View.GONE);
    }
}
