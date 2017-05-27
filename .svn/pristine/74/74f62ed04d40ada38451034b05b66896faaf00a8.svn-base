package com.dgg.hdforeman.di.module;

import com.dgg.hdforeman.mvp.contract.mine.ScoreContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.mine.MineScoreModel;
import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/11/9.
 */
@Module
public class MineScoreModule {
    private ScoreContract.View view;

    /**
     * 构建ScoreModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public MineScoreModule(ScoreContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    public ScoreContract.View provideScoreView(){
        return view;
    }

    @ActivityScope
    @Provides
    public ScoreContract.Model provideScoreModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson){
        return new MineScoreModel(serviceManager,cacheManager,gson);
    }
}
