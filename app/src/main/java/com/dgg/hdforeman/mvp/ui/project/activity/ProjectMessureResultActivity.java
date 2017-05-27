package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.config.GlobalConfig;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectMessureResultComponent;
import com.dgg.hdforeman.di.module.ProjectMessureResultModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectMessureResultContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.ProjectMessureResultPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.MessureResultAdapter;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity.PROJECTINFO_DATA;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class ProjectMessureResultActivity extends BacksActivity<ProjectMessureResultPresenter> implements ProjectMessureResultContract.View {
    private static final String EXTRA_ID = "extra_id";
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_edit)
    TextView tvedit;
    @BindView(R.id.tv_price)
    TextView tv_price;
    private String id;
    boolean ispriceed;//标志是否已经测量
    @Override
    protected View initView() {
        return getLayoutInflater().inflate(R.layout.activity_projectmessureresult, null, false);
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra(EXTRA_ID);
        ispriceed=getIntent().getBooleanExtra(GlobalConfig.EXTRA_PRICE,false);
        tvedit.setVisibility(!ispriceed?View.VISIBLE:View.GONE);
        tv_price.setVisibility(!ispriceed?View.VISIBLE:View.GONE);
        tvedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectMessureActivity.startActivity(ProjectMessureResultActivity.this,id,true);
            }
        });
        mPresenter.initview();
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorOrange));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(true);
                    mPresenter.loadInitData(id,true);
                }
            }
        });
        if (!TextUtils.isEmpty(id))
             mPresenter.loadInitData(id,false);

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectMessureResultComponent.builder().appComponent(appComponent)
                .projectMessureResultModule(new ProjectMessureResultModule(this))
                .build().inject(this);
    }



    public static void startActivity(Context context, String id,boolean ispriceed) {
        Intent intent = new Intent(context, ProjectMessureResultActivity.class);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(GlobalConfig.EXTRA_PRICE,ispriceed);
        context.startActivity(intent);
    }

    @Override
    public void initRecylerview(MessureResultAdapter adapter) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvContent.setLayoutManager(linearLayoutManager);
        rvContent.setAdapter(adapter);
    }

    @Override
    public void refreshend() {
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void lauchActivity(String meesure,String housetype, Class<?> cls) {
        ProjectInfoResponse data=new ProjectInfoResponse();
        data.setId(id);
        data.setPm_acreage(meesure);
        data.setPm_housetype(housetype);
        Intent intent = new Intent(mApplication, cls);
        intent.putExtras(composeBundle(data, PROJECTINFO_DATA));
        launchActivity(intent);
    }

    @Override
    public void tvPreiceGoneornot(boolean isvisiable) {
        tv_price.setVisibility(isvisiable?View.VISIBLE:View.GONE);
    }

    @Override
    protected void onResume() {
        updateResult();
        super.onResume();
    }

    public void updateResult(){
        mPresenter.updateData(id);
    }
    protected Bundle composeBundle(Serializable data, String type) {
        Bundle bundle = new Bundle();//用bundle封装数据
        bundle.putSerializable(type, data);
        return bundle;
    }
    @OnClick(R.id.tv_price)
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_price:
                mPresenter.lanuchQue();
                break;
        }
    }
}
