package com.dgg.hdforeman.mvp.ui.project.activity;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerProjectShutComponent;
import com.dgg.hdforeman.di.module.ProjectShutModule;
import com.dgg.hdforeman.mvp.contract.project.ProjectShutContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.ProjectShutPresenter;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by kelvin on 2016/11/11.
 */

public class ProjectShutActivity extends BacksActivity<ProjectShutPresenter> implements ProjectShutContract.View {

    @BindView(R.id.ProjectShutEditText)
    EditText mEditText;

    public static final String PROJECT_SHUT_DATA = "project_shut_data";

    private WaitingDialog mWaitingDialog;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectShutComponent
                .builder()
                .appComponent(appComponent)
                .projectShutModule(new ProjectShutModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.project_shut_activity, null, false);
    }

    @Override
    protected void initData() {
        mWaitingDialog = new WaitingDialog(this);
        mWaitingDialog.setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.toolbar_right)
    public void projectShut(){
        mPresenter.projectShut();
    }

    @Override
    public void showLoading() {
        mWaitingDialog.show();
    }

    @Override
    public void hideLoading() {
        mWaitingDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {
        super.onBackPressed();
    }

    @Override
    public ProjectInfoResponse getData() {
        return (ProjectInfoResponse) getIntent().getExtras().getSerializable(PROJECT_SHUT_DATA);
    }

    @Override
    public String getReason() {
        return mEditText.getText().toString().trim();
    }
}
