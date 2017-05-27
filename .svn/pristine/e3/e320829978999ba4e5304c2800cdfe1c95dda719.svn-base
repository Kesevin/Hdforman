package com.dgg.hdforeman.mvp.contract.mine;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.MineData;
import com.jess.arms.mvp.BaseView;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/28.
 */

public interface DataContract {
    interface View extends BaseView {
        void bindDataToView(MineData mineData);
    }

    interface Model {
        Long getUserId();
        Observable<BaseData<MineData>> getMineData();
    }
}
