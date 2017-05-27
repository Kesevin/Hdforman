package com.dgg.hdforeman.mvp.ui.project.holder;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/25.
 */

public class DuringSureItemModel extends EpoxyModelWithHolder<DuringSureItemModel.DuringSureItemHolder> {


    @Override
    protected DuringSureItemHolder createNewHolder() {
        return new DuringSureItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.layout_sure_project;
    }

    static class DuringSureItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.tv_add_title)
        TextView tvAddTitle;
        @BindView(R.id.tv_construct_name)
        TextView tvConstructName;
        @BindView(R.id.tv_construct_size)
        TextView tvConstructSize;
        @BindView(R.id.tv_owner_name_construct)
        TextView tvOwnerNameConstruct;
        @BindView(R.id.ib_call)
        ImageButton ibCall;
        @BindView(R.id.btn_sure_project)
        Button btnSureProject;
        @BindView(R.id.rl_add_item)
        AutoLinearLayout rlAddItem;
        @BindView(R.id.ll_construction)
        AutoLinearLayout llConstruction;
    }
}
