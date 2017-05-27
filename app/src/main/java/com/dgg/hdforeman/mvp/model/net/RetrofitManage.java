package com.dgg.hdforeman.mvp.model.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Rex on 2016/10/19.
 */

public class RetrofitManage {


    private static RetrofitManage service;
    private Retrofit retrofit;
    OkHttpClient client;
    Interceptor mTokenInterceptor;
    SharedPreferences sharedPreferences;
    Gson gson;

    public RetrofitManage() {
        sharedPreferences = HDApplication.getInstance().getSharedPreferences("HDCookie", Context.MODE_PRIVATE);
        gson = new Gson();
        mTokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                String token = sharedPreferences.getString("hdmsToken", "");
                if (!TextUtils.isEmpty(token)) {
                    final Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Cookie", token);
                } else {
                    //这里获取请求返回的cookie
                    if (!originalResponse.headers("Set-Cookie").isEmpty()) {

                        //保存Cookie数据
                        Observable.from(originalResponse.headers("Set-Cookie"))
                                .filter(new Func1<String, Boolean>() {
                                    @Override
                                    public Boolean call(String str) {
                                        return str.contains("hdmsToken");
                                    }
                                })
                                .map(new Func1<String, String>() {
                                    @Override
                                    public String call(String s) {
                                        String[] headArray = s.split(";");
                                        return headArray[0];
                                    }
                                })
                                .subscribe(new Action1<String>() {
                                    @Override
                                    public void call(String cookie) {
                                        CommonUtil.logDebug("hdmsToken " + cookie);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("hdmsToken", cookie);
                                        editor.commit();
                                    }
                                });
                    }

                }
                return originalResponse;
            }

        };
         client = new OkHttpClient.Builder()
                .addInterceptor(mTokenInterceptor)//添加后台自定的hdmsToken
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(new RequestIntercept())
//                .cookieJar(new CookieJar() {
//                    @Override
//                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                        if (cookies != null && cookies.size() > 0) {
//                            String jsonStr = gson.toJson(cookies);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("cookie", jsonStr);
//                            editor.commit();
//                            CommonUtil.logDebug("getCookie :" + jsonStr);
//                        }
//                    }
//
//                    @Override
//                    public List<Cookie> loadForRequest(HttpUrl url) {
//                        String token = sharedPreferences.getString("cookie", "");
//                        if (!TextUtils.isEmpty(token)) {
//                            List<Cookie> cookies = gson.fromJson(token,
//                                    new TypeToken<List<Cookie>>() {
//                                    }.getType());
//                            return cookies;
//                        }
//                        return new ArrayList<Cookie>();
//                    }
//                })
                .build();
          retrofit = new Retrofit.Builder()
                .baseUrl(Api.APP_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())// 使用Gson作为数据转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// 使用RxJava作为回调适配器
                .client(client)
                .build();

    }

    public static RetrofitManage getInstance() {
        if (null == service) {
            synchronized (RetrofitManage.class) {
                if (null == service) {
                    service = new RetrofitManage();
                }
            }
        }
        return service;
    }

    public CommonService getApi() {
        return  retrofit.create(CommonService.class);
    }
}
