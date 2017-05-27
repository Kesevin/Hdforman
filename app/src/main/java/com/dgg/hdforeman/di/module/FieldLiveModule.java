package com.dgg.hdforeman.di.module;

import com.dgg.hdforeman.mvp.contract.project.FieldLiveContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.project.FieldLiveModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kelvin on 2016/11/8.
 */
@Module
public class FieldLiveModule {
    private FieldLiveContract.View view;

    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public FieldLiveModule(FieldLiveContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    FieldLiveContract.View provideUserView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    FieldLiveContract.Model provideUserModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson){
        return new FieldLiveModel(serviceManager,cacheManager,gson);
    }
}
