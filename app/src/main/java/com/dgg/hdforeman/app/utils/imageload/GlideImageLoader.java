package com.dgg.hdforeman.app.utils.imageload;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.dgg.hdforeman.mvp.model.api.Api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rex on 2016/11/4.
 */

public class GlideImageLoader implements BaseLoaderProvider {

    private int loadingImage;//正在加载中的图片
    private int errImage;//加载失败的图片
    private  RequestListener  listener;

    @Override
    public void loadImage(Context ctx, ImageView imgView, String url) {
        DrawableTypeRequest<String> buld = Glide.with(ctx).load(url);
        if (loadingImage != 0) {
            buld.placeholder(loadingImage);
        }
        if (errImage != 0) {
            buld.placeholder(errImage);
        }
        if(listener!=null){
            buld.listener(listener);
        }
        buld.into(imgView);

    }

    public GlideImageLoader() {

    }

    public GlideImageLoader(RequestListener  listener) {
         this.listener=listener;
    }
    public GlideImageLoader(int loadingImage, int errImage) {
        this.loadingImage = loadingImage;
        this.errImage = errImage;
    }
    public GlideImageLoader(int loadingImage, int errImage ,RequestListener  listener) {
        this.loadingImage = loadingImage;
        this.errImage = errImage;
        this.listener=listener;
    }

    public static String getImageUrl(String id,String width,String hight){
        JSONObject json=new JSONObject();
        try {
            json.put("id",id);
            json.put("width",width);
            json.put("hight",hight);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url= Api.APP_DOMAIN+Api.IMAGE_URL+json;
        Log.i("msg","url========"+url);
        return url;
    }

}
