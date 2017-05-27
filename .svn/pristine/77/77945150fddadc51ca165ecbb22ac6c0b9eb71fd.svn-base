package com.dgg.hdforeman.di.module;

import com.dgg.hdforeman.mvp.contract.project.ProjectAcceptContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.project.ProjectAcceptModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kelvin on 2016/11/8.
 */
@Module
public class ProjectAcceptModule {
    private ProjectAcceptContract.View view;

    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public ProjectAcceptModule(ProjectAcceptContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ProjectAcceptContract.View provideUserView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    ProjectAcceptContract.Model provideUserModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson){
        return new ProjectAcceptModel(serviceManager,cacheManager,gson);
    }
}
