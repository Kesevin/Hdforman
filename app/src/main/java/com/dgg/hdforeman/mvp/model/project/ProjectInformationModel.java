package com.dgg.hdforeman.mvp.model.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.mvp.contract.project.ProjectInformationContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ProjectService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoRequest;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;
import com.jess.arms.utils.Preconditions;

import rx.Observable;


/**
 * Created by kelvin on 2016/11/2.
 */

public class ProjectInformationModel extends BaseModel<ServiceManager, CacheManager> implements ProjectInformationContract.Model {

    private CommonService mCommonService;
    private ProjectService mProjectService;
    private CommonCache mCommonCache;
    private Gson mGson;
    private DaoSession mDaoSession;

    public ProjectInformationModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, DaoSession daoSession) {
        super(serviceManager, cacheManager);
        this.mCommonService = mServiceManager.getCommonService();
        this.mCommonCache = mCacheManager.getCommonCache();
        this.mProjectService = mServiceManager.getProjectService();
        this.mGson = gson;
        this.mDaoSession = daoSession;
    }

    @Override
    public Observable<BaseJson<ProjectInfoResponse>> getProjectInformation(@Nullable String proid) {

        BaseParams<ProjectInfoRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getProjectInfoApi.getClassName());
        params.setM(Api.RequestModule.getProjectInfoApi.getMethodName());

        ProjectInfoRequest request = new ProjectInfoRequest();
        request.setProid(proid);

        params.setD(request);

        return mCommonService.getProjectInfo(mGson.toJson(params));
    }


    @Override
    public Observable<BaseData> signProject(@Nullable String id) {

        Preconditions.checkNotNull(id);

        BaseParams<ProjectInfoRequest> params = new BaseParams<>();

        params.setC(Api.RequestModule.signProject.getClassName());
        params.setM(Api.RequestModule.signProject.getMethodName());

        ProjectInfoRequest request = new ProjectInfoRequest();
        request.setProid(id);

        params.setD(request);

        return mProjectService.signProject(mGson.toJson(params));
    }


}
