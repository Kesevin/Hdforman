package com.dgg.hdforeman.mvp.contract.project;

import android.content.Intent;

import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.jess.arms.mvp.BaseView;

/**
 * Created by kelvin on 2016/11/2.
 */

public interface ConstructMaterialContract {
    interface View extends BaseView {
        void setAdapter(BaseAdapter adapter);
        void launchActivity(Intent intent);
    }


    interface Model {

    }

}
