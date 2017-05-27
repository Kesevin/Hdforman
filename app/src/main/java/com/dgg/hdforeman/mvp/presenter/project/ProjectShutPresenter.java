package com.dgg.hdforeman.mvp.presenter.project;


import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.ProjectShutContract;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * Created by kelvin on 2016/11/2.
 */

@ActivityScope
public class ProjectShutPresenter extends BasePresenter<ProjectShutContract.Model, ProjectShutContract.View> {

    private RxErrorHandler mErrorHandler;

    @Inject
    public ProjectShutPresenter(ProjectShutContract.Model model, ProjectShutContract.View view,
                                RxErrorHandler handler) {
        super(model, view);
        this.mErrorHandler = handler;
    }

    public void projectShut() {
        projectShutViaPass(mRootView.getData(), mRootView.getReason());
    }

    private void projectShutViaPass(@Nullable ProjectInfoResponse data, @Nullable String reason) {
        if (data == null) {
            return;
        }
        if (TextUtils.isEmpty(reason)) {
            mRootView.showMessage("请输入停工原因");
            return;
        }

        mModel.projectShut(data.getId(), reason)
                .compose(RxUtils.<BaseJson>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson>(mErrorHandler) {
                    @Override
                    public void onNext(BaseJson json) {
                        if (json.isSuccess()) {
                            mRootView.killMyself();
                        } else {
                            mRootView.showMessage(json.getMsg());
                        }
                    }
                });
    }
}
