package com.dgg.hdforeman.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * Created by jess on 01/12/2016 20:40
 * Contact with jess.yan.effort@gmail.com
 */

public class NoTouchTagFlowLayout extends TagFlowLayout {
    public NoTouchTagFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public NoTouchTagFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoTouchTagFlowLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
