package com.dgg.hdforeman.di.module;


import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.IncomeService;
import com.dgg.hdforeman.mvp.model.api.service.ProjectService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by zhiyicx on 2016/3/30.
 */
@Module
public class ServiceModule {

    @Singleton
    @Provides
    CommonService provideCommonService(Retrofit retrofit) {
        return retrofit.create(CommonService.class);
    }

    @Singleton
    @Provides
    ProjectService provideProjectService(Retrofit retrofit) {
        return retrofit.create(ProjectService.class);
    }
    @Singleton
    @Provides
    IncomeService provideIncomeService(Retrofit retrofit) {
        return retrofit.create(IncomeService.class);
    }
}
