package com.dgg.hdforeman.mvp.contract.income;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.Income;
import com.dgg.hdforeman.mvp.model.been.ScoreBean;
import com.jess.arms.mvp.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface IncomeContract {
    interface View extends BaseView {
        void bindDataToView(Income income);
    }

    interface Model {
        Observable<BaseData<Income>> getIncomeData();
    }
}
