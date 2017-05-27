package com.dgg.hdforeman.mvp.ui.project.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.imageload.GlideImageLoader;
import com.dgg.hdforeman.mvp.contract.project.ConstructTeamContract;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.dgg.hdforeman.mvp.ui.widget.CircleImageView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/15.
 */

public class ConstructTeamUserHolder extends BaseConstructTeamHolder {

    @BindView(R.id.iv_mine)
    CircleImageView ivMine;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.ib_delete_member)
    ImageButton ibDeleteMember;
    private Context mContext;
    private ConstructTeamContract.NetUtil mNetUtil;
    private HDApplication mHDApplication;
    private ImageLoader mImageLoader;

    public ConstructTeamUserHolder(Context context, View itemView, ConstructTeamContract.NetUtil netUtil) {
        super(itemView);
        this.mContext=context;
        this.mNetUtil=netUtil;
        mHDApplication= (HDApplication) itemView.getContext().getApplicationContext();
        mImageLoader=mHDApplication.getAppComponent().imageLoader();
    }

    @Override
    public void bindItemView(final ConstructTeamBean constructTeamBean) {
        mImageLoader.loadImage(mContext,
                GlideImageConfig
                        .builder()
                        .url(GlideImageLoader.getImageUrl(constructTeamBean.getCcnstructTeamUserList().get(0).getWk_headpic(),"100","100"))
                        .imagerView(ivMine)
                        .errorPic(R.mipmap.default_image)
                        .build());
        tvMineName.setText(constructTeamBean.getCcnstructTeamUserList().get(0).getPs_wkname());
        ibDeleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNetUtil.deleteTeamMemberUtil(constructTeamBean);
            }
        });
    }

}
