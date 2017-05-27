package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.FitmentPictureHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18.
 */

public class FitmentPictureAdapter extends BaseAdapter<String> {

    public FitmentPictureAdapter(List<String> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<String> getHolder(View v) {
        return new FitmentPictureHolder(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_project_fitment_picture;
    }
}
