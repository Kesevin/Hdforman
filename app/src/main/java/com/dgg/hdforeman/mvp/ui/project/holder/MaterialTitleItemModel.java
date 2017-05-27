package com.dgg.hdforeman.mvp.ui.project.holder;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/27.
 */

public class MaterialTitleItemModel extends EpoxyModelWithHolder<MaterialTitleItemModel.MaterialTitleItemHolder> {


    @Override
    protected MaterialTitleItemHolder createNewHolder() {
        return new MaterialTitleItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_material_title;
    }

    static class MaterialTitleItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.tv_materials_title)
        TextView tvMaterialsTitle;
    }
}
