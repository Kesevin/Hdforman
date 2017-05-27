package com.dgg.hdforeman.mvp.model.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.project.FieldLiveContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.LiveBean;
import com.dgg.hdforeman.mvp.model.been.LiveRequest;
import com.google.gson.Gson;

import java.util.List;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/8.
 */

public class FieldLiveModel extends com.jess.arms.mvp.BaseModel<ServiceManager,CacheManager> implements FieldLiveContract.Model {
    private CommonService mCommonService;
    private CommonCache mCommonCache;
    private Gson mGson;

    public FieldLiveModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson) {
        super(serviceManager, cacheManager);
        this.mCommonService = mServiceManager.getCommonService();
        this.mCommonCache = mCacheManager.getCommonCache();
        this.mGson = gson;
    }

    @Override
    public Observable<BaseData<List<LiveBean>>> getSiteLiveData(@Nullable String proid, int pageStart, int pageSize) {
        BaseParams<LiveRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getSiteLive.getClassName());
        params.setM(Api.RequestModule.getSiteLive.getMethodName());
        LiveRequest request = new LiveRequest(proid,pageStart,pageSize);
        params.setD(request);
        CommonUtil.logDebug(new Gson().toJson(params));
        return mCommonService.getSiteLive(new Gson().toJson(params));
    }
}
