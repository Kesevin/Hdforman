package com.dgg.hdforeman.mvp.model.mine;

import android.app.Application;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.contract.mine.ModifyPassWordContract;
import com.dgg.hdforeman.mvp.model.been.ModifyPassWord;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/28.
 */

public class ModifyPassWordModel implements ModifyPassWordContract.Model{
    private Application mApplication;
    private AppComponent mAppComponent;


    public ModifyPassWordModel(HDApplication app) {
        this.mApplication = app;
        this.mAppComponent = app.getAppComponent();
    }

    @Override
    public Observable<ModifyPassWord> modifyPassWord(String oldPwd, String newPwd) {
        JSONObject json = new JSONObject();
        try {
            json.put("uspassword", oldPwd);
            json.put("nuspassword", newPwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug( json.toString());
        return mAppComponent.serviceManager().getCommonService().modifyPassWord(ApiUtil.getParams("ForemanApi","updatePassword",json));
    }
}
