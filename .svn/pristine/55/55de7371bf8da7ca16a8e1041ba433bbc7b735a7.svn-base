package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.FieldLiveContract;
import com.dgg.hdforeman.mvp.model.been.LiveBean;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.FieldLiveHolder;

import java.util.List;


/**
 * Created by kelvin on 2016/11/8.
 */

public class FieldLiveAdapter extends BaseAdapter<LiveBean> {

    private FieldLiveContract.View mRootView;

    public FieldLiveContract.View getRootView() {
        return mRootView;
    }

    public void setRootView(FieldLiveContract.View rootView) {
        mRootView = rootView;
    }

    public FieldLiveAdapter(List infos) {
        super(infos);
    }

    @Override
    public BaseHolder getHolder(View v) {
        return new FieldLiveHolder(v,getRootView());
    }

    @Override
    public int getLayoutId() {
        return R.layout.field_live_list_item;
    }
}
