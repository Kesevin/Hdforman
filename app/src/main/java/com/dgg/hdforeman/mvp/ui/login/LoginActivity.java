package com.dgg.hdforeman.mvp.ui.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.SPUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerLoginComponent;
import com.dgg.hdforeman.di.module.LoginModule;
import com.dgg.hdforeman.mvp.contract.login.LoginContract;
import com.dgg.hdforeman.mvp.presenter.login.LoginPresenter;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class LoginActivity extends BacksActivity<LoginPresenter> implements LoginContract.View, TextWatcher {


    private static final String TAG = "LoginActivity";
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.delete)
    ImageView delete;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.show_chk)
    CheckBox showChk;
    @BindView(R.id.login_btn)
    TextView loginBtn;


    private WaitingDialog mWaitingDialog;

    @Override
    protected void onStart() {
        super.onStart();
        StatusBarUtil.setTransparentForImageView(this, null);
    }


    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.login_activity, null, false);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected void initData() {
        mWaitingDialog = new WaitingDialog(this);
        mWaitingDialog.setCanceledOnTouchOutside(false);
        phoneEt.setText(SPUtil.getParam(this, "userphone", "").toString());
        showChk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    //如果选中，显示密码
                    passwordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    passwordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
        phoneEt.addTextChangedListener(this);
    }


    @OnClick({R.id.login_btn, R.id.delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                mPresenter.login();
                break;
            case R.id.delete:
                phoneEt.setText("");
                break;
        }
    }

    @Override
    public String getPhone() {
        return phoneEt.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return passwordEt.getText().toString().trim();
    }

    @Override
    public void rememberPhone(String phone) {
        phoneEt.setText(phone);
    }

    @Override
    public void showLoading() {
        mWaitingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mWaitingDialog != null)
            mWaitingDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(this, intent);
    }

    @Override
    public void killMyself() {
        finish();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String content = s.toString().trim();
        if (TextUtils.isEmpty(content)) {
            delete.setVisibility(View.GONE);

        } else {
            delete.setVisibility(View.VISIBLE);
        }
    }

}
