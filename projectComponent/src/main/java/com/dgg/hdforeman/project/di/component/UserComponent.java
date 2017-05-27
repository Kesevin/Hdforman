package com.dgg.hdforeman.project.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;
import com.dgg.hdforeman.project.di.module.UserModule;
import com.dgg.hdforeman.project.mvp.ui.activity.UserActivity;

/**
 * Created by jess on 9/4/16 11:17
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
@Component(modules = UserModule.class,dependencies = AppComponent.class)
public interface UserComponent {
    void inject(UserActivity activity);
}
