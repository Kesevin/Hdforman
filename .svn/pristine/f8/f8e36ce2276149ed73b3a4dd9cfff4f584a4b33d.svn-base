package com.dgg.hdforeman.mvp.ui.login;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.SPUtil;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.ui.MainActivity;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.jess.arms.mvp.BaseView;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.UiUtils;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * Created by jess on 11/11/2016 11:49
 * Contact with jess.yan.effort@gmail.com
 */

public class SplashActivity extends BacksActivity implements BaseView{
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_splash, null, false);
    }

    @Override
    protected void initData() {
        if (DataHelper.getStringSF(getApplicationContext()
                , SPUtil.CURRENT_USER_ID) != null) {
            launchActivity(new Intent(mApplication, MainActivity.class));
        }else {
            launchActivity(new Intent(mApplication, LoginActivity.class));
        }
        killMyself();

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(this, intent);
        finish();
    }

    @Override
    public void killMyself() {
        finish();
    }
}
