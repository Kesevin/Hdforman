package com.dgg.hdforeman.di.module;

import com.dgg.hdforeman.mvp.contract.project.FreeTemContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.project.FreeTermModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import javax.inject.Scope;

import dagger.Module;
import dagger.Provides;

/**
 * author:zhangjing
 * 作用:
 * return:
 */
@ActivityScope
@Module
public class FreeTermModule {
    private FreeTemContract.View mview;
    public FreeTermModule(FreeTemContract.View mview) {
        this.mview = mview;
    }
    @ActivityScope
    @Provides
    public FreeTemContract.View provideView(){
        return mview;
    }
    @ActivityScope
    @Provides
    public FreeTemContract.Model provideModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, DaoSession daoSession){
        return new FreeTermModel(serviceManager,cacheManager,gson,daoSession);
    }
}
