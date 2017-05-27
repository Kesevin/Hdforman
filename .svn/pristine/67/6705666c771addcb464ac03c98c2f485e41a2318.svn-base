package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/26.
 */

public class ProjectOfferAddModel extends EpoxyModelWithHolder<ProjectOfferAddModel.ProjectOfferAddHolder> {

    @EpoxyAttribute
    View.OnClickListener listener;

    @Override
    public void bind(ProjectOfferAddHolder holder) {
        super.bind(holder);
        holder.offerSubAdd.setOnClickListener(listener);
    }

    @Override
    protected ProjectOfferAddHolder createNewHolder() {
        return new ProjectOfferAddHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_project_offer_add;
    }

    static class ProjectOfferAddHolder extends BaseEpoxyHolder {

        @BindView(R.id.offer_sub_name)
        TextView offerSubName;
        @BindView(R.id.offer_sub_size)
        TextView offerSubSize;
        @BindView(R.id.offer_sub_add)
        ImageButton offerSubAdd;

    }
}
