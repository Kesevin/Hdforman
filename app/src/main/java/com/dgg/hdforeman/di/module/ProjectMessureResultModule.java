package com.dgg.hdforeman.di.module;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.project.ProjectMessureContract;
import com.dgg.hdforeman.mvp.contract.project.ProjectMessureResultContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.project.ProjectMessureModel;
import com.dgg.hdforeman.mvp.model.project.ProjectMessureResultModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * author:zhangjing
 * 作用:提供view对象和model对象
 * return:
 */
@Module
public class ProjectMessureResultModule {
    private ProjectMessureResultContract.View mrootview;
    //通过构造函数把v层传过来

    public ProjectMessureResultModule(ProjectMessureResultContract.View mrootview) {
        this.mrootview = mrootview;
    }
    @ActivityScope
    @Provides
    ProjectMessureResultContract.View provideView(){
        return this.mrootview;
    }

    @ActivityScope
    @Provides
    ProjectMessureResultContract.Model provideModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application){
        return new ProjectMessureResultModel(serviceManager,cacheManager,gson,application);
    }
}
