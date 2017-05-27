package com.dgg.hdforeman.mvp.presenter.project;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.IProjectInfoFragment;
import com.dgg.hdforeman.mvp.model.been.ProjectInformation;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.dgg.hdforeman.mvp.model.net.BaseSubscriber;
import com.dgg.hdforeman.app.utils.ToastUtils;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tsang on 2016/10/25.
 */

public class ProjectInfoPresenter extends ProjectBasePresenter implements IProjectInfoFragment.Presenter {
    private IProjectInfoFragment.View view;

    public ProjectInfoPresenter(IProjectInfoFragment.View ui) {
        super();
        this.view = ui;
    }

    @Override
    public void getProjectInfo() {
//        view.showLoading();
//        getProjectInfoTest();
    }

    private void getProjectInfoTest() {
        consumeEvent(ApiUtil.mockGetData(ProjectInformation.class, ApiUtil.projectInfoStr));
    }

    @Override
    public void releaseResources() {
        compositeSubscription.clear();
        compositeSubscription = null;
        view = null;
    }

    @Override
    public void unSubscribe() {
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.clear();
        }
    }

    private void consumeEvent(Observable<ProjectInformation> observable) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ProjectInformation>() {
                    @Override
                    public void onEnd() {
                        view.hideLoading();
                    }

                    @Override
                    public void onNext(ProjectInformation projectInformation) {
                        if(projectInformation.getCode().equals("0")){
                            view.bindDataToRecyclerView(projectInformation);
                        }else{
                            ToastUtils.showToast(HDApplication.getInstance().getString(R.string.network_exception));
                        }

                    }
                });
        compositeSubscription.add(subscription);
    }
}
