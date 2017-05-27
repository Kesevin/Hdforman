package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.Material;

import butterknife.BindView;


/**
 * Created by kelvin on 2016/11/3.
 */

public class ConstructMaterialHolder extends BaseHolder<Material> {

    @BindView(R.id.tv_materials_title)
    TextView mTitle;

    public ConstructMaterialHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(Material data) {
        mTitle.setText(data.getName());
    }
}
