package com.dgg.hdforeman.mvp.model.mine;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.contract.mine.TeamAddSearchContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.TeamAdd;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/27.
 */

public class TeamAddSearchModel implements TeamAddSearchContract.Model {

    private AppComponent mAppComponent;

    public TeamAddSearchModel(HDApplication app) {
        this.mAppComponent = app.getAppComponent();
    }

    @Override
    public Observable<BaseData<List<TeamSearchResult>>> getSearchTeamData(String searchText) {
        JSONObject json = new JSONObject();
        try {
            json.put("wk_contactno", searchText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug(json.toString());
        return mAppComponent.serviceManager().getCommonService().teamSearch(ApiUtil.getParams("ForemanApi", "seleteWorker", json));
    }

    @Override
    public Observable<TeamAdd> addTeamUser(Long grid) {
        JSONObject json = new JSONObject();
        try {
            json.put("grid", grid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug(json.toString());
        return mAppComponent.serviceManager().getCommonService().teamAdd(ApiUtil.getParams("ForemanApi", "addWorker", json));
    }

//    @Override
//    public Observable<BaseData<List<TeamSearchResult>>> getSearchTeamDataAll() {
//        return RetrofitManage.getInstance().getApi().teamSearchAll(ApiUtil.getParams("ForemanApi","selectAll",new JSONObject()));
//    }
}
