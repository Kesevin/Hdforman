package com.dgg.hdforeman.mvp.model.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.project.HDPhotoPreviewContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ProjectService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseData2;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.ConfirmAcceptanceRequest;
import com.dgg.hdforeman.mvp.model.been.ConfirmUploadRequest;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.been.UpLoadResponse;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;


/**
 * Created by kelvin on 2016/11/2.
 */

public class HDPhotoPreviewModel extends BaseModel<ServiceManager, CacheManager> implements HDPhotoPreviewContract.Model {

    private CommonService mCommonService;
    private ProjectService mProjectService;
    private CommonCache mCommonCache;
    private Gson mGson;
    private DaoSession mDaoSession;

    public HDPhotoPreviewModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, DaoSession daoSession) {
        super(serviceManager, cacheManager);
        this.mCommonService = mServiceManager.getCommonService();
        this.mCommonCache = mCacheManager.getCommonCache();
        this.mProjectService = mServiceManager.getProjectService();
        this.mGson = gson;
        this.mDaoSession = daoSession;
    }

    @Override
    public Observable<BaseData2<UpLoadResponse>> upLoadImg(@Nullable String picName) {
        File file = new File(picName);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        builder.addFormDataPart("file", file.getName(), requestBody);
        MultipartBody multipartBody = builder.build();
        return mCommonService.upLoadImg(file.getName(),multipartBody);
    }

    @Override
    public Observable<BaseData<String>> confirmUpload(@Nullable String projectId, String pictureIds) {
        BaseParams<ConfirmUploadRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getUpLoadApi.getClassName());
        params.setM(Api.RequestModule.getUpLoadApi.getMethodName());
        ConfirmUploadRequest request = new ConfirmUploadRequest(projectId,pictureIds);
        params.setD(request);
        CommonUtil.logDebug(new Gson().toJson(params));
        return mCommonService.confirmUpLoad(new Gson().toJson(params));
    }

    @Override
    public Observable<BaseData<String>> uploadDecorationPermit(@Nullable String projectId, String pictureIds) {
        BaseParams<ConfirmUploadRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getDecorationPermit.getClassName());
        params.setM(Api.RequestModule.getDecorationPermit.getMethodName());
        ConfirmUploadRequest request = new ConfirmUploadRequest(projectId,pictureIds);
        params.setD(request);
        CommonUtil.logDebug(new Gson().toJson(params));
        return mCommonService.upLoadDecorationPermit(new Gson().toJson(params));
    }

    @Override
    public Observable<BaseData<String>> confirmAcceptance(@Nullable String proid , String stage ,String pictureIds ) {
        BaseParams<ConfirmAcceptanceRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getAcceptanceCheckApi.getClassName());
        params.setM(Api.RequestModule.getAcceptanceCheckApi.getMethodName());
        ConfirmAcceptanceRequest request = new ConfirmAcceptanceRequest(proid,stage,pictureIds);
        params.setD(request);
        CommonUtil.logDebug(new Gson().toJson(params));
        return mCommonService.confirmAcceptance(new Gson().toJson(params));
    }
}
