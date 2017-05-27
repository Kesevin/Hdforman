package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.ProjectInformationModule;
import com.dgg.hdforeman.mvp.ui.project.activity.NotSignInfoActivity;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectCompletedDetailActivity;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectInformationActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by kelvin on 2016/11/8
 *
 */
@ActivityScope
@Component(modules = ProjectInformationModule.class,dependencies = AppComponent.class)
public interface ProjectInformationComponent {
    void inject(ProjectInformationActivity activity);
    void inject(ProjectCompletedDetailActivity activity);
    void inject(NotSignInfoActivity activity);
    void inject(ProjectInfoActivity activity);
}
