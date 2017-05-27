package com.dgg.hdforeman.mvp.ui.project.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.SimpleEpoxyModel;

/**
 * Created by tsang on 2016/10/24.
 */

public class SimpleTextModel extends SimpleEpoxyModel {
    @EpoxyAttribute
    String text;

    public SimpleTextModel(String content) {
        super(android.R.layout.simple_list_item_1);
        text = content;
    }

    @Override
    public void bind(View view) {
        super.bind(view);
        TextView textView = (TextView) view;
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }
}
