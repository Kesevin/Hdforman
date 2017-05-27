package com.dgg.hdforeman.mvp.contract.project;

import com.dgg.hdforeman.mvp.model.been.ProjectInformation;
import com.dgg.hdforeman.mvp.presenter.project.BasePresenterElse;
import com.jess.arms.mvp.BaseView;

/**
 * Created by tsang on 2016/10/25.
 */

public interface IProjectInfoFragment {
    interface View extends BaseView {
        void bindDataToRecyclerView(ProjectInformation projectInformation);
    }

    interface Presenter extends BasePresenterElse {
        void getProjectInfo();
    }
}
