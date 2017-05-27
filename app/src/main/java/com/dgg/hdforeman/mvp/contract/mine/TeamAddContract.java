package com.dgg.hdforeman.mvp.contract.mine;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.ui.base.ListBaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/31.
 */

public interface TeamAddContract {
    interface View extends ListBaseView {
        void startLoadMore();
        void endLoadMore();
        void setLoadEnd(boolean b);
        void bindTeamDataToListView(List<TeamSearchResult> list);
        void removeAdd(int position);
    }

    interface Model {
        Observable<BaseData<List<TeamSearchResult>>> getTeamNoForemanData(int pageStart,int pageSize);
    }

}
