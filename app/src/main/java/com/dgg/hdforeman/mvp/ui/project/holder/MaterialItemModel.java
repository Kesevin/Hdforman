package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;

import butterknife.BindView;


/**
 * Created by twick on 2016/10/27.
 */

public class MaterialItemModel extends EpoxyModelWithHolder<MaterialItemModel.MaterialItemHolder> {
    @EpoxyAttribute
    View.OnClickListener listener;

    @Override
    public void bind(MaterialItemHolder holder) {
        super.bind(holder);
        holder.btnDeliveryContact.setOnClickListener(listener);
    }

    @Override
    protected MaterialItemHolder createNewHolder() {
        return new MaterialItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.material_item;
    }

    static class MaterialItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.material_image)
        ImageView materialImage;
        @BindView(R.id.material_name)
        TextView materialName;
        @BindView(R.id.material_capacity)
        TextView materialCapacity;
        @BindView(R.id.material_number)
        TextView materialNumber;
        @BindView(R.id.btn_delivery_contact)
        Button btnDeliveryContact;
    }
}
