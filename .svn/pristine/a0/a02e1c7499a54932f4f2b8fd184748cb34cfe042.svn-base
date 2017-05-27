package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;

/**
 * Created by twick on 2016/10/26.
 */

public class ProjectOfferTitleModel extends EpoxyModelWithHolder<ProjectOfferTitleModel.ProjectOfferTitleHolder> {

    @EpoxyAttribute
    ExpandImageViewClickListener listener;
    private boolean isExpanded = false;

    @Override
    public void bind(final ProjectOfferTitleHolder holder) {
        super.bind(holder);
        holder.arrowContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onExpandImageViewClick(id(), isExpanded, 5);
                if (isExpanded) {
                    isExpanded = false;
                    holder.expandSubItem.setBackgroundResource(R.mipmap.list_btn_weizhankai);
                } else {
                    isExpanded = true;
                    holder.expandSubItem.setBackgroundResource(R.mipmap.list_btn_zhankai);
                }
            }
        });
    }

    @Override
    protected ProjectOfferTitleHolder createNewHolder() {
        return new ProjectOfferTitleHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_project_offer_title;
    }

    static class ProjectOfferTitleHolder extends BaseEpoxyHolder {
        @BindView(R.id.offer_name)
        TextView offerName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.expand_sub_item)
        ImageView expandSubItem;
        @BindView(R.id.arrow_container)
        AutoRelativeLayout arrowContainer;
    }

    public interface ExpandImageViewClickListener {
        void onExpandImageViewClick(long id, boolean isExpanded, int itemSize);
    }
}
