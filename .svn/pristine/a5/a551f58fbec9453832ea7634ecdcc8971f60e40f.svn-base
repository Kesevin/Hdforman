package com.dgg.hdforeman.mvp.presenter.project;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.IConstructionProgressActivity;
import com.dgg.hdforeman.mvp.model.been.ConstructProgress;
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

public class ConstructionProgressPresenterElse extends ProjectBasePresenter implements IConstructionProgressActivity.Presenter {
    private IConstructionProgressActivity.View view;

    public ConstructionProgressPresenterElse(IConstructionProgressActivity.View ui) {
        super();
        this.view = ui;
    }


    @Override
    public void getData() {
        view.showLoading();
        consumeEvent(ApiUtil.mockGetData(ConstructProgress.class, ApiUtil.progressStr));
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

    private void consumeEvent(Observable<ConstructProgress> observable) {
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ConstructProgress>() {
                    @Override
                    public void onEnd() {
                        view.hideLoading();
                    }

                    @Override
                    public void onNext(ConstructProgress constructProgress) {
//                        if (constructProgress.getCode().equals("0")) {
//                            view.bindDataToRecycler(constructProgress);
//                        } else {
//                            ToastUtils.showToast(HDApplication.getInstance().getString(R.string.network_exception));
//                        }
                    }
                });
        compositeSubscription.add(subscription);
    }
}
