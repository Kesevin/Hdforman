package com.dgg.hdforeman.mvp.contract.project;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.IntermediateAcceptResponse;
import com.dgg.hdforeman.mvp.model.been.InviteRequest;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.mvp.BaseView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/8.
 */

public interface IntermediateAcceptContract {
    interface View extends BaseView {
        void updateLayout(IntermediateAcceptResponse data);
        void setAdapter(BaseAdapter adapter);
        void launchActivityForResult(Intent intent,int request);
        public void jumpToPhotoPreview(ArrayList<String> url, int position);
    }


    interface Model {
        @WorkerThread
        Observable<BaseJson<IntermediateAcceptResponse>> getIntermediateAcceptList(@Nullable String proid, @Nullable String stage);

        @WorkerThread
        Observable<BaseJson> ownerAcceptance(@Nullable List<InviteRequest.WorkBean> work, @Nullable String stageid);
    }

}
