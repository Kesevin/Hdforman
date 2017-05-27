package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.FieldLiveModule;
import com.dgg.hdforeman.mvp.ui.project.activity.FieldLiveActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by kelvin on 2016/11/8
 *
 */
@ActivityScope
@Component(modules = FieldLiveModule.class,dependencies = AppComponent.class)
public interface FieldLiveComponent {
    void inject(FieldLiveActivity activity);
}
