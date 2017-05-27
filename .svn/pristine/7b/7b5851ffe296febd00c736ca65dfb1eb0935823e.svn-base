package com.dgg.hdforeman.mvp.model.mine;

import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.mine.ScoreContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.ScoreBean;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Observable;


/**
 * Created by Administrator on 2016/11/9.
 */

public class MineScoreModel extends BaseModel<ServiceManager,CacheManager> implements ScoreContract.Model{

    private ServiceManager serviceManager;
    private CacheManager cacheManager;
    private CommonService mCommonService;
    private Gson gson;
    public MineScoreModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson) {
        super(serviceManager, cacheManager);
        this.serviceManager=serviceManager;
        this.cacheManager=cacheManager;
        this.mCommonService = serviceManager.getCommonService();
        this.gson=gson;
    }

    @Override
    public Observable<BaseData<List<ScoreBean>>> getScoreListData(int pageStart,int pageSize) {
        JSONObject json=new JSONObject();
        try {
            json.put("pageStart",pageStart);
            json.put("pageSize",pageSize);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug(json.toString());
        return mCommonService.MineScoreList(ApiUtil.getParams("ForemanApi","gzProjectPart",json));
    }
}
