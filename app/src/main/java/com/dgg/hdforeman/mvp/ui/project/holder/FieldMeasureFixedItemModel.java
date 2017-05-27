package com.dgg.hdforeman.mvp.ui.project.holder;

import android.widget.Button;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.project.holder.FieldMeasureFixedItemModel.FieldMeasureFixedItemHolder;

import butterknife.BindView;

/**
 * Created by tsang on 2016/10/25.
 */

public class FieldMeasureFixedItemModel extends EpoxyModelWithHolder<FieldMeasureFixedItemHolder> {

    @Override
    protected FieldMeasureFixedItemHolder createNewHolder() {
        return new FieldMeasureFixedItemHolder();
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.field_measure_fixed_item;
    }

    static class FieldMeasureFixedItemHolder extends BaseEpoxyHolder {
        @BindView(R.id.area_count)
        TextView areaCount;
        @BindView(R.id.area_count_val)
        TextView areaCountVal;
        @BindView(R.id.perimeter_val)
        TextView perimeterVal;
        @BindView(R.id.calculate_rule)
        TextView calculateRule;
        @BindView(R.id.calculate_area)
        Button calculateArea;
    }
}
