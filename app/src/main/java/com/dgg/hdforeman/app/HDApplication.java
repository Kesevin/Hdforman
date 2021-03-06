package com.dgg.hdforeman.app;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.dgg.hdforeman.BuildConfig;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerAppComponent;
import com.dgg.hdforeman.di.module.CacheModule;
import com.dgg.hdforeman.di.module.ServiceModule;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.ui.login.LoginActivity;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.http.GlobeHttpHandler;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.UiUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErroListener;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static com.jess.arms.base.BaseActivity.IS_NOT_ADD_ACTIVITY_LIST;

/**
 * Created by Rex on 2016/10/18.
 */

public class HDApplication extends BaseApplication {
    private static HDApplication mApplication;
    public static final String COOKIES = "cookies";
    private RefWatcher mRefWatcher;//leakCanary观察器
    public Long userId;
    private AppComponent mAppComponent;
    private Map<String, String> mCookie;
    public static final boolean ENCRYPTED = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(getAppModule())//baseApplication提供
                .clientModule(getClientModule())//baseApplication提供
                .imageModule(getImageModule())//baseApplication提供
                .serviceModule(new ServiceModule())//需自行创建b
                .cacheModule(new CacheModule())//需自行创建
                .build();
        mApplication = this;
        if (BuildConfig.LOG_DEBUG) {
            Timber.plant(new Timber.DebugTree());//Timber日志打印
        }

//        installLeakCanary();//leakCanary内存泄露检查

//        CrashHandler crashHandler = CrashHandler.getInstance();//崩溃抓取
//        crashHandler.init(getApplicationContext());

        JPushInterface.setDebugMode(BuildConfig.LOG_DEBUG);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush

        loadCookie();
    }

    /**
     * 从本地读取cookie
     */
    private void loadCookie() {
        Observable.just(1)
                .observeOn(Schedulers.io())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        String s = DataHelper.getStringSF(HDApplication.this, COOKIES);//取出本地的cookie设置内存

                        if (!TextUtils.isEmpty(s)) {
                            mCookie = new HashMap<>();
                            for (String cookie : s.split("&&&")) {
                                String prefix = cookie.substring(0, 5);
                                mCookie.put(prefix, cookie);
                            }
                        }
                    }
                });
    }

    /**
     * 安装leakCanary检测内存泄露
     */
    protected void installLeakCanary() {
        this.mRefWatcher = BuildConfig.USE_CANARY ? LeakCanary.install(this) : RefWatcher.DISABLED;
    }


    /**
     * 获得leakCanary观察器
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        HDApplication application = (HDApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }

    public void setCookie(Map<String, String> cookie) {
        mCookie = cookie;
    }

    @Override
    protected String getBaseUrl() {
        return Api.APP_DOMAIN;
    }

    public static HDApplication getInstance() {
        return mApplication;
    }

    public DaoSession getDaoSession() {
        return getAppComponent().daoSession();
    }

    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例, 在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return mAppComponent;
    }


    private String message = "未知错误";

    @Override
    protected GlobeHttpHandler getHttpHandler() {
        return new GlobeHttpHandler() {
            @Override
            public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {

                //这里可以先客户端一步拿到每一次http请求的结果,可以解析成json,做一些操作,如检测到token过期后
                //重新请求token,并重新执行请求
                parseCode(httpResult);


                //解析cookie
                parseCookie(response);

                //如果需要返回新的结果,则直接把response参数返回出去
                return response;
            }

            @Override
            public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {


                if (HDApplication.this.mCookie != null && HDApplication.this.mCookie.size() > 0) {
                    Request.Builder builder = chain.request().newBuilder();
                    for (String key : mCookie.keySet()) {
                        if (mCookie.get(key).startsWith("hdmsToken"))
                            builder.addHeader("Cookie", mCookie.get(key));
                    }
                    return builder
                            .build();
                }


                return request;
            }
        };
    }

    /**
     * 解析结果的状态码
     *
     * @param httpResult
     */
    private void parseCode(String httpResult) {
        try {
            if (TextUtils.isEmpty(httpResult))
                return;

            JSONObject object = new JSONObject(httpResult);
            String code = object.getString("code");
            if (code.equals(Api.LOGIN_TIME_OUT) || code.equals(Api.SIGNLE_LOGIN)) {//非法请求或者授权信息失效则跳到登陆页面
                if (object.has("msg")) {
                    message = object.getString("msg");
                }
                Observable.just(1).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                UiUtils.makeText(message);
                                HDApplication.this.setCookie(null);
                                DataHelper.removeSF(HDApplication.this, DataHelper.CURRENT_USER_ID);
                                DataHelper.removeSF(HDApplication.this, COOKIES);
                                killAll();
                                Intent intent = new Intent(HDApplication.this, LoginActivity.class);
                                intent.putExtra(IS_NOT_ADD_ACTIVITY_LIST, true);//加此字段,killAll时也不回关闭此activity
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                HDApplication.this.startActivity(intent);
                            }
                        });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析cookie
     *
     * @param response
     */
    private void parseCookie(Response response) {
        List<String> cookies = response.headers("Set-Cookie");//取出Cookies
        if (cookies != null && cookies.size() > 0) {
            if (mCookie == null)
                mCookie = new HashMap<>();
            for (int i = 0; i < cookies.size(); i++) {
                String cookie = cookies.get(i);
                String prefix = cookie.substring(0, 5);
                mCookie.put(prefix, cookie);
            }
            StringBuffer buffer = new StringBuffer();
            for (String key : mCookie.keySet()) {
                buffer.append(mCookie.get(key) + "&&&");
            }

            DataHelper.SetStringSF(HDApplication.this, COOKIES, buffer.toString());//cookie保存至本地
        }
    }


    @Override
    protected ResponseErroListener getResponseErroListener() {
        return new ResponseErroListener() {
            @Override
            public void handleResponseError(Context context, Exception e) {
                Timber.tag(TAG).w("------------>" + e.getMessage());
                UiUtils.SnackbarText("网络连接失败,请检测你的网络");
            }
        };
    }
}
