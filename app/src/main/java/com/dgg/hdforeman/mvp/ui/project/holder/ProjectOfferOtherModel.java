package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/27.
 */

public class ProjectOfferOtherModel extends EpoxyModelWithHolder<ProjectOfferOtherModel.ProjectOfferOtherHolder> {
    @EpoxyAttribute
    View.OnClickListener listener;

    @Override
    public void bind(ProjectOfferOtherHolder holder) {
        super.bind(holder);
        holder.rlOther.setOnClickListener(listener);
    }

    @Override
    protected ProjectOfferOtherHolder createNewHolder() {
        return new ProjectOfferOtherHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_project_offer_other;
    }

    static class ProjectOfferOtherHolder extends BaseEpoxyHolder {
        @BindView(R.id.tv_other)
        TextView tvOther;
        @BindView(R.id.rl_other)
        AutoRelativeLayout rlOther;

    }
}
