package com.dgg.hdforeman.di.module;

import com.dgg.hdforeman.mvp.contract.tool.AnnouncementContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.tool.AnnouncementModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/11/9.
 */
@Module
public class AnnouncementModule {
    private AnnouncementContract.View view;

    /**
     * 构建AnnouncementModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public AnnouncementModule(AnnouncementContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public AnnouncementContract.View provideAnnouncementView(){
        return view;
    }

    @ActivityScope
    @Provides
    public AnnouncementContract.Model provideAnnouncementModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson){
        return new AnnouncementModel(serviceManager,cacheManager,gson);
    }

}
