package com.dgg.hdforeman.mvp.ui.mine.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.ToastUtils;
import com.dgg.hdforeman.mvp.contract.mine.TeamContract;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.dgg.hdforeman.mvp.presenter.mine.TeamPresenter;
import com.dgg.hdforeman.mvp.ui.base.BaseActivity;
import com.dgg.hdforeman.mvp.ui.mine.adapter.TeamAdapter;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectInformationActivity;
import com.jess.arms.utils.PermissionUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.functions.Action1;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Created by Administrator on 2016/10/20.
 */

public class TeamActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, TeamContract.View {

    @BindView(R.id.team_recyclerView)
    RecyclerView teamRecyclerView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.team_swipe_refresh_widget)
    SwipeRefreshLayout teamSwipeRefreshWidget;
    @BindView(R.id.noData)
    LinearLayout noData;

    private TeamAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private List<TeamBean> teamListData = new ArrayList<>();
    private TeamPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_team_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        presenter = new TeamPresenter(this, (HDApplication) getApplication());
        title.setText("团队管理");
        rightMenu.setText("添加成员");
        rightMenu.setVisibility(View.VISIBLE);

        mLinearLayoutManager = new LinearLayoutManager(this);
        teamRecyclerView.setItemAnimator(new DefaultItemAnimator());
        teamRecyclerView.setHasFixedSize(true);
        teamRecyclerView.setLayoutManager(mLinearLayoutManager);
//        teamRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        teamSwipeRefreshWidget.setColorSchemeResources(R.color.colorOrange, R.color.bgGreyColor, R.color.colorLightRed, R.color.colorPrimaryDark);
        teamSwipeRefreshWidget.setOnRefreshListener(this);
        teamSwipeRefreshWidget.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        adapter = new TeamAdapter(this);
        adapter.addTeamList(teamListData);
        teamRecyclerView.setAdapter(adapter);
        presenter.getTeamDataLists();
    }

    @OnClick({R.id.toolbar_back, R.id.right_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.right_menu:
                Intent intent = new Intent(this, TeamAddSearchActivity.class);

                startActivity(intent);
                break;
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        teamListData.clear();
        presenter.getTeamDataLists();
    }

    @Override
    public void showLoading() {
        teamSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        teamSwipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ToastUtils.showToast(message);
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public void bindDataToRecycleView(List<TeamBean> teamList) {
        teamListData.addAll(teamList);
        adapter.notifyDataSetChanged();
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
