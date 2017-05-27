package com.dgg.hdforeman.mvp.ui.project.holder;

import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;

import butterknife.BindView;

/**
 * Created by twick on 2016/10/25.
 */

public class AddMemberTitleItemModel extends EpoxyModelWithHolder<AddMemberTitleItemModel.AddMemberItemHolder> {
    @EpoxyAttribute
    String str;

    @Override
    public void bind(AddMemberItemHolder holder) {
        super.bind(holder);
        holder.tvMembersTitle.setText(str);
    }

    @Override
    protected AddMemberItemHolder createNewHolder() {
        return new AddMemberItemHolder();
    }

    @Override
    protected int getDefaultLayout() {

        return R.layout.item_add_member_title;
    }

    static class AddMemberItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.tv_members_title)
        TextView tvMembersTitle;
    }
}
