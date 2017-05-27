package com.dgg.hdforeman.mvp.contract.project;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectShutResponse;
import com.jess.arms.mvp.BaseView;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/2.
 */

public interface ProjectShutContract {
    interface View extends BaseView {
        ProjectInfoResponse getData();
        String getReason();
    }

    interface Model {
        @WorkerThread
        Observable<BaseJson> projectShut(@Nullable String proid, @Nullable String reason);
    }

}
