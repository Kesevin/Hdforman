package com.dgg.hdforeman.mvp.presenter.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.project.ProjectContract;
import com.dgg.hdforeman.mvp.model.been.MaterialInfo;
import com.dgg.hdforeman.mvp.model.been.ProjectResponse;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectInformationActivity;
import com.jess.arms.mvp.BaseView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

/**
 * Created by kelvin on 2016/11/9
 */

public class TypeItem1 implements ProjectContract.TabTypeItem<ProjectResponse> {

    private BaseView mBaseView;
    private HDApplication mApplication;
    private ImageLoader mImageLoader;

    public TypeItem1(HDApplication application,BaseView baseView) {
        this.mBaseView = baseView;
        this.mApplication = application;
        this.mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void launchActivity(Intent intent) {
        mBaseView.launchActivity(intent.setClass(mApplication,ProjectInformationActivity.class));
    }

    @Override
    public void initItemView(View itemView, MaterialInfo data) {
        ImageView materialImage = (ImageView) itemView.findViewById(R.id.material_image);
        materialImage.setVisibility(View.VISIBLE);
        itemView.findViewById(R.id.btn_delivery_contact).setVisibility(View.VISIBLE);
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(CommonUtil.getImageUrl(data.getAccessory(), 150, 150))
                .imagerView(materialImage)
                .errorPic(R.mipmap.default_project_icon)
                .build());
    }


}
