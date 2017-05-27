package com.dgg.hdforeman.mvp.ui.base;

import com.jess.arms.mvp.BaseView;

/**
 * Created by Administrator on 2016/11/24.
 */

public interface ListBaseView extends BaseView{
    void showNoData();//列表无数据提示显示
    void hintNodata();//列表无数据提示隐藏
}
