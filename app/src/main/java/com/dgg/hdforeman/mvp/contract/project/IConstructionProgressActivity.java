package com.dgg.hdforeman.mvp.contract.project;

import com.dgg.hdforeman.mvp.model.been.ConstructProgress;
import com.dgg.hdforeman.mvp.presenter.project.BasePresenterElse;
import com.jess.arms.mvp.BaseView;

/**
 * Created by tsang on 2016/10/20.
 */

public interface IConstructionProgressActivity {
    interface View extends BaseView {
        void bindDataToRecycler(ConstructProgress data);
    }

    interface Presenter extends BasePresenterElse {
        void getData();
    }
}
