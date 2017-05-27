package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ProjectContentBean;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ProjectListContentHolder;

import java.util.List;

/**
 * Created by kelvin on 2016/11/15.
 */

public class ProjectListContentAdapter extends BaseAdapter<ProjectContentBean> {

    public ProjectListContentAdapter(List<ProjectContentBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ProjectContentBean> getHolder(View v) {
        return new ProjectListContentHolder(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_project_list_layout;
    }
}
