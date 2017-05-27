package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.imageload.GlideImageLoader;
import com.dgg.hdforeman.app.utils.imageload.ImageLoaderUtil;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/18.
 */

public class FitmentPictureHolder extends BaseHolder<String> {

    @BindView(R.id.item_project_fitment_image)
    ImageView itemProjectFitmentImage;

    private HDApplication application;

    public FitmentPictureHolder(View itemView) {
        super(itemView);
        application = (HDApplication) itemView.getContext().getApplicationContext();
    }

    @Override
    public void setData(String data) {
        ImageLoaderUtil.getInstance().loadImage(application,itemProjectFitmentImage, GlideImageLoader.getImageUrl(data,"550","482"));
    }
}
