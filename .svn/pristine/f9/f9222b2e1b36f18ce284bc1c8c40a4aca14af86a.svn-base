package com.dgg.hdforeman.di.module;

import com.dgg.hdforeman.mvp.contract.project.IntermediateAcceptContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.project.IntermediateAcceptModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kelvin on 2016/11/8.
 */
@Module
public class IntermediateAcceptModule {
    private IntermediateAcceptContract.View view;

    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public IntermediateAcceptModule(IntermediateAcceptContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    IntermediateAcceptContract.View provideUserView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    IntermediateAcceptContract.Model provideUserModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson){
        return new IntermediateAcceptModel(serviceManager,cacheManager,gson);
    }
}
