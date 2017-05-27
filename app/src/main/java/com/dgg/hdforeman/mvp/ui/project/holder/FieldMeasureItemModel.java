package com.dgg.hdforeman.mvp.ui.project.holder;

import android.widget.EditText;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.project.holder.FieldMeasureItemModel.FieldMeasureItemHolder;

import butterknife.BindView;


/**
 * Created by tsang on 2016/10/25.
 */

public class FieldMeasureItemModel extends EpoxyModelWithHolder<FieldMeasureItemHolder> {
    @EpoxyAttribute
    String spaceName;

    @Override
    public void bind(FieldMeasureItemHolder holder) {
        holder.areaName.setText(spaceName);
    }

    @Override
    protected FieldMeasureItemHolder createNewHolder() {
        return new FieldMeasureItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.field_measure_item;
    }

    static class FieldMeasureItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.area_name)
        TextView areaName;
        @BindView(R.id.clear_text)
        TextView clearText;
        @BindView(R.id.calculate_text)
        TextView calculateText;
        @BindView(R.id.length)
        EditText length;
        @BindView(R.id.width)
        EditText width;
        @BindView(R.id.perimeter)
        EditText perimeter;
        @BindView(R.id.area_count)
        TextView areaCount;
        @BindView(R.id.perimeter_val)
        TextView perimeterVal;
    }
}
