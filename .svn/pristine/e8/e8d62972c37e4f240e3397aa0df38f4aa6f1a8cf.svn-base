package com.dgg.hdforeman.mvp.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kelvin on 2016/11/18.
 */

public class BaseItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpaceLeft,mSpaceTop,mSpaceRight,mSpaceBottom;

    public BaseItemDecoration(int spaceLeft, int spaceTop, int spaceRight, int spaceBottom) {
        mSpaceLeft = spaceLeft;
        mSpaceTop = spaceTop;
        mSpaceRight = spaceRight;
        mSpaceBottom = spaceBottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(mSpaceLeft,mSpaceTop,mSpaceRight,mSpaceBottom);
    }
}
