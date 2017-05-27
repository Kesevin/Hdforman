package com.dgg.hdforeman.mvp.model.project;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.project.AddFreeTermContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.AddFreeTermBean;
import com.dgg.hdforeman.mvp.model.been.AddFreeTermBeanDao;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBean;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoRequest;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.model.been.workertypeDb;
import com.dgg.hdforeman.mvp.model.been.workertypeDbDao;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;
import com.jess.arms.utils.DataHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class Addfreetermmodel extends BaseModel<ServiceManager, CacheManager> implements AddFreeTermContract.Model {
    private Gson mGson;
    private Application mApplication;
    private DaoSession daoSession;

    public Addfreetermmodel(ServiceManager serviceManager, CacheManager cacheManager, Gson mGson, Application mApplication, DaoSession daoSession) {
        super(serviceManager, cacheManager);
        this.mGson = mGson;
        this.daoSession = daoSession;
        this.mApplication = mApplication;
    }

    @Override
    public Observable<BaseJson<List<AddFreeTermBean>>> loadDeployFreeTerm() {
        BaseParams baseParams = new BaseParams();
        baseParams.setC(Api.RequestModule.deployFreeTerm.getClassName());
        baseParams.setM(Api.RequestModule.deployFreeTerm.getMethodName());
        baseParams.setD(new ArrayList<>());
        String req = mGson.toJson(baseParams);
        return mServiceManager.getCommonService().loaddepolyFreeTerm(req);
    }

    @Override
    public void savaDataInDb(List<AddFreeTermBean> beens) {
        List<workertypeDb> workertypeDbs = new ArrayList<>();
        for (int i = 0; i < beens.size(); i++) {
            workertypeDb workertype = new workertypeDb();
            workertype.setParentid(beens.get(i).getParentid());
            workertype.setWt_name(beens.get(i).getWt_name());
            workertypeDbs.add(workertype);
        }
        daoSession.getAddFreeTermBeanDao().deleteAll();
        daoSession.getWorkertypeDbDao().deleteAll();

        daoSession.getAddFreeTermBeanDao().insertInTx(beens);
        daoSession.getWorkertypeDbDao().insertInTx(workertypeDbs);
    }

    @Override
    public Observable<List<AddFreeTermBean>> getworkerType() {

        return Observable.just(daoSession.getAddFreeTermBeanDao().queryBuilder().distinct().where(AddFreeTermBeanDao.Properties.Parentid.eq(0)).list());
//        daoSession.getWorkertypeDbDao().rx().loadAll();
    }

    @Override
    public List<AddFreeTermBean> getlistbyType(String type) {
        return daoSession.getAddFreeTermBeanDao().queryBuilder().where(AddFreeTermBeanDao.Properties.Parentid.eq(type))
                .build().list();
    }

    @Override
    public Observable<BaseJson<SpaceBean>> getMessurelist(String id) {
        BaseParams<ProjectInfoRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getMessureListApi.getClassName());
        params.setM(Api.RequestModule.getMessureListApi.getMethodName());
        ProjectInfoRequest request = new ProjectInfoRequest();
        request.setProid(id);
        params.setD(request);
        return mServiceManager.getCommonService().getMessurelist(mGson.toJson(params));
    }

    @Override
    public Observable<Object[]> addFree2server(AddFreeTermBean addFreeTermBean, String pid, SpaceBean.SpacenameEntity spacenameEntity, String num) {
        FreeTermlistBean request = new FreeTermlistBean();
        request.setFreeid(addFreeTermBean.getId()+"");
        request.setPunumber(num+"");
        if (addFreeTermBean.getUg_unit()!=null){
            request.setPuugunit(addFreeTermBean.getUg_unit());
        }
        request.setSpaceid(spacenameEntity.getId() + "");
        request.setSpacename(spacenameEntity.getPe_name());
        request.setPuugname(addFreeTermBean.getUg_name());
        request.setPuugprice(addFreeTermBean.getUg_price());
        request.setUgworktypename(addFreeTermBean.getWt_name());
        request.setPu_proid(pid);
        return daoSession.getFreeTermlistBeanDao().rx().insertInTx(request);
    }

    }
