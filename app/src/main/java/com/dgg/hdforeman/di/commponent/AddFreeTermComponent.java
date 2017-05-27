package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.AddFreeTermModule;
import com.dgg.hdforeman.mvp.ui.project.activity.AddFreeTermActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * author:zhangjing
 * 作用:
 * return:
 */
@ActivityScope
@Component(modules = AddFreeTermModule.class,dependencies = AppComponent.class)
public interface AddFreeTermComponent {
    void inject(AddFreeTermActivity activity);
}
