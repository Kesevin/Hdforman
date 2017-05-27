package com.dgg.hdforeman.mvp.presenter.project;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.contract.project.ProjectContract;
import com.dgg.hdforeman.mvp.model.been.MaterialInfo;
import com.dgg.hdforeman.mvp.model.been.ProjectResponse;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectCompletedDetailActivity;
import com.jess.arms.mvp.BaseView;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by kelvin on 2016/11/9
 */

public class TypeItem4 implements ProjectContract.TabTypeItem<ProjectResponse> {

    private BaseView mBaseView;
    private HDApplication mApplication;

    public TypeItem4(HDApplication application, BaseView baseView) {
        this.mBaseView = baseView;
        this.mApplication = application;
    }

    @Override
    public void launchActivity(Intent intent) {
        mBaseView.launchActivity(intent.setClass(mApplication, ProjectCompletedDetailActivity.class));
    }

    @Override
    public void initItemView(View itemView, MaterialInfo data) {
        ((TextView) itemView.findViewById(R.id.material_name)).setTextSize(TypedValue.COMPLEX_UNIT_PX, AutoUtils.getPercentHeightSize(36));
        ((TextView) itemView.findViewById(R.id.material_capacity)).setTextSize(TypedValue.COMPLEX_UNIT_PX, AutoUtils.getPercentHeightSize(36));
        ((TextView) itemView.findViewById(R.id.material_number)).setTextSize(TypedValue.COMPLEX_UNIT_PX, AutoUtils.getPercentHeightSize(36));
        itemView.findViewById(R.id.material_image).setVisibility(View.GONE);
        itemView.findViewById(R.id.btn_delivery_contact).setVisibility(View.GONE);
    }

}
