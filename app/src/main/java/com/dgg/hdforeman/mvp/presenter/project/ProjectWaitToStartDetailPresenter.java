package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;

import com.dgg.hdforeman.app.config.Constants;
import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.ProjectWaitToStartDetailContract;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectShutResponse;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.functions.Action0;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Rex on 2016/11/14.
 */

@ActivityScope
public class ProjectWaitToStartDetailPresenter extends BasePresenter<ProjectWaitToStartDetailContract.Model, ProjectWaitToStartDetailContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private RxPermissions mRxPermissions;
    private ImageLoader mImageLoader;
    private ProjectInfoResponse mData;


    @Inject
    public ProjectWaitToStartDetailPresenter(ProjectWaitToStartDetailContract.Model model, ProjectWaitToStartDetailContract.View rootView
            , RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mRxPermissions = rxPermissions;
        this.mImageLoader = imageLoader;
    }

    public void getData(String id) {
        mModel.getProjectInformation(id)
                .compose(RxUtils.<BaseJson<ProjectInfoResponse>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson<ProjectInfoResponse>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseJson<ProjectInfoResponse> projectInfo) {
                        mRootView.hideLoading();
                        if (projectInfo.isSuccess()) {
                            mData=projectInfo.getD();
                            mRootView.updateLayout(projectInfo.getD());//将详情数据提供给view刷新页面
                        } else {
                            mRootView.showMessage(projectInfo.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mRootView.hideLoading();
                    }
                });

    }
    public void startWork(String id) {
        mModel.StartProject(id)
                .compose(RxUtils.<BaseJson<ProjectShutResponse>>applySchedulers(mRootView))
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mRootView.hideLoading();
                    }
                })
                .subscribe(new ErrorHandleSubscriber<BaseJson<ProjectShutResponse>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseJson<ProjectShutResponse> projectState) {
                        mRootView.hideLoading();
                        if (projectState.isSuccess()) {
                            EventBus.getDefault().post(true, Constants.REFRESH_FRAGMENT);
                            mRootView.showMessage(projectState.getMsg());
                            mRootView.killMyself();
                        }else{
                            mRootView.showMessage(projectState.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mRootView.hideLoading();
                    }
                });

    }



}