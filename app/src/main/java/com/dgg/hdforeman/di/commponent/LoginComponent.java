package com.dgg.hdforeman.di.commponent;

import com.dgg.hdforeman.di.module.LoginModule;
import com.dgg.hdforeman.mvp.ui.login.LoginActivity;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by jess on 9/4/16 11:17
 * Contact with jess.yan.effort@gmail.com
 */
@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
