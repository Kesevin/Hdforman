package com.dgg.hdforeman.mvp.model.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.mvp.contract.project.ConstructProgressContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.ConstructProgress;
import com.dgg.hdforeman.mvp.model.been.ConstructProgressRequest;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoRequest;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;


/**
 * Created by kelvin on 2016/11/7.
 */

public class ConstructProgressModel extends BaseModel<ServiceManager,CacheManager> implements ConstructProgressContract.Model {

    private CommonService mCommonService;
    private CommonCache mCommonCache;
    private Gson mGson;

    public ConstructProgressModel(ServiceManager serviceManager, CacheManager cacheManager,Gson gson) {
        super(serviceManager, cacheManager);
        this.mCommonService = mServiceManager.getCommonService();
        this.mCommonCache = mCacheManager.getCommonCache();
        this.mGson = gson;
    }

    @Override
    public Observable<BaseJson<ConstructProgress.PDBean>> getConstructProgressInfo(@Nullable String proid) {
        BaseParams<ProjectInfoRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getConstructProgressInfoApi.getClassName());
        params.setM(Api.RequestModule.getConstructProgressInfoApi.getMethodName());

        ProjectInfoRequest request = new ProjectInfoRequest();
        request.setProid(proid);

        params.setD(request);

        return mCommonService.getConstructProgressInfo(mGson.toJson(params));
    }

    @Override
    public Observable<BaseJson> updateStageNode(@Nullable String id) {
        BaseParams<ConstructProgressRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.updateStageNodeApi.getClassName());
        params.setM(Api.RequestModule.updateStageNodeApi.getMethodName());

        ConstructProgressRequest request = new ConstructProgressRequest();
        request.setNodeid(id);

        params.setD(request);
        return mCommonService.updateStageNode(mGson.toJson(params));
    }

    @Override
    public Observable<BaseData> applyStartWork(String proid) {
        JSONObject json=new JSONObject();
        try {
            json.put("proid",proid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mCommonService.applyStartWork(ApiUtil.getParams(
                Api.RequestModule.applyStartWork.getClassName(),
                Api.RequestModule.applyStartWork.getMethodName(),
                json));
    }
}
