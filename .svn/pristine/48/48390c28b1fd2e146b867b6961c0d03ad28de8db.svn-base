package com.dgg.hdforeman.mvp.ui.project.holder;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoRadioGroup;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/26.
 */

public class ProjectOfferTopModel extends EpoxyModelWithHolder<ProjectOfferTopModel.ProjectOfferTopHolder> {


    @Override
    protected ProjectOfferTopHolder createNewHolder() {
        return new ProjectOfferTopHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_project_offer_top;
    }

    static class ProjectOfferTopHolder extends BaseEpoxyHolder {
        @BindView(R.id.tv_room_num)
        TextView tvRoomNum;
        @BindView(R.id.arg_num)
        AutoRadioGroup argNum;
        @BindView(R.id.arg_toilet_num)
        AutoRadioGroup argToiletNum;

    }
}
