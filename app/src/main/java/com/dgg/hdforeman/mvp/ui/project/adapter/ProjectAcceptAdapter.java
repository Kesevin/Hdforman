package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ProjectAcceptResponse;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ProjectAcceptHolder;

import java.util.List;

/**
 * Created by kelvin on 2016/11/11.
 */

public class ProjectAcceptAdapter extends BaseAdapter<ProjectAcceptResponse> {

    public ProjectAcceptAdapter(List infos) {
        super(infos);
    }

    @Override
    public BaseHolder getHolder(View v) {
        return new ProjectAcceptHolder(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.project_accept_list_item;
    }

}
