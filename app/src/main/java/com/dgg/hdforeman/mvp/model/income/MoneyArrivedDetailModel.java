package com.dgg.hdforeman.mvp.model.income;

import android.app.Application;

import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.income.MoneyArrivedDetailContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.MoneyArrivedDetail;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;

import org.json.JSONException;
import org.json.JSONObject;

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
 * Created by Administrator on 2016/11/17.
 */

public class MoneyArrivedDetailModel extends BaseModel<ServiceManager, CacheManager> implements MoneyArrivedDetailContract.Model {
    private Gson mGson;
    private Application mApplication;

    public MoneyArrivedDetailModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = application;
    }


    @Override
    public Observable<BaseData<MoneyArrivedDetail>> getMoneyArrivedDetailData(String id, String stid) {
        JSONObject json=new JSONObject();
        try {
            json.put("id",id);
            json.put("stid",stid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CommonUtil.logDebug(json.toString());
        return mServiceManager.getIncomeService().getMoneyArrivedDetail(
                ApiUtil.getParams(Api.RequestModule.getMoneyArrivedDetail.getClassName(),
                Api.RequestModule.getMoneyArrivedDetail.getMethodName(), json));
    }
}