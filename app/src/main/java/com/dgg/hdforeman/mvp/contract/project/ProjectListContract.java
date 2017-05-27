package com.dgg.hdforeman.mvp.contract.project;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.dgg.hdforeman.mvp.model.been.ProjectListResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectListResponseNew;
import com.dgg.hdforeman.mvp.model.been.ProjectTotalBean;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.mvp.BaseView;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/2.
 */

public interface ProjectListContract {
    interface View extends BaseView {
        void setContentAdapter(BaseAdapter adapter);
        void updateLayout(ProjectTotalBean data,String total);
        void setPriceAdapter(BaseAdapter adapter);
    }


    interface Model {
        @WorkerThread
        Observable<ProjectListResponse> getProjectList(@Nullable String xmid);

        @WorkerThread
        Observable<ProjectListResponseNew> getProjectListNew(@Nullable String xmid);
    }

}
