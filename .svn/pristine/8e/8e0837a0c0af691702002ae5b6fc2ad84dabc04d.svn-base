package com.dgg.hdforeman.mvp.model.project;


import android.support.annotation.Nullable;

import com.dgg.hdforeman.mvp.contract.project.ProjectShutContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.ProjectShutRequest;
import com.dgg.hdforeman.mvp.model.been.ProjectShutResponse;
import com.dgg.hdforeman.mvp.model.net.RetrofitManage;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import rx.Observable;


/**
 * Created by kelvin on 2016/11/2.
 */

public class ProjectShutModel extends BaseModel<ServiceManager,CacheManager> implements ProjectShutContract.Model {

    private CommonService mCommonService;
    private CommonCache mCommonCache;
    private Gson mGson;

    public ProjectShutModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson) {
        super(serviceManager, cacheManager);
        this.mCommonService = mServiceManager.getCommonService();
        this.mCommonCache = mCacheManager.getCommonCache();
        this.mGson = gson;
    }

    @Override
    public Observable<BaseJson> projectShut(@Nullable String proid, @Nullable String reason) {
        BaseParams<ProjectShutRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.projectShutApi.getClassName());
        params.setM(Api.RequestModule.projectShutApi.getMethodName());

        ProjectShutRequest request = new ProjectShutRequest();
        request.setProid(proid);
        request.setReason(reason);

        params.setD(request);

        return mCommonService.projectShut(mGson.toJson(params));
    }
}
