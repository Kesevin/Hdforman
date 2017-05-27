package com.dgg.hdforeman.di.module;


import android.app.Application;

import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.been.DaoMaster;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.db.DBManager;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache.internal.RxCache;

/**
 * Created by zhiyicx on 2016/3/30.
 */
@Module
public class CacheModule {

    @Singleton
    @Provides
    CommonCache provideCommonService(RxCache rxCache) {
        return rxCache.using(CommonCache.class);
    }


    @Singleton
    @Provides
    DaoMaster.DevOpenHelper provideDevOpenHelper(Application application) {
        return new DaoMaster.DevOpenHelper(application, "dgg_home_decoration.db");
    }

    @Singleton
    @Provides
    Database provideDatabase(DaoMaster.DevOpenHelper helper) {
        return helper.getWritableDb();
    }

    @Singleton
    @Provides
    DaoSession provideDaoSession(Database database) {
        return new DaoMaster(database).newSession();
    }


    @Singleton
    @Provides
    DBManager provideDBManager(DaoSession daoSession) {
        return new DBManager(daoSession);
    }

}
