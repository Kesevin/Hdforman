package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.FreeTermModule;
import com.dgg.hdforeman.mvp.ui.project.activity.FreeTemActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * author:zhangjing
 * 作用:
 * return:
 */
@ActivityScope
@Component(modules = FreeTermModule.class,dependencies = AppComponent.class)
public interface FreeTermComponent {
    void inject(FreeTemActivity freeTemActivity);
}
