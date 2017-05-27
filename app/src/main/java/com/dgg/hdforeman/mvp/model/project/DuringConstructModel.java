package com.dgg.hdforeman.mvp.model.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.project.DuringConstructContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.ProjectRequest;
import com.dgg.hdforeman.mvp.model.been.ProjectResponse;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/2.
 */

public class DuringConstructModel extends BaseModel<ServiceManager,CacheManager> implements DuringConstructContract.Model {
    private CommonService mCommonService;
    private Gson mGson;
    public static final int LIST_LIMIT_MAX = 10;

    public DuringConstructModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson) {
        super(serviceManager, cacheManager);
        this.mCommonService = serviceManager.getCommonService();
        this.mGson = gson;
    }

    @Override
    public Observable<BaseJson<List<ProjectResponse>>> qryDuringConstructList(@Nullable String protype, @Nullable int pageStart) {
        BaseParams<ProjectRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getProjectApi.getClassName());
        params.setM(Api.RequestModule.getProjectApi.getMethodName());

        ProjectRequest request = new ProjectRequest();
        request.setProtype(protype);
        request.setPageStart(pageStart);
        request.setPageSize(LIST_LIMIT_MAX);

        params.setD(request);

        return mCommonService.getList(new Gson().toJson(params));
    }

    @Override
    public Observable<BaseData> doFreeitems(String proid) {
        JSONObject json=new JSONObject();
        try {
            json.put("proid",proid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug(json.toString());
        return mCommonService.doFreeitems(ApiUtil.getParams(
                Api.RequestModule.doFreeitems.getClassName(),
                Api.RequestModule.doFreeitems.getMethodName(),
                json));
    }
}
