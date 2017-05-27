package com.dgg.hdforeman.app.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dgg.hdforeman.app.config.GlobalConfig;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.model.been.User;

/**
 * Created by Administrator on 2016/11/4.
 */

public class GlidImageLoder {
    private String doValue;
    private boolean appFileValue;
    private Long uid;
    private String password;

    public static void loadImage(Context context, String url, final ImageView imageView, int errorImage, int placeImage){
        Glide.with(context)
                .load(url)
                .placeholder(placeImage)
                .error(errorImage)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        imageView.setImageDrawable(resource);
                    }
                });
    }

    public static String getImageUrl(String fileid,User user){
        String url= Api.APP_DOMAIN +GlobalConfig.IMAGE_URL+"&fileid="+fileid+"&uid="+user.getUsid()+"&password="+user.getUspassword();
        Log.i("msg","url========"+url);
        return url;
    }
}
