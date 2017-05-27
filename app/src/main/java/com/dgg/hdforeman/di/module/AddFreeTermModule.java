package com.dgg.hdforeman.di.module;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.project.AddFreeTermContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.project.Addfreetermmodel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * author:zhangjing
 * 作用:
 * return:
 */
@ActivityScope
@Module
public class AddFreeTermModule {
    private AddFreeTermContract.View view;

    public AddFreeTermModule(AddFreeTermContract.View view) {
        this.view = view;
    }
    @ActivityScope
    @Provides
    public AddFreeTermContract.View provideview(){
        return this.view;
    }
    @ActivityScope
    @Provides
    public AddFreeTermContract.Model provideModel(ServiceManager serviceManager, CacheManager cacheManager
            , Gson gson, Application application, DaoSession daoSession){
        return new Addfreetermmodel(serviceManager,cacheManager,gson,application,daoSession);
    }
}
