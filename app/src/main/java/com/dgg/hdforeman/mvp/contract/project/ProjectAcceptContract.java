package com.dgg.hdforeman.mvp.contract.project;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.ProjectAcceptResponse;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.mvp.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/8.
 */

public interface ProjectAcceptContract {
    interface View extends BaseView {
        void setAdapter(BaseAdapter adapter);
        void launchIntermediateAcceptActivity(Intent intent);
    }


    interface Model {
        @WorkerThread
        Observable<BaseJson<List<ProjectAcceptResponse>>> getProjectAcceptList(@Nullable String proid);
    }

}
