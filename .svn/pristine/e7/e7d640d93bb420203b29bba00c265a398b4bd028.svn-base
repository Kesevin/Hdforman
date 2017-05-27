package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerConstructTeamComponent;
import com.dgg.hdforeman.di.module.ConstructTeamModule;
import com.dgg.hdforeman.mvp.contract.project.ConstructTeamContract;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.WorkerBean;
import com.dgg.hdforeman.mvp.presenter.project.ConstructTeamPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.ConstructTeamAdapter;
import com.jess.arms.utils.UiUtils;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.project.activity.AddMembersActivity.ADD_MEMBERS;
import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Created by kelvin on 2016/11/3.
 */

public class ConstructTeamActivity extends BacksActivity<ConstructTeamPresenter> implements ConstructTeamContract.View, ConstructTeamContract.NetUtil, SwipeRefreshLayout.OnRefreshListener {

    public static final String CONSTRUCT_TEAM_DATA = "construct_team_data";

    @BindView(R.id.ConstructTeamRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ConstructTeamSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.noData)
    LinearLayout noData;

    private ProjectInfoResponse mData;
    private ConstructTeamAdapter adapter;
    private ArrayList<ConstructTeamBean> mList = new ArrayList<>();

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.construct_team_activity, null, false);
    }

    @Override
    protected void initData() {
        mData = (ProjectInfoResponse) getIntent().getExtras().getSerializable(CONSTRUCT_TEAM_DATA);
        initRecyclerView();
        initAdapter();
        qryData();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerConstructTeamComponent
                .builder()
                .appComponent(appComponent)
                .constructTeamModule(new ConstructTeamModule(this))//请将AModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    /**
     * 请求施工团队信息列表
     */
    private void qryData() {
        if (mData == null)
            return;
        mPresenter.requestList(mData.getId());
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
    }

    private void initAdapter() {
        adapter = new ConstructTeamAdapter(this, this, mList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void launchActivity(Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(this, intent);
    }

    @Override
    public void deleteWorker(WorkerBean workerBean) {

    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void bindDataToView(List<ConstructTeamBean> data) {
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshAdapter(ConstructTeamBean constructTeamBean) {
        onRefresh();
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }

    @Subscriber(tag = "refreshMember", mode = ThreadMode.MAIN)
    public void updateTab(boolean b) {
        onRefresh();
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
    public void onRefresh() {
        mList.clear();
        mPresenter.requestList(mData.getId());
    }

    @OnClick(R.id.toolbar_right)
    public void onClick() {
        Intent intent = new Intent(this, AddMembersActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ADD_MEMBERS, mList);
        bundle.putSerializable(CONSTRUCT_TEAM_DATA, mData);
        intent.putExtras(bundle);
        launchActivity(intent);
    }

    /**
     * 删除施工团队成员
     *
     * @param constructTeamBean
     */
    @Override
    public void deleteTeamMemberUtil(ConstructTeamBean constructTeamBean) {
        mPresenter.deleteWorker(mData.getId(), constructTeamBean);
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
