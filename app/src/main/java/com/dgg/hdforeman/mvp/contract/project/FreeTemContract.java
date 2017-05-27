package com.dgg.hdforeman.mvp.contract.project;


import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBean;
import com.dgg.hdforeman.mvp.ui.project.adapter.FreeTermAdapter;
import com.jess.arms.mvp.BaseView;

import java.util.List;

import rx.Observable;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public interface FreeTemContract {
    public interface View extends BaseView{
        void initRecylerView(FreeTermAdapter adapter);

    }
    public interface Model{
        Observable<BaseJson<List<FreeTermlistBean>>> getFreeTerms(String id);
        Observable<List<FreeTermlistBean>> queryAllFree();
        Observable<Iterable<FreeTermlistBean>> insertdatas(List<FreeTermlistBean> freeTermlistBeens);
        Observable<Void> delall();
        Observable<Void> delFreeTerm(FreeTermlistBean freeTermlistBean);
        Observable<FreeTermlistBean> update(FreeTermlistBean freeTermlistBean);
        Observable<List<FreeTermlistBean>> queryById(String id);
    }
}
