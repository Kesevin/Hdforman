package com.dgg.hdforeman.mvp.contract.project;

import com.dgg.hdforeman.mvp.model.been.AddFreeTermBean;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.model.been.workertypeDb;
import com.dgg.hdforeman.mvp.ui.project.adapter.ProductAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.WokernameAdapter;
import com.jess.arms.mvp.BaseView;

import java.util.List;

import rx.Observable;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public interface AddFreeTermContract {
    interface View extends BaseView{
        void initrecylerview(WokernameAdapter adapter,ProductAdapter padapter);
        void showpopwindow(List<SpaceBean.SpacenameEntity> spacenameEntityList,AddFreeTermBean freeTermBean);
        void hidepopwindow();
    }
    interface Model{
        Observable<BaseJson<List<AddFreeTermBean>>> loadDeployFreeTerm();
        void savaDataInDb(List<AddFreeTermBean> beens);
        Observable<List<AddFreeTermBean>> getworkerType();
        List<AddFreeTermBean> getlistbyType(String type);
        Observable<BaseJson<SpaceBean>> getMessurelist(String id);
        Observable<Object[]> addFree2server(AddFreeTermBean addFreeTermBean, String pid, SpaceBean.SpacenameEntity spacenameEntity, String num);
    }
}
