package com.dgg.hdforeman.mvp.contract.login;

import android.content.Context;
import android.support.annotation.UiThread;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.LoginData;
import com.dgg.hdforeman.mvp.model.been.User;
import com.jess.arms.mvp.BaseView;

import rx.Observable;


public interface LoginContract {

    interface View extends BaseView {
        @UiThread
        String getPhone();//获取手机号
        @UiThread
        String getPassWord();//获取密码

        @UiThread
        void rememberPhone( String phone);//记住手机号

    }

    interface Model {
      public Observable<BaseData<LoginData>> login(String phone, String password);
        public  void SaveUserInfo(HDApplication app, User info);
        public void   savePhone(Context context, String phone);
    }
}
