package com.dgg.hdforeman.mvp.presenter.mine;

/**
 * Created by Administrator on 2016/10/27.
 */

public interface BasePresenter {
    /**
     * 释放资源或应用，防止内存泄露
     */
    void releaseResources();

    /**
     * 取消订阅，用于界面隐藏时，停止更新UI
     */
    void unSubscribe();
}
