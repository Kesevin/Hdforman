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

public class AddMemberItemModel extends EpoxyModelWithHolder<AddMemberItemModel.AddMemberItemHolder> {
    @EpoxyAttribute
    View.OnClickListener onClickListener;

    @Override
    public void bind(AddMemberItemHolder holder) {
        super.bind(holder);
        holder.ibAddMember.setOnClickListener(onClickListener);
    }

    @Override
    protected AddMemberItemHolder createNewHolder() {
        return new AddMemberItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_add_meber;
    }

    static class AddMemberItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.iv_mine)
        ImageView ivMine;
        @BindView(R.id.tv_mine_name)
        TextView tvMineName;
        @BindView(R.id.ib_add_member)
        ImageButton ibAddMember;
    }
}
