package com.dgg.hdforeman.mvp.ui.base;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.mvp.BasePresenter;

import butterknife.BindView;

/**
 * Created by jess on 8/5/16 13:13
 * contact with jess.yan.effort@gmail.com
 */
public abstract class HDActivity<P extends BasePresenter> extends BaseActivity<P> {
    protected HDApplication mHDApplication;
    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;
    @Nullable
    @BindView(R.id.toolbar_title)
    TextView mTitle;

    @Override
    protected void ComponentInject() {
        mHDApplication = (HDApplication) getApplication();
        setupActivityComponent(mHDApplication.getAppComponent());
        initToolBar();
    }

    /**
     * 初始化toolbar
     */
    protected void initToolBar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * manifest中如果设置了label，则会调用此方法
     * @param title
     * @param color
     */
    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (mTitle != null) {
            mTitle.setText(title);//将label显示在自定义的title中
        }
    }

    @Override
    protected void onStart() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorOrange), 0);
        super.onStart();
    }
    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);
}
