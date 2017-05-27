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

public class BudgetSheetTitleModel extends EpoxyModelWithHolder<BudgetSheetTitleModel.BudgetSheetTitleHolder> {
    @EpoxyAttribute
    BudgetSheetTitleModel.ExpandImageViewClickListener listener;
    private boolean isExpanded = false;

    @Override
    public void bind(final BudgetSheetTitleHolder holder) {
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
    protected int getDefaultLayout() {
        return R.layout.item_budget_title;
    }

    @Override
    protected BudgetSheetTitleHolder createNewHolder() {
        return new BudgetSheetTitleHolder();
    }

    static class BudgetSheetTitleHolder extends BaseEpoxyHolder {
        @BindView(R.id.progress_name)
        TextView progressName;
        @BindView(R.id.expand_sub_item)
        ImageView expandSubItem;
        @BindView(R.id.arrow_container)
        AutoRelativeLayout arrowContainer;

    }
    public interface ExpandImageViewClickListener {
        void onExpandImageViewClick(long id, boolean isExpanded, int itemSize);
    }
}
