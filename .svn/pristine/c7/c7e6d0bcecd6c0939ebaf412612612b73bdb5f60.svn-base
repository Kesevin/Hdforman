package com.dgg.hdforeman.mvp.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.dgg.hdforeman.R;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.mvp.BasePresenter;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * Created by Rex on 2016/10/18.
 */
public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity {

    protected ConnectionChangeReceiver myReceiver;
    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new ConnectionChangeReceiver();
        this.registerReceiver(myReceiver, filter);
        if (needBindEvent()) {
            EventBus.getDefault().register(this);
        }
     
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        //第二个参数是想要设置的颜色
        ButterKnife.bind(this);
        initView();
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        initView();
    }
    @Override
    protected void onStart() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorOrange), 0);
        super.onStart();
    }
    @Override
    protected void onDestroy() {
        this.unregisterReceiver(myReceiver);
        if (needBindEvent()) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    protected abstract void initView();
    protected boolean needBindEvent() {
        return false;
    }
    protected void readyGo(Class<?> pClass) {
        startActivity(new Intent(this, pClass));
    }
    protected void readyGo(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (null != pBundle) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }
    protected void readyGo(Class<?> pClass, int requestCode) {
        Intent intent = new Intent(this, pClass);
        startActivityForResult(intent, requestCode);
    }

    protected void readyGo(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (null != pBundle) {
            intent.putExtras(pBundle);
        }
        startActivityForResult(intent, requestCode);
    }
    public class ConnectionChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifiNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                Toast.makeText(BaseActivity.this, "网络连接已断开，请检测你的网路！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}