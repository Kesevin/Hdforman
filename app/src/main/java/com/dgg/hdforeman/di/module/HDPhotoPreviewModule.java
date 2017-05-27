package com.dgg.hdforeman.di.module;

import com.dgg.hdforeman.mvp.contract.project.HDPhotoPreviewContract;
import com.dgg.hdforeman.mvp.contract.project.ProjectInformationContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.project.HDPhotoPreviewModel;
import com.dgg.hdforeman.mvp.model.project.ProjectInformationModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kelvin on 2016/11/8.
 */
@Module
public class HDPhotoPreviewModule {
    private HDPhotoPreviewContract.View view;

    /**
     * 构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public HDPhotoPreviewModule(HDPhotoPreviewContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    HDPhotoPreviewContract.View provideUserView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    HDPhotoPreviewContract.Model provideUserModel(ServiceManager serviceManager
            , CacheManager cacheManager, Gson gson, DaoSession daoSession){
        return new HDPhotoPreviewModel(serviceManager,cacheManager,gson,daoSession);
    }
}