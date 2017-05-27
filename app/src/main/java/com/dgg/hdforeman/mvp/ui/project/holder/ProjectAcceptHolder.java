package com.dgg.hdforeman.mvp.ui.project.holder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.model.been.ProjectAcceptResponse;

import butterknife.BindView;

/**
 * Created by kelvin on 2016/11/11.
 */

public class ProjectAcceptHolder extends BaseHolder<ProjectAcceptResponse> {

    private final HDApplication mApplication;

    @Nullable
    @BindView(R.id.ProjectAcceptTitle)
    TextView mTitle;
    @Nullable
    @BindView(R.id.ProjectAcceptContent)
    TextView mContent;

    public ProjectAcceptHolder(View itemView) {
        super(itemView);
        mApplication = (HDApplication) itemView.getContext().getApplicationContext();
    }

    @Override
    public void setData(ProjectAcceptResponse data) {
        mTitle.setText(data.getPs_stname());

        setState(data);

    }

    private void setState(ProjectAcceptResponse data){
        //阶段状态(默认0 未完工  1施工中 2停工中 3待验收 4验收中 5待付款 6已付款(完工))
        mContent.setBackgroundResource(R.drawable.text_red_shape);
        mContent.setTextColor(mApplication.getResources().getColor(R.color.textRedColor));
        if(data.getPs_state() == 1){
            mContent.setText("施工中");
        }else if(data.getPs_state()==2){
            mContent.setText("停工中");
        }else if(data.getPs_state()==3){
            mContent.setText("待验收");
            mContent.setBackgroundResource(R.drawable.text_green_shape);
            mContent.setTextColor(mApplication.getResources().getColor(R.color.textGreenColor));
        }else if(data.getPs_state() == 4){
            mContent.setText("验收中");
            mContent.setBackgroundResource(R.drawable.text_green_shape);
            mContent.setTextColor(mApplication.getResources().getColor(R.color.textGreenColor));
//            mContent.setBackgroundResource(R.drawable.text_blue_shape);
//            mContent.setTextColor(mApplication.getResources().getColor(R.color.colorBlue));
        }else if(data.getPs_state() == 5 ){
            mContent.setText("待付款");
            mContent.setBackgroundResource(R.drawable.text_green_shape);
            mContent.setTextColor(mApplication.getResources().getColor(R.color.textGreenColor));
        }else if(data.getPs_state() == 6 ){
            mContent.setBackgroundResource(R.drawable.text_gray_shape);
            mContent.setTextColor(mApplication.getResources().getColor(R.color.gray_BABABA));
            mContent.setText("已付款");
        }else{
            mContent.setText("未完工");
        }
    }
}
