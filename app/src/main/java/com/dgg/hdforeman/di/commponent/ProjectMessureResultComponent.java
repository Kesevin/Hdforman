package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.ProjectMessureResultModule;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectMessureResultActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * author:zhangjing
 * 作用:
 * return:
 */
@ActivityScope
@Component(modules = ProjectMessureResultModule.class, dependencies = AppComponent.class)
public interface ProjectMessureResultComponent {
    void inject(ProjectMessureResultActivity activity);
}
