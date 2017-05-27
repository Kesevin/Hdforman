package com.dgg.hdforeman.app.utils;

import android.widget.Toast;

import com.dgg.hdforeman.app.HDApplication;

/**
 * Created by tsang on 2016/10/14.
 */

public class ToastUtils {
    public static Toast toast;

    public static void showToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(HDApplication.getInstance().getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
