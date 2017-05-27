package com.dgg.hdforeman.mvp.model.net;

import android.widget.Toast;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;

import rx.Subscriber;

/**
 * Created by Rex on 2016/10/22.
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {
    /**
     * 做一些dialog消失操作或停止刷新、加载操作
     */
    public abstract void onEnd();

    @Override
    public void onCompleted() {
        onEnd();
    }

    @Override
    public void onError(Throwable e) {
        onEnd();
        Toast.makeText(HDApplication.getInstance().getApplicationContext(),"网络连接失败，请检测你的网络!", Toast.LENGTH_SHORT).show();
        CommonUtil.logDebug(e.getMessage());
    }


}
