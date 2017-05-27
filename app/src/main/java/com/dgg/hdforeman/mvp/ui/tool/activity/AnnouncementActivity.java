package com.dgg.hdforeman.mvp.ui.tool.activity;

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
import com.dgg.hdforeman.di.commponent.DaggerAnnouncementComponent;
import com.dgg.hdforeman.di.module.AnnouncementModule;
import com.dgg.hdforeman.mvp.contract.tool.AnnouncementContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.been.AnnouncementBean;
import com.dgg.hdforeman.mvp.presenter.tool.AnnouncementPresenter;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.tool.adapter.AnnouncementAdapter;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class AnnouncementActivity extends BacksActivity<AnnouncementPresenter> implements AnnouncementContract.View, SwipeRefreshLayout.OnRefreshListener, BaseAdapter.OnRecyclerViewItemClickListener {
    public static final int PAGESIZE = 10;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.announcement_lv)
    RecyclerView mRecyclerView;
    @BindView(R.id.announcement_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.noData)
    LinearLayout noData;

    private AnnouncementAdapter announcementAdapter;
    private List<AnnouncementBean> mData = new ArrayList();
    //    private AnnouncementPresenter presenter;
    private Paginate mPaginate;
    private LinearLayoutManager mLinearLayoutManager;

    private boolean isLoadingMoreEnd=false;//加载更多是否完成
    private boolean isLoadEnd = true;//列表数据是否加载完毕

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.announcement_activity, null);
    }

    @Override
    protected void initData() {
        title.setText("公告");
        initRecylerView();
        initPaginate();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerAnnouncementComponent
                .builder()
                .appComponent(appComponent)
                .announcementModule(new AnnouncementModule(this))
                .build()
                .inject(this);
    }

    private void initRecylerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor,
                R.color.colorLightRed, R.color.colorPrimaryDark);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        announcementAdapter = new AnnouncementAdapter(mData);
        mRecyclerView.setAdapter(announcementAdapter);
        announcementAdapter.setOnItemClickListener(this);

        mPresenter.getAnnouncementListData(false, PAGESIZE);
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
    public void bindDataToRecylerView(List<AnnouncementBean> list) {
        mData.addAll(list);
        announcementAdapter.notifyDataSetChanged();
    }

    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.getAnnouncementListData(false, PAGESIZE);
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
                    .setLoadingTriggerThreshold(0)
//                    .setLoadingListItemCreator(new CustomLoadingListItemCreator(mRecyclerView))
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }

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
        mPresenter.getAnnouncementListData(true, PAGESIZE);
    }

    /**
     * 公告详情
     *
     * @param view
     * @param data
     * @param position
     */
    @Override
    public void onItemClick(View view, Object data, int position) {
        Intent intent = new Intent(this, AnnouncementDetailActivity.class);
        intent.putExtra("url", Api.APP_DOMAIN + "/orgmain.nk?id=" + mData.get(position).getId());
        intent.putExtra("title", "详情");
        launchActivity(intent);
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
