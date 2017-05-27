package com.dgg.hdforeman.mvp.model.income;

import com.dgg.hdforeman.mvp.contract.income.IncomeContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.Income;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import org.json.JSONObject;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/11.
 */

public class IncomeModel extends BaseModel<ServiceManager,CacheManager> implements IncomeContract.Model{
    private ServiceManager serviceManager;
    private CacheManager cacheManager;
    private Gson gson;
    public IncomeModel(ServiceManager serviceManager, CacheManager cacheManager,Gson gson) {
        super(serviceManager, cacheManager);
        this.serviceManager=serviceManager;
        this.cacheManager=cacheManager;
        this.gson=gson;
    }

    @Override
    public Observable<BaseData<Income>> getIncomeData() {
        return serviceManager.getIncomeService().getIncome(ApiUtil.getParams("ForemanApi","gzIncome",new JSONObject()));
    }
}
