package com.dgg.hdforeman.mvp.model.api.service;

import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.Income;
import com.dgg.hdforeman.mvp.model.been.IncomeDetail;
import com.dgg.hdforeman.mvp.model.been.IncomeWaitForPayment;
import com.dgg.hdforeman.mvp.model.been.MoneyArrivedDetail;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

;

/**
 * 接口
 */
public interface IncomeService {

    /**
     * 收入主页
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<Income>> getIncome(@Field("p") String params);

    /**
     * 待到款
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<List<IncomeWaitForPayment>>> getWaitForPaymentData(@Field("p") String params);

    /**
     * 已到款
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<List<IncomeWaitForPayment>>> getMoneyArrivedData(@Field("p") String params);

    /**
     * 增扣款
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<List<IncomeWaitForPayment>>> getChargebackData(@Field("p") String params);

    /**
     * 到款信息
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<MoneyArrivedDetail>> getMoneyArrivedDetail(@Field("p") String params);

    /**
     * 提现
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData> withdraw(@Field("p") String params);

    /**
     * 收入明细
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST(Api.URL)
    Observable<BaseData<List<IncomeDetail>>> getIncomeDetail(@Field("p") String params);
}
