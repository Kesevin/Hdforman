package com.dgg.hdforeman.mvp.contract.project;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.ConstructProgress;
import com.dgg.hdforeman.mvp.model.been.ConstructProgressBean;
import com.dgg.hdforeman.mvp.model.been.ProjectAcceptResponse;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.mvp.BaseView;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/7.
 */

public interface ConstructProgressContract {
    interface View extends BaseView {
        void setAdapter(BaseAdapter adapter);
        void launchActivity(Intent intent);
        void onExpandImageViewClick(boolean isExpanded, ConstructProgressBean dBean);
        void updateStageNode(ConstructProgressBean data,int position);
        void updatePop(int shutdown);
        void setDialog(ProjectAcceptResponse data);
    }


    interface Model {
        @WorkerThread
        Observable<BaseJson<ConstructProgress.PDBean>> getConstructProgressInfo(@Nullable String proid);

        @WorkerThread
        Observable<BaseJson> updateStageNode(@Nullable String id);

        Observable<BaseData> applyStartWork(String proid);
    }

}
