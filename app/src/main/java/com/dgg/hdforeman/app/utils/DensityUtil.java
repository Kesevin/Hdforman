package com.dgg.hdforeman.app.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by Rex on 2016/10/21.DensityUtil
 */
public class DensityUtil
{
//    public static final boolean DEBUG = BuildConfig.TITLEBAR_DEBUG;
    public static final boolean DEBUG = false;

    private static DensityUtil util;
    private  DisplayMetrics dm;
    private int mScreenWidth, mScreenHeight;


    public static void init(Context mContext)
    {
        if (null == util)
        {
            synchronized (DensityUtil.class)
            {
                if (null == util)
                {
                    util = new DensityUtil(mContext.getApplicationContext());
                }
            }
        }
    }

    public static DensityUtil getIntance()
    {
        if (null == util)
        {
            throw new NullPointerException("DensityUtil is Null");
        }
        return util;
    }


    private DensityUtil(Context mContext)
    {

        dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
    }

    public int getScreenWidth()
    {
        return mScreenWidth;
    }

    public int getScreenHeight()
    {
        return mScreenHeight;
    }



    public  int dipTopx(float dpValue)
    {
        return (int) (dpValue * DensityUtil.getIntance().dm.density + 0.5f);
    }

    public  int pxTodip(float pxValue)
    {
        return (int) (pxValue / DensityUtil.getIntance().dm.density + 0.5f) - 15;
    }

    public static int getPercentHeightSize(int size)
    {
        if (DEBUG)
        {
            return size;
        }
        return AutoUtils.getPercentHeightSize(size);
    }

    public static int getPercentWidthSize(int size)
    {
        if (DEBUG)
        {
            return size;
        }
        return AutoUtils.getPercentWidthSize(size);
    }
    /**
     * 描述：获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
