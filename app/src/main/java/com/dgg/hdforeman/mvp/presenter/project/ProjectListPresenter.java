package com.dgg.hdforeman.mvp.presenter.project;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.ProjectListContract;
import com.dgg.hdforeman.mvp.model.been.ProjectContentBean;
import com.dgg.hdforeman.mvp.model.been.ProjectListResponse;
import com.dgg.hdforeman.mvp.ui.project.adapter.ProjectListContentAdapter;
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
public class ProjectListPresenter extends BasePresenter<ProjectListContract.Model, ProjectListContract.View> {

    private RxErrorHandler mErrorHandler;
    private List<ProjectContentBean> mContentData = new ArrayList<>();
    private ProjectListContentAdapter mContentAdapter;

    @Inject
    public ProjectListPresenter(ProjectListContract.Model model, ProjectListContract.View view,
                                RxErrorHandler handler) {
        super(model, view);
        this.mErrorHandler = handler;
    }

    public void initAdapter(){
        mContentAdapter = new ProjectListContentAdapter(mContentData);
        mRootView.setContentAdapter(mContentAdapter);
    }

    public void requestList(@Nullable String xmid){
        mContentData.clear();
        mModel.getProjectList(xmid)
                .compose(RxUtils.<ProjectListResponse>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<ProjectListResponse>(mErrorHandler) {
                    @Override
                    public void onNext(ProjectListResponse json) {
                        if (json.isSuccess()) {

                            for(ProjectContentBean contentBean : json.getKj()){
                                mContentData.add(contentBean);
                            }
                            mContentAdapter.notifyDataSetChanged();
                            if(json.getJg()!=null && json.getJg().size()!=0){
                                mRootView.updateLayout(json.getJg().get(0),json.getJg().get(0).getPm_acreage());
                            }
                        } else {
                            mRootView.showMessage(json.getMsg());
                        }
                    }
                });
    }

}
