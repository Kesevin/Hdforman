package com.dgg.hdforeman.di.module;

import com.dgg.hdforeman.mvp.contract.project.DuringConstructContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.project.DuringConstructModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kelvin on 2016/11/8.
 */
@Module
public class DuringConstructModule {
    private DuringConstructContract.View view;

    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public DuringConstructModule(DuringConstructContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    DuringConstructContract.View provideUserView(){
        return this.view;
    }

    @FragmentScope
    @Provides
    DuringConstructContract.Model provideUserModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson){
        return new DuringConstructModel(serviceManager,cacheManager,gson);
    }
}
