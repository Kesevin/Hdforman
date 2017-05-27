package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.ConstructTeamContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.ConstructTeam;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.schedulers.Schedulers;

/**
 * Created by kelvin on 2016/11/2.
 */
@ActivityScope
public class ConstructTeamPresenter extends BasePresenter<ConstructTeamContract.Model, ConstructTeamContract.View> {

    private List<ConstructTeamBean> mDatas = new ArrayList<>();
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private RxPermissions mRxPermissions;
    private ImageLoader mImageLoader;
    private int pageNum=0;

    @Inject
    public ConstructTeamPresenter(ConstructTeamContract.Model model, ConstructTeamContract.View view
            , RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader) {
        super(model, view);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mRxPermissions = rxPermissions;
        this.mImageLoader = imageLoader;
    }

    public void requestList(String proId){
        mModel.getConstructTeamList(proId)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.<BaseData<List<ConstructTeam>>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseData<List<ConstructTeam>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData<List<ConstructTeam>> listBaseData) {
                        if(listBaseData.isSuccess()){
                            mRootView.bindDataToView(mModel.getConstructTeamListData(listBaseData.getD()));
                        }else{
                            mRootView.showMessage(listBaseData.getMsg());
                        }
                    }
                });
    }


    /**
     * 删除施工团队成员
     * @param proid
     * @param constructTeamBean
     */
    public void deleteWorker(String proid, final ConstructTeamBean constructTeamBean){
        mModel.deleteTeamMember(proid,constructTeamBean)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.<BaseData>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseData>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData baseData) {
                        if(baseData.isSuccess()){
                            mRootView.showMessage("删除成功");
                            mRootView.refreshAdapter(constructTeamBean);
                            EventBus.getDefault().post(true,"refreshTeamMember");
                        }else{
                            mRootView.showMessage(baseData.getMsg());
                        }
                    }
                });
    }
}
