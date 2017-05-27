package com.dgg.hdforeman.mvp.ui.project.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ProjectInformation;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/25.
 */

public class DuringProjectItemModel extends EpoxyModelWithHolder<DuringProjectItemModel.DuringProjectItemHolder> {
    @EpoxyAttribute
    String str;
    @EpoxyAttribute
    View.OnClickListener onClickListener;
    @EpoxyAttribute
    ProjectInformation.DBean dataBean;
    @EpoxyAttribute
    Context context;

    @Override
    public void bind(DuringProjectItemHolder holder) {
        super.bind(holder);
        holder.llAccept.setOnClickListener(onClickListener);
        holder.tvLocation.setText(dataBean.getPm_roomno());
        holder.tvArea.setText(dataBean.getPm_housesname());
        holder.tvOwnerAccept.setText(context.getString(R.string.str_owner_name, dataBean.getPm_cusname()));
//        holder.tvAcceptState.setText(dataBean.getStage());
    }

    @Override
    protected DuringProjectItemHolder createNewHolder() {
        return new DuringProjectItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.layout_during_accept;
    }

    static class DuringProjectItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.tv_area)
        TextView tvArea;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.rl_floor)
        AutoRelativeLayout rlFloor;
//        @BindView(R.id.tv_accept_state)
//        TextView tvAcceptState;
//        @BindView(R.id.tv_pay_state)
//        TextView tvPayState;
        @BindView(R.id.tv_owner_accept)
        TextView tvOwnerAccept;
        @BindView(R.id.ib_call_accept)
        ImageButton ibCallAccept;
        @BindView(R.id.ll_accept)
        AutoLinearLayout llAccept;

    }
}
