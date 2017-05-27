package com.dgg.hdforeman.mvp.contract.project;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.ui.project.adapter.MessureResultAdapter;
import com.jess.arms.mvp.BaseView;

import rx.Observable;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public interface ProjectMessureResultContract {
    interface View extends BaseView{
        void initRecylerview(MessureResultAdapter adapter);
        void refreshend();
        void lauchActivity(String meesure,String housetype,Class<?> cls);
        void tvPreiceGoneornot(boolean isvisiable);
    }
    interface Model{
        Observable<BaseJson<SpaceBean>> getMessurelist(String id);
    }
}
