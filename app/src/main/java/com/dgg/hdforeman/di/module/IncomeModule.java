package com.dgg.hdforeman.di.module;

import com.dgg.hdforeman.mvp.contract.income.IncomeContract;
import com.dgg.hdforeman.mvp.contract.mine.ScoreContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.income.IncomeModel;
import com.dgg.hdforeman.mvp.model.mine.MineScoreModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/11/11.
 */
@Module
public class IncomeModule {
    private IncomeContract.View view;

    /**
     * IncomeModule,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public IncomeModule(IncomeContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public IncomeContract.View provideIncomeView(){
        return view;
    }

    @ActivityScope
    @Provides
    public IncomeContract.Model provideIncomeModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson){
        return new IncomeModel(serviceManager,cacheManager,gson);
    }
}
