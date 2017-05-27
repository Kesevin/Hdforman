package com.dgg.hdforeman.mvp.ui.project.holder;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.model.been.ProjectContentBean;
import com.dgg.hdforeman.mvp.ui.project.adapter.UpgradePackageAdapter;
import com.jess.arms.utils.CharactorHandler;

import butterknife.BindView;

/**
 * Created by kelvin on 2016/11/15.
 */

public class ProjectListContentHolder extends BaseHolder<ProjectContentBean> {

    private final HDApplication mApplication;
    private UpgradePackageAdapter mAdapter;

    @Nullable
    @BindView(R.id.pe_name)
    TextView mName;
    @Nullable
    @BindView(R.id.pe_acreage)
    TextView mAcreage;
    @Nullable
    @BindView(R.id.recyclerViewContent)
    RecyclerView mRecyclerView;

    public ProjectListContentHolder(View itemView) {
        super(itemView);
        mApplication = (HDApplication) itemView.getContext().getApplicationContext();
    }

    @Override
    public void setData(ProjectContentBean data) {
        mName.setText(CharactorHandler.excludeEmpty(data.getPu_ugname()));
        mAcreage.setText(CharactorHandler.excludeEmpty(data.getPu_number()) + CharactorHandler.excludeEmpty(data.getPu_ugunit()));
//        mName.setText(data.getPe_name());
//        mAcreage.setText(String.format(mApplication.getResources().getString(R.string.str_project_list_acreage),CharactorHandler.excludeEmpty(data.getPe_acreage())));

        if (data.getM() != null && data.getM().size() != 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mAdapter = new UpgradePackageAdapter(data.getM());
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mApplication));
            mRecyclerView.setNestedScrollingEnabled(false);
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
    }
}
