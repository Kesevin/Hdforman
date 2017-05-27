package com.dgg.hdforeman.mvp.presenter.project;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.IProjectDetailActivity;
import com.dgg.hdforeman.mvp.model.been.ProjectDetail;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;
import com.dgg.hdforeman.mvp.model.net.BaseSubscriber;
import com.dgg.hdforeman.app.utils.ToastUtils;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tsang on 2016/10/20.
 */

public class ProjectDetailPresenter extends ProjectBasePresenter implements IProjectDetailActivity.Presenter {
    private IProjectDetailActivity.View view;

    public ProjectDetailPresenter(IProjectDetailActivity.View view) {
        super();
        this.view = view;
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

    private void consumeEvent(Observable<ProjectDetail> observable) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ProjectDetail>() {
                    @Override
                    public void onEnd() {
                        view.hideLoading();
                    }

                    @Override
                    public void onNext(ProjectDetail projectDetail) {
                        if(projectDetail.getCode().equals("0")){
                            view.bindDataToUI(projectDetail);
                        }else{
                            ToastUtils.showToast(HDApplication.getInstance().getString(R.string.network_exception));
                        }

                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void getProjectDetail() {
        view.showLoading();
        consumeEvent(ApiUtil.mockGetData(ProjectDetail.class, ApiUtil.projectDetailStr));
    }
}
