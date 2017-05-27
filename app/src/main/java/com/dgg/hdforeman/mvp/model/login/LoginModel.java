package com.dgg.hdforeman.mvp.model.login;

import android.app.Application;
import android.content.Context;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.SPUtil;
import com.dgg.hdforeman.mvp.contract.login.LoginContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.been.LoginData;
import com.dgg.hdforeman.mvp.model.been.User;
import com.dgg.hdforeman.mvp.model.been.UserDao;
import com.dgg.hdforeman.mvp.model.db.DBManager;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;
import com.jess.arms.utils.DataHelper;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by Rex on 2016/10/27.
 */

public class LoginModel extends BaseModel<ServiceManager, CacheManager> implements LoginContract.Model {

    private UserDao useDao;
    private Gson mGson;
    private CommonService mCommonService;
    private Application mApplication;

    public LoginModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application, DBManager dbManager) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mCommonService = serviceManager.getCommonService();
        this.mApplication = application;
    }

    public Observable<BaseData<LoginData>> login(String phone, String password) {
        JSONObject json = new JSONObject();
        try {
            json.put("c", "LoginApi");
            json.put("m", "appLogin");
            JSONObject jo = new JSONObject();
            jo.put("userName", phone);
            jo.put("uspassword", password);
            json.put("d", jo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug(json.toString());
        return mCommonService.login(json.toString());
    }

    @Override
    public void SaveUserInfo(HDApplication app, final User info) {
        DaoSession daoSession = app.getDaoSession();
        useDao = daoSession.getUserDao();
        Timber.tag(TAG).d("user " + info.getUsname());
        DataHelper.SetStringSF(mApplication, SPUtil.CURRENT_USER_ID, info.getUsid() + "");
        User user = useDao.queryBuilder().where(UserDao.Properties.Usid.eq(info.getUsid())).build().unique();
        if (user == null) {
            useDao.insert(info);
        } else {
            useDao.update(info);
        }
    }

    @Override
    public void savePhone(Context context, String phone) {
        SPUtil.setParam(context, "userphone", phone);
    }
}
