package com.dgg.hdforeman.mvp.ui.project.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ProStage;

import butterknife.BindView;

/**
 * Created by kelvin on 2016/11/7.
 */

public class ConstructPeriodChildHolder extends BaseHolder<Object> {

    private ProStage.StageItem data;
    @BindView(R.id.up_vertical_line)
    View upVerticalLine;
    @BindView(R.id.down_vertical_line)
    View downVerticalLine;
    @BindView(R.id.progress_state)
    ImageView progressState;
    @BindView(R.id.progress_sub_name)
    TextView progressSubName;
    @BindView(R.id.progress_state_finish)
    ImageView progressStateFinish;
    @BindView(R.id.sub_item_finish)
    Button subItemFinish;
    @BindView(R.id.text_finish)
    TextView textFinish;

    private Context mContext;

    public ConstructPeriodChildHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }

    @Override
    public void setData(Object mData) {
        this.data = (ProStage.StageItem) mData;
        textFinish.setVisibility(View.GONE);
        if (data.isFirst()) {
            upVerticalLine.setVisibility(View.INVISIBLE);
        } else {
            upVerticalLine.setVisibility(View.VISIBLE);
        }

        if (data.isLast()) {
            downVerticalLine.setVisibility(View.INVISIBLE);
        } else {
            downVerticalLine.setVisibility(View.VISIBLE);
        }
        progressSubName.setText(data.getSn_stname());

    }
}
