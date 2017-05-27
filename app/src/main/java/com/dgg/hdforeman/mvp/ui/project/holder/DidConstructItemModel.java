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

public class DidConstructItemModel extends EpoxyModelWithHolder<DidConstructItemModel.DidConstructItemHolder> {
    @EpoxyAttribute
    View.OnClickListener onClickListener;
    @EpoxyAttribute
    ProjectInformation.DBean dBean;
    @EpoxyAttribute
    Context context;

    @Override
    public void bind(DidConstructItemHolder holder) {
        super.bind(holder);
        holder.llAccept.setOnClickListener(onClickListener);
        holder.tvArea.setText(dBean.getPm_housesname());
        holder.tvLocation.setText(dBean.getPm_roomno());
        holder.tvOwnerAccept.setText(context.getString(R.string.str_owner_name,dBean.getPm_cusname()));
    }

    @Override
    protected DidConstructItemHolder createNewHolder() {
        return new DidConstructItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_wait_sign;
    }

    static class DidConstructItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.tv_area)
        TextView tvArea;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.rl_floor)
        AutoRelativeLayout rlFloor;
        @BindView(R.id.tv_sign_state)
        TextView tvSignState;
        @BindView(R.id.tv_owner_accept)
        TextView tvOwnerAccept;
        @BindView(R.id.ib_call_accept)
        ImageButton ibCallAccept;
        @BindView(R.id.ll_accept)
        AutoLinearLayout llAccept;
    }
}
