package com.dgg.hdforeman.mvp.contract.project;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.mvp.BaseView;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/2.
 */

public interface ProjectInformationContract {
    interface View extends BaseView {
        void setAdapter(BaseAdapter adapter);

        void updateLayout(ProjectInfoResponse data);
    }


    interface Model {
        @WorkerThread
        Observable<BaseJson<ProjectInfoResponse>> getProjectInformation(@Nullable String proid);

        @WorkerThread
        Observable<BaseData> signProject(@Nullable String id);
    }

}
