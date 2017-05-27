package com.dgg.hdforeman.mvp.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.contract.mine.ModifyPassWordContract;
import com.dgg.hdforeman.mvp.presenter.mine.ModifyPassWordPresenter;
import com.dgg.hdforeman.mvp.ui.base.BaseActivity;
import com.dgg.hdforeman.mvp.ui.widget.WaitingDialog;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/10/21.
 */

public class ModifyPasswordActivity extends BaseActivity implements ModifyPassWordContract.View{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.modify_password_old)
    EditText modifyPasswordOld;
    @BindView(R.id.modify_password_new)
    EditText modifyPasswordNew;
    @BindView(R.id.modify_password_reNew)
    EditText modifyPasswordReNew;
    @BindView(R.id.show_chk)
    CheckBox mCheckBox;
    @BindView(R.id.show_chk2)
    CheckBox mCheckBox2;
    @BindView(R.id.show_chk3)
    CheckBox mCheckBox3;

    private ModifyPassWordPresenter mPresenter;
    private WaitingDialog mWaitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_modify_password_activity);
    }

    @Override
    protected void initView() {
        mWaitingDialog = new WaitingDialog(this);
        mWaitingDialog.setCanceledOnTouchOutside(false);
        title.setText("修改密码");
        rightMenu.setText("确定");
        rightMenu.setVisibility(View.VISIBLE);
        mPresenter=new ModifyPassWordPresenter(this,(HDApplication) getApplication());
        check(mCheckBox,modifyPasswordOld);
        check(mCheckBox2,modifyPasswordNew);
        check(mCheckBox3,modifyPasswordReNew);
    }

    private void check(CheckBox checkBox,final EditText editText){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick({R.id.toolbar_back, R.id.right_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.right_menu:
                mPresenter.modifyPassWord();
                break;
            case R.id.show_chk:
                modifyPasswordOld.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case R.id.show_chk2:
                modifyPasswordNew.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            case R.id.show_chk3:
                modifyPasswordReNew.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
        }
    }

    @Override
    public String getOldPassWord() {
        return modifyPasswordOld.getText().toString().trim();
    }

    @Override
    public String getNewPassWord() {
        return modifyPasswordNew.getText().toString().trim();
    }

    @Override
    public String getReNewPassWord() {
        return modifyPasswordReNew.getText().toString().trim();
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

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {
        finish();
    }
}
