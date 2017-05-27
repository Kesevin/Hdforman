package com.dgg.hdforeman.mvp.model.api.service;

import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BudgetSheetBean;
import com.dgg.hdforeman.mvp.model.been.MessageBean;
import com.dgg.hdforeman.mvp.model.been.PackageInfo;
import com.dgg.hdforeman.mvp.model.been.QuoteListInfo;
import com.dgg.hdforeman.mvp.model.been.StuffInfo;
import com.dgg.hdforeman.mvp.model.been.UpgradeInfo;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jess on 16/11/2016 15:49
 * Contact with jess.yan.effort@gmail.com
 */

public interface ProjectService {

    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData> signProject(@Field("p") String params);

    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<List<PackageInfo>>> projectPackage(@Field("p") String params);


    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<UpgradeInfo>> getUpgrade(@Field("p") String params);

    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<List<StuffInfo>>> getProjectStuff(@Field("p") String params);

    /**
     * 查看预算单
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<BudgetSheetBean>> getBudgetSheet(@Field("p") String params);

    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<List<QuoteListInfo>>> getOtherUP(@Field("p") String params);

    /**
     * 施工消息
     */
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<List<MessageBean>>> getProMessage(@Field("p") String params);
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData> addBudgetSheet(@Field("p") String params);


}
