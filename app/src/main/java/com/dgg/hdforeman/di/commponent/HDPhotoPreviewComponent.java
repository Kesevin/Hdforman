package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.HDPhotoPreviewModule;
import com.dgg.hdforeman.mvp.ui.project.activity.HDPhotoPreviewActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by kelvin on 2016/11/8
 *
 */
@ActivityScope
@Component(modules = HDPhotoPreviewModule.class,dependencies = AppComponent.class)
public interface HDPhotoPreviewComponent {
    void inject(HDPhotoPreviewActivity activity);
}
