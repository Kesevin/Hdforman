package com.dgg.hdforeman.mvp.model.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.mvp.contract.project.ProjectAcceptContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.ProjectAcceptResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoRequest;
import com.dgg.hdforeman.mvp.model.net.RetrofitManage;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import rx.Observable;


/**
 * Created by kelvin on 2016/11/9.
 */

public class ProjectAcceptModel extends BaseModel<ServiceManager,CacheManager> implements ProjectAcceptContract.Model {

    private CommonService mCommonService;
    private CommonCache mCommonCache;
    private Gson mGson;

    public ProjectAcceptModel(ServiceManager serviceManager, CacheManager cacheManager,Gson gson) {
        super(serviceManager, cacheManager);
        this.mCommonService = mServiceManager.getCommonService();
        this.mCommonCache = mCacheManager.getCommonCache();
        this.mGson = gson;
    }

    @Override
    public Observable<BaseJson<List<ProjectAcceptResponse>>> getProjectAcceptList(@Nullable String proid) {
        BaseParams<ProjectInfoRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getProjectAcceptListApi.getClassName());
        params.setM(Api.RequestModule.getProjectAcceptListApi.getMethodName());

        ProjectInfoRequest request = new ProjectInfoRequest();
        request.setProid(proid);

        params.setD(request);

        return mCommonService.getProjectAcceptList(mGson.toJson(params));
    }
}
