package com.dgg.hdforeman.mvp.contract.project;

import com.dgg.hdforeman.mvp.model.been.ProjectDetail;
import com.dgg.hdforeman.mvp.presenter.project.BasePresenterElse;
import com.jess.arms.mvp.BaseView;

/**
 * Created by tsang on 2016/10/20.
 */

public interface IProjectDetailActivity {
    interface View extends BaseView {
        void bindDataToUI(ProjectDetail projectDetail);
    }

    interface Presenter extends BasePresenterElse {
        void getProjectDetail();
    }
}
