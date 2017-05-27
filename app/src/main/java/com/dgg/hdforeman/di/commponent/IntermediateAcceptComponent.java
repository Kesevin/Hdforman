package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.IntermediateAcceptModule;
import com.dgg.hdforeman.mvp.ui.project.activity.IntermediateAcceptActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by kelvin on 2016/11/8
 *
 */
@ActivityScope
@Component(modules = IntermediateAcceptModule.class,dependencies = AppComponent.class)
public interface IntermediateAcceptComponent {
    void inject(IntermediateAcceptActivity activity);
}
