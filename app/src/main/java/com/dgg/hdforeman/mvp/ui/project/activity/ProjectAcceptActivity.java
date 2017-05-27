package com.dgg.hdforeman.mvp.ui.project.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.DaggerProjectAcceptComponent;
import com.dgg.hdforeman.di.module.ProjectAcceptModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectAcceptContract;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.ProjectAcceptPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.UiUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.project.activity.IntermediateAcceptActivity.INTERMEDIATE_ACCEPT_DATA;
import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Created by kelvin on 2016/11/7.
 */

public class ProjectAcceptActivity extends BacksActivity<ProjectAcceptPresenter> implements ProjectAcceptContract.View,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static final String PROJECT_ACCEPT_DATA = "project_accept_data";

    private ProjectInfoResponse mData;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectAcceptComponent
                .builder()
                .appComponent(appComponent)
                .projectAcceptModule(new ProjectAcceptModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.project_accept_activity, null, false);
    }

    @Override
    protected void initData() {
        mData = (ProjectInfoResponse) getIntent().getExtras().getSerializable(PROJECT_ACCEPT_DATA);
        initRecyclerView();
        mPresenter.initAdapter();
        qryData();
    }

    /**
     * 请求工程验收列表信息
     */
    private void qryData(){
        mPresenter.requestList(mData);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @OnClick(R.id.toolbar_right)
    public void onClick(){
        callPhone();
    }

    /**
     * 给业主打电话
     */
    public void callPhone() {
        PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                if (ActivityCompat.checkSelfPermission(ProjectAcceptActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + mData.getPm_cuscontactno())));
            }
        }, RxPermissions.getInstance(this), this, mHDApplication.getAppComponent().rxErrorHandler());
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 跳转到阶段验收界面
     */
    @Override
    public void launchIntermediateAcceptActivity(Intent intent) {
        intent.putExtra(INTERMEDIATE_ACCEPT_DATA,mData);
        intent.setClass(this,IntermediateAcceptActivity.class);
        launchActivity(intent);
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {

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
        qryData();
    }
}
