package com.dgg.hdforeman.mvp.model.mine;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.SPUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.contract.mine.DataContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.MineData;
import com.dgg.hdforeman.mvp.model.been.User;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.jess.arms.utils.DataHelper;

import org.greenrobot.greendao.rx.RxDao;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;


/**
 * Created by Administrator on 2016/10/28.
 */

public class MineDataModel implements DataContract.Model{

    private HDApplication mApplication;
    private AppComponent mAppComponent;

    public MineDataModel(HDApplication app) {
        this.mApplication = app;
        this.mAppComponent = app.getAppComponent();
    }

    @Override
    public Observable<BaseData<MineData>> getMineData() {
        String usId=DataHelper.getStringSF(mApplication,SPUtil.CURRENT_USER_ID);
        JSONObject json=new JSONObject();
        try {
            json.put("usid",usId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug( json.toString());
        return mAppComponent.serviceManager().getCommonService().mineData(ApiUtil.getParams("ForemanApi","selectWokerMy",json));
    }

    @Override
    public Long getUserId() {
        Long userId=null;

        return userId;
    }
}
