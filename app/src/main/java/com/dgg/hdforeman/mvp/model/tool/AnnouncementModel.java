package com.dgg.hdforeman.mvp.model.tool;

import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.tool.AnnouncementContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.AnnouncementBean;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/2.
 */

public class AnnouncementModel extends BaseModel<ServiceManager,CacheManager> implements AnnouncementContract.Model{

    private ServiceManager serviceManager;
    private CacheManager cacheManager;
    private CommonService mCommonService;
    private Gson gson;
    public AnnouncementModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson) {
        super(serviceManager, cacheManager);
        this.serviceManager=serviceManager;
        this.cacheManager=cacheManager;
        this.gson=gson;
        mCommonService=serviceManager.getCommonService();
    }

    @Override
    public Observable<BaseData<List<AnnouncementBean>>> getAnnouncementListData(int pageStart,int pageSize) {
        JSONObject json=new JSONObject();
        try {
            json.put("pageStart",pageStart);
            json.put("pageSize",pageSize);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug( json.toString());
        return mCommonService.Announcement(ApiUtil.getParams("ForemanApi","selectNoticeList",json));
    }
}
