package com.dgg.hdforeman.mvp.ui.project.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.project.FieldLiveContract;
import com.dgg.hdforeman.mvp.model.been.ImageInfo;
import com.dgg.hdforeman.mvp.model.been.LiveBean;
import com.dgg.hdforeman.mvp.model.been.MaterialInfo;
import com.dgg.hdforeman.mvp.ui.widget.CircleImageView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

/**
 * Created by kelvin on 2016/11/8.
 */

public class FieldLiveHolder extends BaseHolder<LiveBean> {

    private FieldLiveContract.View mRootView;

    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.image_avatar)
    CircleImageView  imageAvatar;
    @BindView(R.id.role)
    TextView  role;
    @BindView(R.id.date)
    TextView  date;
    @BindView(R.id.starNumber)
    TextView  starNumber;
    @BindView(R.id.which_stage)
    TextView  whichStage;

    @BindView(R.id.BGANinePhotoLayout)
    BGANinePhotoLayout mBGANinePhotoLayout;

    private HDApplication mApplication;
    private ImageLoader mImageLoader;
    List<String> photos=new ArrayList<String>();
    private LiveBean mInfo;

    public FieldLiveHolder(View itemView, FieldLiveContract.View mRootView) {
        super(itemView);
        this.mRootView = mRootView;
        mApplication = (HDApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
    }

    @Override
    public void setData(LiveBean data) {
        this.mInfo=data;
        mName.setText(data.getPt_wkname());
        role.setText(data.getWk_worktypename());
        date.setText(data.getPt_enddate());
        whichStage.setText(data.getPt_stname());
        mImageLoader.loadImage(mApplication, GlideImageConfig
                .builder()
                .url(CommonUtil.getImageUrl(data.getWk_headpic(), 150, 150))
                .imagerView(imageAvatar)
                .errorPic(R.mipmap.def_logo)
                .build());
        photos.clear();
        if (!TextUtils.isEmpty(data.getPt_picture())) {
            String tmp[]=data.getPt_picture().split(",");
            for (int i=0;i<tmp.length;i++){
                photos.add(  CommonUtil.getImageUrl(tmp[i], 1500, 1500));
            }
            mRootView.configPhotoLayout(mBGANinePhotoLayout,photos);
        }else{
            mBGANinePhotoLayout.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.image_avatar)
    public void onPrevie(View view){
        mRootView.previewImage(mInfo);
    }
}
