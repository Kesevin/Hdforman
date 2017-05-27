package com.dgg.hdforeman.mvp.model.mine;

import android.app.Application;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.contract.mine.TeamAddContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/31.
 */

public class TeamAddModel implements TeamAddContract.Model {
    private Application mApplication;
    private AppComponent mAppComponent;


    public TeamAddModel(HDApplication app) {
        this.mApplication = app;
        this.mAppComponent = app.getAppComponent();
    }

    @Override
    public Observable<BaseData<List<TeamSearchResult>>> getTeamNoForemanData(int pageStart, int pageSize) {
        JSONObject json = new JSONObject();
        try {
            json.put("pageStart", pageStart);
            json.put("pageSize", pageSize);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug(json.toString());
        return mAppComponent.serviceManager().getCommonService().teamNoForeman(ApiUtil.getParams("ForemanApi", "selectAll", json));
    }
}
