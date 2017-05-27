package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/26.
 */

public class BudgetSheetSubItemModel extends EpoxyModelWithHolder<BudgetSheetSubItemModel.BudgetSheetSubItemHolder> {

    @EpoxyAttribute
    boolean isFirst = false;
    @EpoxyAttribute
    boolean isLast = false;

    @Override
    public void bind(BudgetSheetSubItemHolder holder) {
        super.bind(holder);
        //绑定数据
        if (isFirst) {
            holder.upVerticalLine.setVisibility(View.INVISIBLE);
        } else {
            holder.upVerticalLine.setVisibility(View.VISIBLE);
        }
        if (isLast) {
            holder.downVerticalLine.setVisibility(View.INVISIBLE);
        } else {
            holder.downVerticalLine.setVisibility(View.VISIBLE);

        }
    }

    @Override
    protected BudgetSheetSubItemHolder createNewHolder() {
        return new BudgetSheetSubItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_sub_budget;
    }

    static class BudgetSheetSubItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.up_vertical_line)
        View upVerticalLine;
        @BindView(R.id.down_vertical_line)
        View downVerticalLine;
        @BindView(R.id.progress_state)
        ImageView progressState;
        @BindView(R.id.progress_sub_name)
        TextView progressSubName;
    }
}
