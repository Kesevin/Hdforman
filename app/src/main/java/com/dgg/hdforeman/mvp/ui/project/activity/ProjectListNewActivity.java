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
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectListComponent;
import com.dgg.hdforeman.di.module.ProjectListModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectListContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectTotalBean;
import com.dgg.hdforeman.mvp.presenter.project.ProjectListNewPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.UiUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Created by kelvin on 2016/11/4.
 */

public class ProjectListNewActivity extends BacksActivity<ProjectListNewPresenter> implements ProjectListContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerViewContent)
    RecyclerView mRecyclerViewContent;
    @BindView(R.id.recyclerViewPrice)
    RecyclerView mRecyclerViewPrice;

    public static final String PROJECT_LIST_DATA = "project_list_data";

    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.title)
    TextView title;

    private ProjectInfoResponse mData;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectListComponent
                .builder()
                .appComponent(appComponent)
                .projectListModule(new ProjectListModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.project_list_activity_new, null, false);
    }

    @Override
    protected void initData() {
        rightMenu.setText("联系业主");
        rightMenu.setVisibility(View.VISIBLE);
        title.setText("项目清单");
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mData = (ProjectInfoResponse) getIntent().getExtras().getSerializable(PROJECT_LIST_DATA);
        initRecyclerView();
        mPresenter.initAdapter();
        qryData();
    }

    private void qryData() {
        mPresenter.requestList(mData.getId());
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mRecyclerViewContent.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewContent.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewContent.setNestedScrollingEnabled(false);

        mRecyclerViewPrice.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewPrice.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewPrice.setNestedScrollingEnabled(false);
    }

    @Override
    public void setContentAdapter(BaseAdapter adapter) {
        mRecyclerViewContent.setAdapter(adapter);
    }

    @Override
    public void updateLayout(ProjectTotalBean data,String total) {

    }

    @Override
    public void setPriceAdapter(BaseAdapter adapter) {
        mRecyclerViewPrice.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestList(mData.getId());
    }

    @OnClick(R.id.right_menu)
    public void onClick() {
        callPhone();
    }

    /**
     * 给业主打电话
     */
    public void callPhone() {
        PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                if (ActivityCompat.checkSelfPermission(ProjectListNewActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + mData.getPm_cuscontactno())));
            }
        }, RxPermissions.getInstance(this), this, mHDApplication.getAppComponent().rxErrorHandler());
    }

    @Override
    public void launchActivity(Intent intent) {

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
}
