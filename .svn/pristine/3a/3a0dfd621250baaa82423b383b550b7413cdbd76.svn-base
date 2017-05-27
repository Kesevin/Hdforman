package com.dgg.hdforeman.di.commponent;

import android.app.Application;

import com.dgg.hdforeman.di.module.CacheModule;
import com.dgg.hdforeman.di.module.ServiceModule;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.db.DBManager;
import com.google.gson.Gson;
import com.jess.arms.di.module.AppModule;
import com.jess.arms.di.module.ClientModule;
import com.jess.arms.di.module.ImageModule;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Singleton;

import dagger.Component;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.OkHttpClient;

/**
 * Created by kelvin on 2016/11/8.
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ServiceModule.class, ImageModule.class, CacheModule.class})
public interface AppComponent {
    Application Application();

    //服务管理器,retrofitApi

    ServiceManager serviceManager();

    //缓存管理器

    CacheManager cacheManager();
    //Rxjava错误处理管理类
    RxErrorHandler rxErrorHandler();

    OkHttpClient okHttpClient();

    //用于请求权限,适配6.0的权限管理
    RxPermissions rxPermissions();

    //图片管理器,用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    ImageLoader imageLoader();

    //gson
    Gson gson();

    //greenDao
    DaoSession daoSession();


    DBManager dbManager();

}
