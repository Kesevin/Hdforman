package com.dgg.hdforeman.mvp.ui.project.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.project.MaterialBagContract;
import com.dgg.hdforeman.mvp.model.been.MaterialInfo;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by kelvin on 2016/11/3.
 */

public class ConstructMaterialInfoHolder extends BaseHolder<MaterialInfo> {

    private final HDApplication mApplication;
    private ImageLoader mImageLoader;
    private View itemView;
    @BindView(R.id.material_image)
    ImageView materialImage;
    @BindView(R.id.material_name)
    TextView materialName;
    @BindView(R.id.material_capacity)
    TextView materialCapacity;
    @BindView(R.id.material_number)
    TextView materialNumber;
    @BindView(R.id.btn_delivery_contact)
    TextView btnDeliveryDontact;

    private MaterialBagContract.View mRootView;
    private  MaterialInfo mInfo;

    public ConstructMaterialInfoHolder(View itemView,MaterialBagContract.View mRootView) {
        super(itemView);
        this.mRootView=mRootView;
        mApplication = (HDApplication) itemView.getContext().getApplicationContext();
        mImageLoader = mApplication.getAppComponent().imageLoader();
        this.itemView = itemView;
    }

    @Override
    public void setData(MaterialInfo data) {
       this.mInfo=data;
        materialName.setText(data.getName());
        materialCapacity.setText("规格:"+data.getStandard());
        materialNumber.setText("数量:"+data.getNumber()+(TextUtils.isEmpty(data.getUnit())?"":data.getUnit()));

        mRootView.getTabItem().initItemView(itemView, data);
//        mImageLoader.loadImage(mApplication, GlideImageConfig
//                .builder()
//                .url(CommonUtil.getImageUrl(data.getAccessory(), 150, 150))
//                .imagerView(materialImage)
//                .errorPic(R.mipmap.default_project_icon)
//                .build());

    }
    @OnClick(R.id.btn_delivery_contact)
    public void onClicks(View view){
        mRootView.callPhone(mInfo);
    }
    @OnClick(R.id.material_image)
    public void onPrevie(View view){
        mRootView.previewImage(mInfo,getLayoutPosition());
    }
}
