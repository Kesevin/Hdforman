package com.dgg.hdforeman.mvp.presenter.income;

import android.app.Application;

import com.dgg.hdforeman.mvp.contract.income.WithdrawsCashContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
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
public class WithdrawsCashPresenter extends BasePresenter<WithdrawsCashContract.Model, WithdrawsCashContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private RxPermissions mRxPermissions;
    private ImageLoader mImageLoader;

    @Inject
    public WithdrawsCashPresenter(WithdrawsCashContract.Model model, WithdrawsCashContract.View rootView
            , RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mRxPermissions = rxPermissions;
        this.mImageLoader = imageLoader;
    }

    public void initUserDataToView(){
        mRootView.initBankDataToView(mModel.getUserData());
    }

    public void withdraw(final String money){
        mModel.withdraw(money)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<BaseData>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData baseData) {
                        if(baseData.isSuccess()){
                            mRootView.showMessage("提现成功");
                            mRootView.updateCanUseMoney(money);
                            EventBus.getDefault().post(true,"refresh");
                        }else{
                            mRootView.showMessage(baseData.getMsg());
                        }
                    }
                });
    }

}