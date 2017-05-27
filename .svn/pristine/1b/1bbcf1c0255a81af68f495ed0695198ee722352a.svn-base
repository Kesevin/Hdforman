package com.dgg.hdforeman.mvp.model.project;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.project.ProjectQuoteContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBean;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBeanDao;
import com.dgg.hdforeman.mvp.model.been.PackageInfo;
import com.dgg.hdforeman.mvp.model.been.StuffInfo;
import com.dgg.hdforeman.mvp.model.been.UpgradeInfo;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import rx.Observable;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by jess on 18/11/2016 10:02
 * Contact with jess.yan.effort@gmail.com
 */

public class ProjectQuoteModel extends BaseModel<ServiceManager, CacheManager> implements ProjectQuoteContract.Model {
    private Gson mGson;
    private Application mApplication;
    private DaoSession mDaoSession;

    public ProjectQuoteModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application, DaoSession daoSession) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
        this.mDaoSession = daoSession;
    }


    @Override
    public Observable<BaseData<List<PackageInfo>>> projectPackage() {
        BaseParams params = new BaseParams();
        params.setC(Api.RequestModule.projectPackage.getClassName());
        params.setM(Api.RequestModule.projectPackage.getMethodName());

        return mServiceManager.getProjectService().projectPackage(mGson.toJson(params));
    }

    @Override
    public Observable<BaseData<UpgradeInfo>> getUpgrade() {

        BaseParams params = new BaseParams();
        params.setC(Api.RequestModule.getUpgrade.getClassName());
        params.setM(Api.RequestModule.getUpgrade.getMethodName());

        return mServiceManager.getProjectService().getUpgrade(mGson.toJson(params));
    }

    @Override
    public Observable<BaseData<List<StuffInfo>>> getProjectStuff() {

        BaseParams params = new BaseParams();
        params.setC(Api.RequestModule.getProjectStuff.getClassName());
        params.setM(Api.RequestModule.getProjectStuff.getMethodName());

        return mServiceManager.getProjectService().getProjectStuff(mGson.toJson(params));
    }

    @Override
    public Observable<List<FreeTermlistBean>> queryById(String id) {
        return Observable.just(mDaoSession.getFreeTermlistBeanDao().queryBuilder().where(FreeTermlistBeanDao.Properties.Pu_proid.eq(id)).build().list());
    }


}
