package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.ProjectInformationContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.model.been.ProjectResponse;
import com.dgg.hdforeman.mvp.ui.project.activity.NotSignInfoActivity;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectMessureActivity;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectMessureResultActivity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

import static com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment.ITEM_DATA;

/**
 * Created by kelvin on 2016/11/2.
 */

@ActivityScope
public class ProjectInformationPresenter extends BasePresenter<ProjectInformationContract.Model, ProjectInformationContract.View> {

    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    //    private List<String> imgUrlData = new ArrayList<>();
//    private ProjectInformationAdapter mAdapter;
    private ProjectInfoResponse projectInfoResponse;

    @Inject
    public ProjectInformationPresenter(ProjectInformationContract.Model model, ProjectInformationContract.View view,
                                       RxErrorHandler handler, Application application) {
        super(model, view);
        this.mErrorHandler = handler;
        this.mApplication = application;
    }

    public void initAdapter() {
//        mAdapter = new ProjectInformationAdapter(imgUrlData);
//        mRootView.setAdapter(mAdapter);
    }

    public void getProjectInformation(Intent intent) {

        String id = ((ProjectResponse) intent.getExtras().get(ITEM_DATA)).getId();

        mModel.getProjectInformation(id)
                .compose(RxUtils.<BaseJson<ProjectInfoResponse>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson<ProjectInfoResponse>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseJson<ProjectInfoResponse> json) {
                        if (json.isSuccess()) {
                            projectInfoResponse = json.getD();
                            mRootView.updateLayout(json.getD());//将详情数据提供给view刷新页面
                        } else {
                            mRootView.showMessage(json.getMsg());
                        }
                    }
                });
    }


    /**
     * 工长接手
     *
     * @param id
     */
    public void signProject(final String id) {
        mModel.signProject(id)
                .compose(RxUtils.<BaseData>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseData>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData baseData) {
                        if (baseData.isSuccess()) {
                            mRootView.showMessage(baseData.getMsg());
                            mRootView.killMyself();
                            launchProjectInfo(id);//跳到项目页面
                        } else {
                            mRootView.showMessage(baseData.getMsg());
                        }
                    }
                });
    }


    private void launchProjectInfo(String id) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        ProjectResponse response = new ProjectResponse();
        response.setId(id);
        bundle.putSerializable(ITEM_DATA, response);//将列表数据通过intent带给详情页
        intent.putExtras(bundle);
        mRootView.launchActivity(intent.setClass(mApplication, NotSignInfoActivity.class));
    }

    public void startActivity(Context context) {
        if (projectInfoResponse==null) return;
        if (projectInfoResponse.getMeasure().endsWith("0")) {
            //未测量
            ProjectMessureActivity.startActivity(context, projectInfoResponse.getId(), false);
        } else {
            if (projectInfoResponse.getPricestate().equals("0")){
                ProjectMessureResultActivity.startActivity(context, projectInfoResponse.getId(),false);
            }else {
                ProjectMessureResultActivity.startActivity(context, projectInfoResponse.getId(),true);
            }

        }

    }
}
