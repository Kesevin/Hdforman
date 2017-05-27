package com.dgg.hdforeman.mvp.contract.tool;

import com.dgg.hdforeman.mvp.model.been.AnnouncementBean;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.ui.base.ListBaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/2.
 */

public interface AnnouncementContract {

    interface View extends ListBaseView {
        void startLoadMore();
        void endLoadMore();
        void setLoadEnd(boolean b);
        void bindDataToRecylerView(List<AnnouncementBean> list);
    }
    interface Model {
        Observable<BaseData<List<AnnouncementBean>>> getAnnouncementListData(int pageStart, int pageSize);
    }
}
