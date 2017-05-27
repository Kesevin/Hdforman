package com.dgg.hdforeman.mvp.model.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.mvp.contract.project.IntermediateAcceptContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.IntermediateAcceptRequest;
import com.dgg.hdforeman.mvp.model.been.IntermediateAcceptResponse;
import com.dgg.hdforeman.mvp.model.been.InviteRequest;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import rx.Observable;


/**
 * Created by kelvin on 2016/11/8.
 */

public class IntermediateAcceptModel extends BaseModel<ServiceManager,CacheManager> implements IntermediateAcceptContract.Model {

    private CommonService mCommonService;
    private CommonCache mCommonCache;
    private Gson mGson;

    public IntermediateAcceptModel(ServiceManager serviceManager, CacheManager cacheManager,Gson gson) {
        super(serviceManager, cacheManager);
        this.mCommonService = mServiceManager.getCommonService();
        this.mCommonCache = mCacheManager.getCommonCache();
        this.mGson = gson;
    }

    @Override
    public Observable<BaseJson<IntermediateAcceptResponse>> getIntermediateAcceptList(@Nullable String proid, @Nullable String stage) {
        BaseParams<IntermediateAcceptRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getIntermediateAcceptApi.getClassName());
        params.setM(Api.RequestModule.getIntermediateAcceptApi.getMethodName());

        IntermediateAcceptRequest request = new IntermediateAcceptRequest();
        request.setProid(proid);
        request.setStage(stage);

        params.setD(request);
        return mCommonService.getIntermediateAcceptList(mGson.toJson(params));
    }

    @Override
    public Observable<BaseJson> ownerAcceptance(@Nullable List<InviteRequest.WorkBean> work, @Nullable String stageid) {
       BaseParams<InviteRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.ownerAcceptanceApi.getClassName());
        params.setM(Api.RequestModule.ownerAcceptanceApi.getMethodName());

        InviteRequest request = new InviteRequest();
        request.setStageid(stageid);
        request.setWork(work);

        params.setD(request);
        return mCommonService.ownerAcceptance(mGson.toJson(params));
    }
}
