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
import com.dgg.hdforeman.mvp.ui.project.activity.NotSignInfoActivity;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity;
import com.jess.arms.mvp.BaseView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import java.io.Serializable;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity.PROJECTINFO_DATA;
import static com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment.ITEM_DATA;

/**
 * Created by kelvin on 2016/11/9
 */

public class TypeItem2 implements ProjectContract.TabTypeItem<ProjectResponse> {

    private BaseView mBaseView;
    private HDApplication mApplication;
    private ProjectResponse mProjectResponse;
    private ImageLoader mImageLoader;

    public TypeItem2(HDApplication application, BaseView baseView) {
        this.mBaseView = baseView;
        this.mApplication = application;
        this.mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void launchActivity(Intent intent) {
        dispatch(intent);
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

    private void dispatch(final Intent intent) {
        mProjectResponse = (ProjectResponse) intent.getExtras().get(ITEM_DATA);
        String pm_state = mProjectResponse.getPm_state();
        Observable.just(pm_state)
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                })
                .map(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer state) {
                        //大于5说明工长已经接单,否则没有接单
                        return state >= 5;
                    }
                }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean b) {
                if (b) {//工长已经接单
                    mBaseView.launchActivity(intent.setClass(mApplication, NotSignInfoActivity.class));
                } else {//工长没有接单
                    launchProjectInfo();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                launchProjectInfo();
            }
        });
    }

    /**
     * 跳转到项目信息页面
     */
    private void launchProjectInfo() {
        Intent intent = new Intent(mApplication, ProjectInfoActivity.class);
        intent.putExtras(composeBundle(mProjectResponse, PROJECTINFO_DATA));
        mBaseView.launchActivity(intent);
    }


    /**
     * 填充数据到bundle
     *
     * @param data
     * @param type
     * @return
     */
    protected Bundle composeBundle(Serializable data, String type) {
        Bundle bundle = new Bundle();//用bundle封装数据
        bundle.putSerializable(type, data);
        return bundle;
    }

}
