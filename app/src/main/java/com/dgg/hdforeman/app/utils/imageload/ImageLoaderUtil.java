package com.dgg.hdforeman.app.utils.imageload;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Rex on 2016/11/4.
 */

public class ImageLoaderUtil {

    private static ImageLoaderUtil mInstance;

    private  BaseLoaderProvider mStrategy;

    private ImageLoaderUtil(){
        //默认添加一个实现的策略
        mStrategy =new GlideImageLoader();
    }

    public static ImageLoaderUtil getInstance(){
        if(mInstance ==null){
            synchronized (ImageLoaderUtil.class){
                if(mInstance == null){
                    mInstance = new ImageLoaderUtil();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    public void loadImage(Context context, ImageView img ,String url){
        mStrategy.loadImage(context,img,url);
    }

    public void setLoadImgStrategy(BaseLoaderProvider strategy){
        mStrategy =strategy;
    }
}
