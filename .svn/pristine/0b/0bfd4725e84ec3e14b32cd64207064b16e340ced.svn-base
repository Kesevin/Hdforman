package com.dgg.hdforeman.mvp.model.project;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.project.ProjectMessureContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoRequest;
import com.dgg.hdforeman.mvp.model.been.RequestSpaceBean;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import rx.Observable;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class ProjectMessureModel extends BaseModel<ServiceManager, CacheManager> implements ProjectMessureContract.Model {
    private Gson mGson;
    private Application mApplication;

    public ProjectMessureModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
    }


    @Override
    public float calculateArea(float i, float j) {
        return i*j;
    }

    @Override
    public float calculateCircumference(float i, float j) {
        return 2*(i+j);
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
    public Observable<BaseJson<String>> addSpace(RequestSpaceBean requestSpaceBean) {
        BaseParams<RequestSpaceBean> params = new BaseParams<>();
        params.setC(Api.RequestModule.addSpaceApi.getClassName());
        params.setM(Api.RequestModule.addSpaceApi.getMethodName());
        params.setD(requestSpaceBean);
        return mServiceManager.getCommonService().addSpace(mGson.toJson(params));
    }

}

