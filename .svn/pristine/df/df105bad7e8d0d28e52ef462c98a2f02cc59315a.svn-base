package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/25.
 */

public class DeleteMemberItemModel extends EpoxyModelWithHolder<DeleteMemberItemModel.DeleteMemberItemHolder> {
    @EpoxyAttribute
    View.OnClickListener onClickListener;

    @Override
    public void bind(DeleteMemberItemHolder holder) {
        super.bind(holder);
        holder.ibDeleteMember.setOnClickListener(onClickListener);
    }

    @Override
    protected DeleteMemberItemHolder createNewHolder() {
        return new DeleteMemberItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_delete_member;
    }

    static class DeleteMemberItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.iv_mine)
        ImageView ivMine;
        @BindView(R.id.tv_mine_name)
        TextView tvMineName;
        @BindView(R.id.ib_delete_member)
        ImageButton ibDeleteMember;
    }
}
