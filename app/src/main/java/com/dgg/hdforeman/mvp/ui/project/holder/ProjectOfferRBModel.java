package com.dgg.hdforeman.mvp.ui.project.holder;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoRadioGroup;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/26.
 */

public class ProjectOfferRBModel extends EpoxyModelWithHolder<ProjectOfferRBModel.ProjectOfferRBHolder> {


    @Override
    protected ProjectOfferRBHolder createNewHolder() {
        return new ProjectOfferRBHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_project_offer_rg;
    }

    static class ProjectOfferRBHolder extends BaseEpoxyHolder {
        @BindView(R.id.rg_project_offer)
        AutoRadioGroup rgProjectOffer;
    }
}
