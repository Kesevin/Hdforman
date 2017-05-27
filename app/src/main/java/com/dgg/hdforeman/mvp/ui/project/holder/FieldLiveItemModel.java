package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;

import butterknife.BindView;


/**
 * Created by tsang on 2016/10/21.
 */

public class FieldLiveItemModel extends EpoxyModelWithHolder<FieldLiveItemModel.FieldLiveItemHolder> {


    @Override
    protected FieldLiveItemHolder createNewHolder() {
        return new FieldLiveItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.field_live_list_item;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    static class FieldLiveItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.image_avatar)
        ImageView imageAvatar;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.role)
        TextView role;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.starNumber)
        TextView starNumber;

        @Override
        protected void bindView(View itemView) {
            super.bindView(itemView);
        }
    }
}
