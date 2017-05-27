package com.dgg.hdforeman.mvp.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.contract.mine.TeamAddContract;
import com.dgg.hdforeman.mvp.contract.mine.TeamAddSearchContract;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.presenter.mine.TeamAddPresenter;
import com.dgg.hdforeman.mvp.ui.base.BaseActivity;
import com.dgg.hdforeman.mvp.ui.mine.adapter.AddTeamAdapter;
import com.jess.arms.utils.UiUtils;
import com.paginate.Paginate;
import com.zhy.autolayout.AutoRelativeLayout;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Created by Administrator on 2016/10/21.
 */

public class TeamAddActivity extends BaseActivity implements TeamAddContract.View, TeamAddSearchContract.NetUtil, SwipeRefreshLayout.OnRefreshListener {

    public static final int PAGESIZE = 10;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.team_add_recyclerView)
    RecyclerView teamAddRecyclerView;
    @BindView(R.id.team_add_search_textView)
    TextView teamAddSearchTextView;
    @BindView(R.id.search_layout)
    AutoRelativeLayout searchLayout;
    @BindView(R.id.search_editText)
    EditText searchEditText;
    @BindView(R.id.team_add_swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.noData)
    LinearLayout noData;

    private TeamAddPresenter presenter;
    private List<TeamSearchResult> mTeamAddList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private Paginate mPaginate;
    private AddTeamAdapter mAddTeamAdapter;
    private boolean isLoadingMoreEnd;//加载更多是否完成
    private boolean isLoadEnd = true;//列表数据是否加载完毕

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_team_add_activity);
    }

    @Override
    protected void initView() {
        title.setVisibility(View.GONE);
        searchLayout.setVisibility(View.VISIBLE);
        presenter = new TeamAddPresenter(this, (HDApplication) getApplication());
        searchEditText.setFocusable(false);
        initRecyclerView();
        initPaginate();
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        teamAddRecyclerView.setItemAnimator(new DefaultItemAnimator());
        teamAddRecyclerView.setHasFixedSize(true);
        teamAddRecyclerView.setLayoutManager(mLinearLayoutManager);
//        teamAddRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAddTeamAdapter = new AddTeamAdapter(this, this);
        mAddTeamAdapter.addTeamList(mTeamAddList);
        teamAddRecyclerView.setAdapter(mAddTeamAdapter);
        presenter.getTeamNoForemanData(false, PAGESIZE);
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

    }

    @Override
    public void launchActivity(Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(this, intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @OnClick({R.id.toolbar_back, R.id.search_layout, R.id.search_editText, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.search_layout:
            case R.id.search_editText:
            case R.id.search:
                launchActivity(new Intent(getApplicationContext(), TeamAddSearchActivity.class));
                break;
        }
    }

    @Override
    public void addTeamUserUtil(Long grid,int position) {
        presenter.addTeamUser(grid,position);
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
    public void bindTeamDataToListView(List<TeamSearchResult> list) {
        mTeamAddList.addAll(list);
        mAddTeamAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeAdd(int position) {
        mTeamAddList.remove(position);
        mAddTeamAdapter.notifyDataSetChanged();
    }

    /**
     * 上拉加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            presenter.getTeamNoForemanData(false,PAGESIZE);
//                        }
//                    },2000);
                    presenter.getTeamNoForemanData(false, PAGESIZE);
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
            mPaginate = Paginate.with(teamAddRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(2)
//                    .setLoadingListItemCreator(new CustomLoadingListItemCreator(teamAddRecyclerView))
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    public void onRefresh() {
        mTeamAddList.clear();
        presenter.getTeamNoForemanData(true, PAGESIZE);
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
    protected boolean needBindEvent() {
        return true;
    }

    @Subscriber(tag = "teamRefresh", mode = ThreadMode.MAIN)
    public void updateTab(boolean b) {
        onRefresh();
    }

}
