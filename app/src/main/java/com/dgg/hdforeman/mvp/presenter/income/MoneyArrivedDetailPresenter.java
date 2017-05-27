package com.dgg.hdforeman.mvp.presenter.income;

import android.app.Application;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.income.MoneyArrivedDetailContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.MoneyArrivedDetail;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.schedulers.Schedulers;

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

@ActivityScope
public class MoneyArrivedDetailPresenter extends BasePresenter<MoneyArrivedDetailContract.Model, MoneyArrivedDetailContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private RxPermissions mRxPermissions;
    private ImageLoader mImageLoader;

    @Inject
    public MoneyArrivedDetailPresenter(MoneyArrivedDetailContract.Model model, MoneyArrivedDetailContract.View rootView
            , RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mRxPermissions = rxPermissions;
        this.mImageLoader = imageLoader;
    }

    public void getMoneyArrivedDetail(String id,String stid){
        mModel.getMoneyArrivedDetailData(id,stid)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.<BaseData<MoneyArrivedDetail>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseData<MoneyArrivedDetail>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData<MoneyArrivedDetail> moneyArrivedDetailBaseData) {
                        if(moneyArrivedDetailBaseData.isSuccess()){
                            mRootView.bindDAtaToView(moneyArrivedDetailBaseData.getD());
                        }else{
                            mRootView.showMessage(moneyArrivedDetailBaseData.getMsg());
                        }
                    }
                });
    }


}