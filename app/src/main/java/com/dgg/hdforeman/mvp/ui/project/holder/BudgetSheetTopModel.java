package com.dgg.hdforeman.mvp.ui.project.holder;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/26.
 */

public class BudgetSheetTopModel extends EpoxyModelWithHolder<BudgetSheetTopModel.BudgetSheetTopHolder> {


    @Override
    protected BudgetSheetTopHolder createNewHolder() {
        return new BudgetSheetTopHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_budget_top;
    }

    static class BudgetSheetTopHolder extends BaseEpoxyHolder {
        @BindView(R.id.tv_owner)
        TextView tvOwner;
        @BindView(R.id.tv_bid_amount)
        TextView tvBidAmount;
        @BindView(R.id.tv_foreman)
        TextView tvForeman;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_end_time)
        TextView tvEndTime;
        @BindView(R.id.ll_time)
        AutoLinearLayout llTime;
        @BindView(R.id.ll_bid_amount)
        AutoLinearLayout llBidAmount;
    }
}
