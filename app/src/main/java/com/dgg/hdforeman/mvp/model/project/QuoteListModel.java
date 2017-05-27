package com.dgg.hdforeman.mvp.model.project;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.project.QuoteListContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseParams;
import com.dgg.hdforeman.mvp.model.been.BudgetInfo;
import com.dgg.hdforeman.mvp.model.been.QuoteListInfo;
import com.dgg.hdforeman.mvp.model.been.UppacInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.mvp.BaseModel;
import com.jess.arms.utils.DataHelper;

import java.util.List;
import java.util.Map;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by jess on 29/11/2016 15:41
 * Contact with jess.yan.effort@gmail.com
 */

public class QuoteListModel extends BaseModel<ServiceManager, CacheManager> implements QuoteListContract.Model {
    private Gson mGson;
    private Application mApplication;

    public QuoteListModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
    }


    @Override
    public Observable<BaseData<List<QuoteListInfo>>> getOtherUP() {
        BaseParams params = new BaseParams();
        params.setC(Api.RequestModule.getOtherUP.getClassName());
        params.setM(Api.RequestModule.getOtherUP.getMethodName());

        return mServiceManager.getProjectService().getOtherUP(mGson.toJson(params));
    }



    @Override
    public Observable<BaseData> addBudgetSheet(String proid, String packageid, String startdate, String weekend, String remark, List<UppacInfo> infos) {
        BaseParams<BudgetInfo> params = new BaseParams<>();
        params.setC(Api.RequestModule.addBudgetSheet.getClassName());
        params.setM(Api.RequestModule.addBudgetSheet.getMethodName());

        BudgetInfo info = new BudgetInfo(proid,weekend,startdate,remark,packageid,infos);
        params.setD(info);

        return mServiceManager.getProjectService().addBudgetSheet(mGson.toJson(params));
    }

    @Override
    public List<QuoteListInfo.InlistBean> getuppacscache(String id) {
        String str=DataHelper.getStringSF(mApplication,id+"upid");
        return mGson.fromJson(str,new TypeToken<List<QuoteListInfo.InlistBean>>(){}.getType());
    }

    @Override
    public void savaupcaches(List<QuoteListInfo.InlistBean> inlistBeens, final String id) {
        Observable.just(inlistBeens).map(new Func1<List<QuoteListInfo.InlistBean>, String>() {
            @Override
            public String call(List<QuoteListInfo.InlistBean> inlistBeens) {
                return mGson.toJson(inlistBeens);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                DataHelper.SetStringSF(mApplication,id+"upid",s);
            }
        });

    }

}
