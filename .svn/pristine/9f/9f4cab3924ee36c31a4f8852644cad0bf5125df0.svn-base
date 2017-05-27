package com.dgg.hdforeman.mvp.ui.project.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.imageload.GlideImageLoader;
import com.dgg.hdforeman.app.utils.imageload.ImageLoaderUtil;
import com.dgg.hdforeman.mvp.contract.project.AddMembersContract;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.dgg.hdforeman.mvp.ui.mine.holder.BaseAbstractViewHolder;
import com.dgg.hdforeman.mvp.ui.widget.CircleImageView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/15.
 */

public class AddMemberUserHolder extends BaseAbstractViewHolder {

    @BindView(R.id.iv_mine)
    CircleImageView ivMine;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.ib_add_member)
    ImageButton ibAddMember;

    private Context mContext;
    private HDApplication application;
    private AddMembersContract.NetUtil mNetUtil;
    private ImageLoader mImageLoader;
    public AddMemberUserHolder(Context context, View itemView, AddMembersContract.NetUtil netUtil) {
        super(itemView);
        this.mContext=context;
        application = (HDApplication) itemView.getContext().getApplicationContext();
        this.mNetUtil=netUtil;
        mImageLoader=application.getAppComponent().imageLoader();
    }

    @Override
    public void bindItemView(final TeamBean teamBean) {
        mImageLoader.loadImage(mContext,
                GlideImageConfig
                        .builder()
                        .url(GlideImageLoader.getImageUrl(teamBean.getTeamUserList().get(0).getWk_headpic(),"100","100"))
                        .imagerView(ivMine)
                        .errorPic(R.mipmap.default_image)
                        .build());
        tvMineName.setText(teamBean.getTeamUserList().get(0).getWk_name());
        ibAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNetUtil.addTeamMemberUtil(teamBean);
            }
        });
    }
}
