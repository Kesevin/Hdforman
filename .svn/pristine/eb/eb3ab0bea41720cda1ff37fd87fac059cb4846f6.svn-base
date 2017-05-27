package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.base.HDActivity;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.mvp.BaseView;
import com.jess.arms.utils.UiUtils;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Created by HSAEE on 2016/11/2.
 */

public abstract class BacksActivity<P extends BasePresenter> extends HDActivity<P> implements BaseView{
    @Nullable
    @BindView(R.id.toolbar_back)
    ImageView mBack;
    @Override
    protected void initToolBar() {
        super.initToolBar();
        if (mBack != null)//点击返回按钮退出页面
            mBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BacksActivity.this.onBackPressed();//点击返回按钮，关闭本页面
                }
            });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {
        checkNotNull(message);
        UiUtils.SnackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        super.onBackPressed();
    }
}
