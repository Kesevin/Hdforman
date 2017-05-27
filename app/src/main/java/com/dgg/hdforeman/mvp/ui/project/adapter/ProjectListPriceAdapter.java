package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ProjectPriceBean;
import com.dgg.hdforeman.mvp.model.been.ProjectTotalBean;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ProjectListPriceHolder;

import java.util.List;

/**
 * Created by kelvin on 2016/11/15.
 */

public class ProjectListPriceAdapter extends BaseAdapter<ProjectPriceBean> {

    public ProjectListPriceAdapter(List<ProjectPriceBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ProjectPriceBean> getHolder(View v) {
        return new ProjectListPriceHolder(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_project_list_price_layout;
    }
}
