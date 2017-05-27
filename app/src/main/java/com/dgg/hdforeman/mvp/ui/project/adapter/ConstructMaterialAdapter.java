package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.ConstructProgressContract;
import com.dgg.hdforeman.mvp.contract.project.MaterialBagContract;
import com.dgg.hdforeman.mvp.model.been.MaterialBean;
import com.dgg.hdforeman.mvp.model.been.MaterialInfo;
import com.dgg.hdforeman.mvp.model.been.ProjectResponse;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructMaterialHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructMaterialInfoHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.DuringConstructHolder;

import java.util.List;



/**
 * Created by kelvin on 2016/11/3.
 */

public class ConstructMaterialAdapter extends BaseAdapter<MaterialInfo> {

    private MaterialBagContract.View mRootView;

    public ConstructMaterialAdapter(List<MaterialInfo> infos) {
        super(infos);
    }

    public MaterialBagContract.View getRootView() {
        return mRootView;
    }

    public void setRootView(MaterialBagContract.View rootView) {
        mRootView = rootView;
    }

    @Override
    public BaseHolder getHolder(View v) {
        return new ConstructMaterialInfoHolder(v,mRootView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.material_item;
    }


    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
            holder.setData(((MaterialInfo) mInfos.get(position)));
    }
}
