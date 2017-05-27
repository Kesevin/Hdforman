package com.dgg.hdforeman.mvp.contract.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseData2;
import com.dgg.hdforeman.mvp.model.been.UpLoadResponse;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.UploadPicAdapter;
import com.jess.arms.mvp.BaseView;

import java.util.ArrayList;

import rx.Observable;


/**
 * Created by kelvin on 2016/11/2.
 */

public interface HDPhotoPreviewContract {
    interface View extends BaseView {
        void finishWithData(String data);
        public  void setAdapter(UploadPicAdapter adapter);
    }


    interface Model {
        public Observable<BaseData2<UpLoadResponse>> upLoadImg(@Nullable String picName);

        public Observable<BaseData<String>> confirmUpload(@Nullable String projectId,String pictureIds);

        public Observable<BaseData<String>> confirmAcceptance(@Nullable String proid ,String stage ,String pictureIds );


        public Observable<BaseData<String>>  uploadDecorationPermit(@Nullable String projectId,String pictureIds);
    }

}
