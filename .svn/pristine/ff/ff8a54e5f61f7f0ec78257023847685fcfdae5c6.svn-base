package com.dgg.hdforeman.mvp.model.project;

import android.app.Application;
import android.support.annotation.Nullable;

import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.project.MaterialBagContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.CommonService;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.MaterialBean;
import com.dgg.hdforeman.mvp.model.been.MaterialInfo;
import com.dgg.hdforeman.mvp.model.been.MaterialRequest;
import com.dgg.hdforeman.mvp.model.been.ProjectRequest;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

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
 * Created by Rex on 2016/11/18.
 */

public class MaterialBagModel extends BaseModel<ServiceManager, CacheManager> implements MaterialBagContract.Model {
    private Gson mGson;
    private Application mApplication;
    private CommonService mCommonService;
    public MaterialBagModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
        this.mCommonService = mServiceManager.getCommonService();
    }


    @Override
    public Observable<BaseData<MaterialBean>> getMaterialsBag(@Nullable String pf_proid, int order, int type, int pageStart, int pageSize) {
        BaseParams<MaterialRequest> params = new BaseParams<>();
        params.setC(Api.RequestModule.getMaterialsApi.getClassName());
        params.setM(Api.RequestModule.getMaterialsApi.getMethodName());
        MaterialRequest request = new MaterialRequest(pf_proid,order,type,pageStart,pageSize);
        params.setD(request);
        CommonUtil.logDebug(new Gson().toJson(params));
        return mCommonService.getMaterials(new Gson().toJson(params));
    }
}