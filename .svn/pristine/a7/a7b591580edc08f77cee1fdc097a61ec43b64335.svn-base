package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.ConstructProgressContract;
import com.dgg.hdforeman.mvp.model.been.ConstructProgressBean;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by kelvin on 2016/11/7.
 */

public class ConstructProgressHolder extends BaseHolder<ConstructProgressBean> {

    private ConstructProgressContract.View mRootView;
    private ConstructProgressBean mData;

    @BindView(R.id.progress_name)
    TextView progressName;
    @BindView(R.id.progress_date)
    TextView progressDate;
    @BindView(R.id.expand_sub_item)
    ImageView expandSubItem;
    @BindView(R.id.progress_item_container)
    AutoLinearLayout progressItemContainer;


    public ConstructProgressHolder(View itemView, ConstructProgressContract.View mRootView) {
        super(itemView);
        this.mRootView = mRootView;
    }

    @Override
    public void setData(ConstructProgressBean data) {

        this.mData = data;

        if (data.getConstructProgressTitle() != null) {
            progressName.setText(data.getConstructProgressTitle().getPs_stname() + "-" + data.getConstructProgressTitle().getPs_period() + "天");
            String date = (data.getConstructProgressTitle().getPs_startdate() != null ? data.getConstructProgressTitle().getPs_startdate().substring(5, 10) : "") + "至" + (data.getConstructProgressTitle().getPs_enddate() != null ? data.getConstructProgressTitle().getPs_enddate().substring(5, 10) : "");
            progressDate.setText(date);
            if(data.getConstructProgressTitle().getPs_state()==0){
                progressDate.setText("");
            }else if(data.getConstructProgressTitle().getPs_state()>0 && data.getConstructProgressTitle().getPs_state()<6){
                progressDate.setText(data.getConstructProgressTitle().getPs_startdate() != null ? data.getConstructProgressTitle().getPs_startdate().substring(5, 10) : "");
            }
        }

        setOpen(data);//设置是否展开  默认为不展开
    }

    private void setOpen(ConstructProgressBean data) {
        expandSubItem.setBackgroundResource(data.isExpanded() ? R.mipmap.list_btn_zhankai : R.mipmap.list_btn_weizhankai);
    }

    @OnClick(R.id.progress_item_container)
    public void onClicks(View view) {
        mData.setExpanded(!mData.isExpanded());
        mRootView.onExpandImageViewClick(mData.isExpanded(), mData);
//        setOpen();
    }
}
