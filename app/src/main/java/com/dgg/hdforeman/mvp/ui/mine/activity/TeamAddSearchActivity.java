package com.dgg.hdforeman.mvp.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.DividerItemDecoration;
import com.dgg.hdforeman.mvp.contract.mine.TeamAddSearchContract;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.presenter.mine.TeamAddSearchPresenter;
import com.dgg.hdforeman.mvp.ui.base.BaseActivity;
import com.dgg.hdforeman.mvp.ui.mine.adapter.AddTeamAdapter;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/10/21.
 */

public class TeamAddSearchActivity extends BaseActivity implements TeamAddSearchContract.View, TeamAddSearchContract.NetUtil, SwipeRefreshLayout.OnRefreshListener {

    public static final int PAGESIZE = 2;

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

    private TeamAddSearchPresenter presenter;
    private List<TeamSearchResult> mTeamAddList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private AddTeamAdapter mAddTeamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_team_add_activity);
    }

    @Override
    protected void initView() {
        title.setVisibility(View.GONE);
        searchLayout.setVisibility(View.VISIBLE);
        presenter = new TeamAddSearchPresenter(this, (HDApplication) getApplication());
        searchEditText.setFocusable(true);
        searchEditText.setEnabled(true);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        teamAddRecyclerView.setItemAnimator(new DefaultItemAnimator());
        teamAddRecyclerView.setHasFixedSize(true);
        teamAddRecyclerView.setLayoutManager(mLinearLayoutManager);
        teamAddRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAddTeamAdapter = new AddTeamAdapter(this, this);
        mAddTeamAdapter.addTeamList(mTeamAddList);
        teamAddRecyclerView.setAdapter(mAddTeamAdapter);
        presenter.getSearchTeamData();
    }

    @Override
    public String getSearchCondition() {
        return searchEditText.getText().toString();
    }

    @Override
    public void bindDataToListView(List<TeamSearchResult> teamAdd) {
        mTeamAddList.clear();
        mTeamAddList.addAll(teamAdd);
        mAddTeamAdapter.notifyDataSetChanged();
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

    }

    @Override
    public void killMyself() {

    }


    @OnClick({R.id.toolbar_back, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.search:
                presenter.getSearchTeamData();
                break;
        }
    }

    @Override
    public void addTeamUserUtil(Long grid,int position) {
        presenter.addTeamUser(grid,position);
    }

    @Override
    public void removeAdd(int position) {
        mTeamAddList.remove(position);
        mAddTeamAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        mTeamAddList.clear();
        presenter.getSearchTeamData();
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
