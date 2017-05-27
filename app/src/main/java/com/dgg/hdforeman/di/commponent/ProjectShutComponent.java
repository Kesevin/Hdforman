package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.ProjectShutModule;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectShutActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by kelvin on 2016/11/8
 *
 */
@ActivityScope
@Component(modules = ProjectShutModule.class,dependencies = AppComponent.class)
public interface ProjectShutComponent {
    void inject(ProjectShutActivity activity);
}
