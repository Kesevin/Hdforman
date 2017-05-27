package com.dgg.hdforeman.mvp.model.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.mvp.contract.project.ProjectListContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.been.ProjectListRequest;
import com.dgg.hdforeman.mvp.model.been.ProjectListResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectListResponseNew;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import rx.Observable;


/**
 * Created by kelvin on 2016/11/2.
 */

public class ProjectListModel extends BaseModel<ServiceManager,CacheManager> implements ProjectListContract.Model {

    private CommonService mCommonService;
    private CommonCache mCommonCache;
    private Gson mGson;
    private DaoSession mDaoSession;

    public ProjectListModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson,DaoSession daoSession) {
        super(serviceManager, cacheManager);
        this.mCommonService = mServiceManager.getCommonService();
        this.mCommonCache = mCacheManager.getCommonCache();
        this.mGson = gson;
        this.mDaoSession = daoSession;
    }

    @Override
    public Observable<ProjectListResponse> getProjectList(@Nullable String xmid) {
        BaseParams<ProjectListRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getProjectListApi.getClassName());
        params.setM(Api.RequestModule.getProjectListApi.getMethodName());

        ProjectListRequest request = new ProjectListRequest();
        request.setXmid(xmid);

        params.setD(request);
        return mCommonService.getProjectList(mGson.toJson(params));
    }

    @Override
    public Observable<ProjectListResponseNew> getProjectListNew(@Nullable String xmid) {
        BaseParams<ProjectListRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getProjectListApiNew.getClassName());
        params.setM(Api.RequestModule.getProjectListApiNew.getMethodName());

        ProjectListRequest request = new ProjectListRequest();
        request.setXmid(xmid);

        params.setD(request);
        return mCommonService.getProjectListNew(mGson.toJson(params));
    }
}
