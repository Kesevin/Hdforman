package com.dgg.hdforeman.mvp.model.project;

import com.dgg.hdforeman.mvp.contract.project.FreeTemContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.cache.CommonCache;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBean;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBeanDao;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoRequest;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**AP
 * author:zhangjing
 * 作用:
 * return:
 */

public class FreeTermModel extends BaseModel<ServiceManager,CacheManager> implements FreeTemContract.Model{
    private CommonService mCommonService;
    private CommonCache mCommonCache;
    private Gson mGson;
    private DaoSession daoSession;
    public FreeTermModel(ServiceManager serviceManager, CacheManager cacheManager, Gson mGson, DaoSession daoSession) {
        super(serviceManager, cacheManager);
        this.mCommonService = serviceManager.getCommonService();
        this.mCommonCache = cacheManager.getCommonCache();
        this.mGson = mGson;
        this.daoSession=daoSession;
    }

    @Override
    public Observable<BaseJson<List<FreeTermlistBean>>> getFreeTerms(String id) {
        BaseParams<ProjectInfoRequest> baseParams=new BaseParams<>();
        baseParams.setC(Api.RequestModule.getFreeTermlist.getClassName());
        baseParams.setM(Api.RequestModule.getFreeTermlist.getMethodName());
        ProjectInfoRequest projectInfoRequest=new ProjectInfoRequest();
        projectInfoRequest.setProid(id);
        baseParams.setD(projectInfoRequest);
        return mCommonService.getFreeTermsData(mGson.toJson(baseParams));
    }


    @Override
    public Observable<Void> delFreeTerm(FreeTermlistBean freeTermlistBean) {
        return daoSession.getFreeTermlistBeanDao().rx().delete(freeTermlistBean);
    }

    @Override
    public Observable<FreeTermlistBean> update(FreeTermlistBean freeTermlistBean) {
        return daoSession.getFreeTermlistBeanDao().rx().update(freeTermlistBean);
    }

    @Override
    public Observable<List<FreeTermlistBean>> queryById(String id) {
        QueryBuilder.LOG_VALUES = true;
        QueryBuilder.LOG_SQL = true;
        return Observable.just(daoSession.getFreeTermlistBeanDao().queryBuilder().where(FreeTermlistBeanDao.Properties.Pu_proid.eq(id)).build().list());

    }

    public Observable<List<FreeTermlistBean>> queryAllFree(){
        return daoSession.getFreeTermlistBeanDao().rx().loadAll();
    }

    @Override
    public Observable<Iterable<FreeTermlistBean>> insertdatas(List<FreeTermlistBean> freeTermlistBeens) {
        return daoSession.getFreeTermlistBeanDao().rx().insertInTx(freeTermlistBeens);
    }

    public Observable<Void> delall(){
        return daoSession.getFreeTermlistBeanDao().rx().deleteAll();
    }
}
