package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.ProjectMessureResultContract;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.ui.project.activity.ProjectQuoteActivity;
import com.dgg.hdforeman.mvp.ui.project.adapter.MessureResultAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * author:zhangjing
 * 作用:
 * return:
 */
@ActivityScope
public class ProjectMessureResultPresenter extends BasePresenter<ProjectMessureResultContract.Model, ProjectMessureResultContract.View> {
    private ProjectMessureResultContract.View loginView;
    private ProjectMessureResultContract.Model loginModel;
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private MessureResultAdapter adapter;
    private TextView textView;
    private BaseJson<SpaceBean> response;
    private List<SpaceBean.SpacenameEntity> datas = new ArrayList<>();

    @Inject
    public ProjectMessureResultPresenter(ProjectMessureResultContract.Model model, ProjectMessureResultContract.View rootView, ProjectMessureResultContract.View loginView, ProjectMessureResultContract.Model loginModel, RxErrorHandler mErrorHandler, Application mApplication) {
        super(model, rootView);
        this.loginView = loginView;
        this.loginModel = loginModel;
        this.mErrorHandler = mErrorHandler;
        this.mApplication = mApplication;
    }

    public void loadInitData(String id, final boolean isrefresh) {
        mModel.getMessurelist(id).compose(RxUtils.<BaseJson<SpaceBean>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson<SpaceBean>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseJson<SpaceBean> spaceBeanBaseJson) {
                        if (spaceBeanBaseJson.isSuccess()) {
                            if (spaceBeanBaseJson.isSuccess()) {
                                if (isrefresh){
                                    mRootView.refreshend();
                                }
                                response=spaceBeanBaseJson;
                                if (spaceBeanBaseJson.getD() != null) {
                                    textView.setText(spaceBeanBaseJson.getD().getAllforests() + "m²");
                                    if (spaceBeanBaseJson.getD().getAllforests() == 0) {
                                        mRootView.tvPreiceGoneornot(false);
                                    }
                                    adapter.setNewData(spaceBeanBaseJson.getD().getSpacename());
                                } else {
                                    mRootView.tvPreiceGoneornot(false);
                                }
                            } else {
                                mRootView.showMessage(spaceBeanBaseJson.getMsg());
                            }
                        }
                    }
                });
    }

    public void initview() {
        adapter = new MessureResultAdapter(datas);
        View view = LayoutInflater.from(mApplication).inflate(R.layout.footer_messureresult, null);
        textView = (TextView) view.findViewById(R.id.tv_size);
        adapter.addFooterView(view);
        mRootView.initRecylerview(adapter);
    }

    //刷新数据
    public void updateData(String id) {
        mModel.getMessurelist(id).compose(RxUtils.<BaseJson<SpaceBean>>applySchedulerswithoutdialog(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson<SpaceBean>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseJson<SpaceBean> spaceBeanBaseJson) {
                        if (spaceBeanBaseJson.isSuccess()) {
                            if (spaceBeanBaseJson.getD() != null) {
                                response = spaceBeanBaseJson;
                                textView.setText(spaceBeanBaseJson.getD().getAllforests() + "m²");
                                adapter.setNewData(spaceBeanBaseJson.getD().getSpacename());
                            }else{
                                adapter.setNewData(new ArrayList<SpaceBean.SpacenameEntity>());
                                textView.setText("0.00"+ "m²");
                                mRootView.tvPreiceGoneornot(false);
                            }
                        } else {
                            mRootView.showMessage(spaceBeanBaseJson.getMsg());
                        }
                    }
                });
    }
    public void lanuchQue(){
        if (response != null)
            mRootView.lauchActivity(response.getD().getAllforests() + "",response.getD().getPm_housetype(), ProjectQuoteActivity.class);
    }

}
