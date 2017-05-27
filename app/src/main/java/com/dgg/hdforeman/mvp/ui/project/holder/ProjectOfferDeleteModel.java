package com.dgg.hdforeman.mvp.ui.project.holder;

import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/26.
 */

public class ProjectOfferDeleteModel extends EpoxyModelWithHolder<ProjectOfferDeleteModel.ProjectOfferDeleteHolder> {


    @Override
    protected ProjectOfferDeleteHolder createNewHolder() {
        return new ProjectOfferDeleteHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_project_offer_delete;
    }

    static class ProjectOfferDeleteHolder extends BaseEpoxyHolder {
        @BindView(R.id.offer_sub_name)
        TextView offerSubName;
        @BindView(R.id.offer_sub_size)
        TextView offerSubSize;
        @BindView(R.id.offer_sub_delete)
        ImageButton offerSubDelete;
    }
}
