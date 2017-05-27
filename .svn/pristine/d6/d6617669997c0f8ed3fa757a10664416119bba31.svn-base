package com.dgg.hdforeman.mvp.ui.project.holder;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.model.been.ImageInfo;
import com.dgg.hdforeman.mvp.ui.project.adapter.UploadPicAdapter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.widget.BGAImageView;


/**
 * Created by kelvin on 2016/11/3.
 */

public class UploadHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.photo_img)
    BGAImageView photoImg;
    @BindView(R.id.mask)
    ImageView mask;
    @BindView(R.id.load_progress)
    ProgressBar loadProgress;
    @BindView(R.id.retry)
    ImageView retry;
    @BindView(R.id.delete)
    ImageView delete;

    ImageInfo data;

    private final HDApplication mApplication;
    private ImageLoader mImageLoader;
    private UploadPicAdapter.OnItemClickListener mListener;
    public UploadHolder(View itemView, UploadPicAdapter.OnItemClickListener listener) {
        super(itemView);
        bindTarget(this, itemView);//绑定
        this.mListener=listener;
        mApplication = (HDApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
        itemView.setOnClickListener(this);
    }

    public void setData(ImageInfo data, boolean last) {
        this.data = data;
        if (last) {
            photoImg.setImageBitmap(BitmapFactory.decodeResource(
                    mApplication.getResources(), R.mipmap.bga_pp_ic_plus));
            delete.setVisibility(View.GONE);
            mask.setVisibility(View.GONE);
            loadProgress.setVisibility(View.GONE);
            retry.setVisibility(View.GONE);
        } else {

            String imgUri = data.getPath();
            switch (data.getType()){
                case 0:
                    try {
                        FileInputStream f = f = new FileInputStream(imgUri);
                        Bitmap bm = null;
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 5;//图片的长宽都是原来的1/5
                        BufferedInputStream bis = new BufferedInputStream(f);
                        bm = BitmapFactory.decodeStream(bis, null, options);
                        photoImg.setImageBitmap(bm);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    mImageLoader.loadImage(mApplication, GlideImageConfig
                            .builder()
                            .url(imgUri)
                            .imagerView(photoImg)
                            .errorPic(R.mipmap.bga_pp_ic_holder_light)
                            .build());
                    break;
            }
            switch (data.getState()) {
                case 0:
                    mask.setVisibility(View.VISIBLE);
                    loadProgress.setVisibility(View.VISIBLE);
                    retry.setVisibility(View.GONE);
                    delete.setVisibility(View.GONE);
                    break;
                case 1:
                    mask.setVisibility(View.GONE);
                    loadProgress.setVisibility(View.GONE);
                    delete.setVisibility(View.VISIBLE);
                    retry.setVisibility(View.GONE);
                    break;
                case 2:
                    delete.setVisibility(View.VISIBLE);
                    loadProgress.setVisibility(View.GONE);
                    retry.setVisibility(View.VISIBLE);
                    mask.setVisibility(View.GONE);
                    break;
            }
        }
    }


    @OnClick(R.id.retry)
    public void onRetry(View view){
        if (mListener != null) {
            mListener.onRetryClick(view,getLayoutPosition(),data);}
    }

//    @OnClick(R.id.photo_img)
//    public void onPrevie(View view){
//
//    }

    @OnClick(R.id.delete)
    public void onDeleteView (View view){
        if (mListener != null) {
        mListener.onDeleteClick(view ,getLayoutPosition(),data);}
    }
    public void bindTarget(Object target, Object source) {
        if (source instanceof Activity) {
            ButterKnife.bind(target, (Activity) source);
        } else if (source instanceof View) {
            ButterKnife.bind(target, (View) source);
        } else if (source instanceof Dialog) {
            ButterKnife.bind(target, (Dialog) source);
        }
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItem(v ,getLayoutPosition());}
    }
}
