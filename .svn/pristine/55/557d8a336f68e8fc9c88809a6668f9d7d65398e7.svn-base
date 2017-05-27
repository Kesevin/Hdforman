package com.dgg.hdforeman.mvp.contract.project;

import android.content.Intent;
import android.support.annotation.Nullable;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.LiveBean;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.mvp.BaseView;

import java.util.List;

import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;
import rx.Observable;

/**
 * Created by kelvin on 2016/11/8.
 */

public interface FieldLiveContract {
    interface View extends BaseView {
        void setAdapter(BaseAdapter adapter);
        void launchActivity(Intent intent);
        void configPhotoLayout(BGANinePhotoLayout bgaNinePhotoLayout, List<String> imgUrl);
        void  previewImage(LiveBean liveBean);
        public void stopRefresh();
        public void stopLoadMore();
        public void startLoadMore();
        public void endLoadMore();
        void setIsLoad(boolean isLoad);
        public void showNoData();
        public void hideNodata();
    }


    interface Model {
        Observable<BaseData<List<LiveBean>>> getSiteLiveData(@Nullable String proid, int pageStart, int pageSize);
    }

}
