package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.config.EventBusTag;
import com.dgg.hdforeman.app.config.GlobalConfig;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerFreeTermComponent;
import com.dgg.hdforeman.di.module.FreeTermModule;
import com.dgg.hdforeman.mvp.contract.project.FreeTemContract;
import com.dgg.hdforeman.mvp.presenter.project.FreeTermPresenter;
import com.dgg.hdforeman.mvp.ui.project.adapter.FreeTermAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import org.simple.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FreeTemActivity extends BacksActivity<FreeTermPresenter> implements FreeTemContract.View {
    @BindView(R.id.toolbar_right)
    TextView toolbarRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String id;

    @Override
    protected View initView() {
        return getLayoutInflater().inflate(R.layout.activity_free_tem, null, false);
    }

    @Override
    protected void initData() {
        toolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseQuickAdapter adapter= (BaseQuickAdapter) recyclerView.getAdapter();
                int count=adapter.getData().size();
                AddFreeTermActivity.starActivity(FreeTemActivity.this,id,count);
            }
        });
        id = getIntent().getStringExtra(GlobalConfig.EXTRA_PID);
        if (id == null) finish();
        mPresenter.loadDatafromDb(id);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFreeTermComponent.builder().appComponent(appComponent)
                .freeTermModule(new FreeTermModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadDatafromDb(id);
    }

    @Override
    public void initRecylerView(FreeTermAdapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public static void starActivity(Context context, String pid) {
        Intent intent = new Intent(context, FreeTemActivity.class);
        intent.putExtra(GlobalConfig.EXTRA_PID, pid);
        context.startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(mPresenter.getFreedatas(id), EventBusTag.FREE_ITEMS);
        super.onBackPressed();

    }
}
