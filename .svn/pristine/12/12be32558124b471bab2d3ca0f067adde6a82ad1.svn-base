package com.dgg.hdforeman.mvp.ui.mine.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerSetComponent;
import com.dgg.hdforeman.di.module.SetModule;
import com.dgg.hdforeman.mvp.contract.mine.SetContract;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.been.User;
import com.dgg.hdforeman.mvp.presenter.mine.SetPresenter;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.dgg.hdforeman.mvp.ui.tool.activity.AnnouncementDetailActivity;
import com.jess.arms.utils.PermissionUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/10/21.
 */

public class SetActivity extends BacksActivity<SetPresenter> implements SetContract.View{

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.set_phoneNumber)
    TextView setPhoneNumber;

    private User mUser;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.mine_set_activity,null,false);
    }

    @Override
    protected void initData() {
        title.setText("账号设置");
        mUser=mPresenter.getUser();
        setPhoneNumber.setText(mUser.getServicetel());
    }

    @OnClick({R.id.toolbar_back, R.id.set_modify_password, R.id.set_about, R.id.set_service, R.id.set_loginOut})
    public void onClick(View view) {
        Intent intent = new Intent();
        Class<?> cls = null;
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.set_modify_password://修改密码
                intent.setClass(this, ModifyPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.set_about://关于小顶
                intent.setClass(this, AnnouncementDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("url", Api.APP_DOMAIN + "/aboutXd.nk?");
                bundle.putString("title", "关于小顶");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.set_service://联系客服
                String phoneNumber=setPhoneNumber.getText().toString();
                if(!TextUtils.isEmpty(phoneNumber))
                callPhone(phoneNumber);
                break;
            case R.id.set_loginOut://退出登陆
                mPresenter.outLogin();
                break;
        }
    }


    public void callPhone(final String phone) {
        PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                if (ActivityCompat.checkSelfPermission(SetActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        +phone)));
            }
        }, RxPermissions.getInstance(this), this, mHDApplication.getAppComponent().rxErrorHandler());
    }
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSetComponent
                .builder()
                .appComponent(appComponent)
                .setModule(new SetModule(this))
                .build()
                .inject(this);
    }
}
