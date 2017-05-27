package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.ConstructProgressContract;
import com.dgg.hdforeman.mvp.model.been.ConstructProgressBean;
import com.dgg.hdforeman.mvp.model.been.ProStage;
import com.dgg.hdforeman.mvp.ui.project.adapter.ConstructionPeriodAdapter;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kelvin on 2016/11/7.
 */

public class ConstructPeriodParentHolder extends BaseHolder<Object> {

    private ConstructionPeriodAdapter.OnItemClickListener mOnItemClickListener;
    private ProStage mData;

    @BindView(R.id.progress_name)
    TextView progressName;
    @BindView(R.id.progress_date)
    TextView progressDate;
    @BindView(R.id.expand_sub_item)
    ImageView expandSubItem;
    @BindView(R.id.progress_item_container)
    AutoLinearLayout progressItemContainer;


    public ConstructPeriodParentHolder(View itemView, ConstructionPeriodAdapter.OnItemClickListener mOnItemClickListener) {
        super(itemView);
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public void setData(Object data) {

        this.mData =(ProStage) data;
        progressDate.setVisibility(View.INVISIBLE);
            progressName.setText(mData.getPs_stname() + "-" + mData.getPs_period() + "天");

    expandSubItem.setBackgroundResource(mData.isExpand() ? R.mipmap.list_btn_zhankai : R.mipmap.list_btn_weizhankai);
    }

    @OnClick(R.id.progress_item_container)
    public void onClicks(View view) {
        if(mOnItemClickListener!=null)
        mOnItemClickListener.onItem(view,getLayoutPosition(),mData);
    }
}
