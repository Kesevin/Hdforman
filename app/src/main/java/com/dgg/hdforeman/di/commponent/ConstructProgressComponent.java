package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.ConstructProgressModule;
import com.dgg.hdforeman.di.module.ProjectInformationModule;
import com.dgg.hdforeman.mvp.ui.project.activity.ConstructProgressActivity;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectInformationActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by kelvin on 2016/11/8
 *
 */
@ActivityScope
@Component(modules = ConstructProgressModule.class,dependencies = AppComponent.class)
public interface ConstructProgressComponent {
    void inject(ConstructProgressActivity activity);
}
