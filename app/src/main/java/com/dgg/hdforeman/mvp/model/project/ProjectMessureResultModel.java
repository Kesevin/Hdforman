package com.dgg.hdforeman.mvp.model.project;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.project.ProjectMessureResultContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoRequest;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;
import com.jess.arms.utils.Preconditions;

import rx.Observable;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class ProjectMessureResultModel extends BaseModel<ServiceManager,CacheManager>implements ProjectMessureResultContract.Model{
    private Gson mGson;
    private Application mApplication;

    public ProjectMessureResultModel(ServiceManager serviceManager, CacheManager cacheManager, Gson mGson,Application application) {
       super(serviceManager, cacheManager);
        this.mGson = mGson;
        this.mApplication=application;
        this.mApplication = application;
    }

    @Override
    public Observable<BaseJson<SpaceBean>> getMessurelist(String id) {
        Preconditions.checkNotNull(id);
        BaseParams<ProjectInfoRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getMessureListApi.getClassName());
        params.setM(Api.RequestModule.getMessureListApi.getMethodName());
        ProjectInfoRequest request = new ProjectInfoRequest();
        request.setProid(id);
        params.setD(request);
        return mServiceManager.getCommonService().getMessurelist(mGson.toJson(params));
    }
}
