package com.dgg.hdforeman.mvp.contract.mine;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.ScoreBean;
import com.dgg.hdforeman.mvp.ui.base.ListBaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/9.
 */

public interface ScoreContract {

    interface View extends ListBaseView {
        void startLoadMore();
        void endLoadMore();
        void setLoadEnd(boolean b);
        void bindDataToRecylerView(List<ScoreBean> list);
    }

    interface Model {
        Observable<BaseData<List<ScoreBean>>> getScoreListData(int pageStart,int pageSize);
    }
}
