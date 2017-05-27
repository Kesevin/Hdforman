package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.ProjectAcceptModule;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectAcceptActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by kelvin on 2016/11/8
 *
 */
@ActivityScope
@Component(modules = ProjectAcceptModule.class,dependencies = AppComponent.class)
public interface ProjectAcceptComponent {
    void inject(ProjectAcceptActivity activity);
}
