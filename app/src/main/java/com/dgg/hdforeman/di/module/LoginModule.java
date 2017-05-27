package com.dgg.hdforeman.di.module;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.login.LoginContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.db.DBManager;
import com.dgg.hdforeman.mvp.model.login.LoginModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jess on 9/4/16 11:10
 * Contact with jess.yan.effort@gmail.com
 */
@Module
public class LoginModule {
    private LoginContract.View view;

    /**
     * 构建Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LoginModule(LoginContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LoginContract.View provideView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LoginContract.Model provideModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson
            , Application application , DBManager dbManager) {
        return new LoginModel(serviceManager, cacheManager, gson, application, dbManager);
    }
}
