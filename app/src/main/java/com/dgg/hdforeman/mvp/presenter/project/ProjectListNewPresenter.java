package com.dgg.hdforeman.mvp.presenter.project;

import android.support.annotation.Nullable;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.ProjectListContract;
import com.dgg.hdforeman.mvp.model.been.ProjectContentBean;
import com.dgg.hdforeman.mvp.model.been.ProjectListResponseNew;
import com.dgg.hdforeman.mvp.model.been.ProjectPriceBean;
import com.dgg.hdforeman.mvp.ui.project.adapter.ProjectListContentAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.ProjectListPriceAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * Created by kelvin on 2016/11/2.
 */
@ActivityScope
public class ProjectListNewPresenter extends BasePresenter<ProjectListContract.Model, ProjectListContract.View> {

    private RxErrorHandler mErrorHandler;
    private List<ProjectContentBean> mContentData = new ArrayList<>();
    private List<ProjectPriceBean> mPriceData = new ArrayList<>();
    private ProjectListContentAdapter mContentAdapter;
    private ProjectListPriceAdapter mPriceAdapter;

    @Inject
    public ProjectListNewPresenter(ProjectListContract.Model model, ProjectListContract.View view,
                                   RxErrorHandler handler) {
        super(model, view);
        this.mErrorHandler = handler;
    }

    public void initAdapter() {
        mContentAdapter = new ProjectListContentAdapter(mContentData);
        mRootView.setContentAdapter(mContentAdapter);

        mPriceAdapter = new ProjectListPriceAdapter(mPriceData);
        mRootView.setPriceAdapter(mPriceAdapter);
    }

    public void requestList(@Nullable String xmid) {
        mContentData.clear();
        mPriceData.clear();
        mModel.getProjectListNew(xmid)
                .compose(RxUtils.<ProjectListResponseNew>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<ProjectListResponseNew>(mErrorHandler) {
                    @Override
                    public void onNext(ProjectListResponseNew json) {
                        if (json.isSuccess()) {

                            if (json.getUpgradeList() != null && json.getUpgradeList().size() != 0) {
                                for (ProjectContentBean contentBean : json.getUpgradeList()) {
                                    mContentData.add(contentBean);
                                }
                                mContentAdapter.notifyDataSetChanged();
                            }

                            if (json.getPriceList() != null && json.getPriceList().size() != 0) {
                                for (ProjectPriceBean projectPriceBean : json.getPriceList()) {
                                    mPriceData.add(projectPriceBean);
                                }
                                mPriceAdapter.notifyDataSetChanged();
                            }
                        } else {
                            mRootView.showMessage(json.getMsg());
                        }
                    }
                });
    }

}
