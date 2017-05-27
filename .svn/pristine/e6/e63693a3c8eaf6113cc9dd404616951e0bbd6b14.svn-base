package com.dgg.hdforeman.mvp.ui.mine.holder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.imageload.GlideImageLoader;
import com.dgg.hdforeman.mvp.contract.mine.TeamAddSearchContract;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.ui.widget.CircleImageView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/1.
 */

public class AddTeamHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.item_team_add_listView_head)
    public CircleImageView itemTeamAddListViewHead;
    @BindView(R.id.item_team_add_listView_name)
    public TextView itemTeamAddListViewName;
    @BindView(R.id.item_team_add_listView_phoneBtn)
    ImageView itemTeamAddListViewPhoneBtn;

    private Activity mActivity;
    private Context mContext;
    private HDApplication application;
    private ImageLoader mImageLoader;

    public AddTeamHolder(Context context, View itemView) {
        super(itemView);
        this.mActivity=(Activity) context;
        this.mContext=context;
        application = (HDApplication) itemView.getContext().getApplicationContext();
        AutoUtils.auto(itemView);
        ButterKnife.bind(this, itemView);
        this.mImageLoader=application.getAppComponent().imageLoader();
    }

    public void bindItemView(final TeamSearchResult teamSearchResult,final TeamAddSearchContract.NetUtil netUtil) {
        itemTeamAddListViewName.setText(teamSearchResult.getWk_name());
        itemTeamAddListViewPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netUtil.addTeamUserUtil(teamSearchResult.getWk_userid(),getLayoutPosition());

            }
        });
        mImageLoader.loadImage(mContext,
                GlideImageConfig
                        .builder()
                        .url(GlideImageLoader.getImageUrl(teamSearchResult.getWk_headpic(),"100","100"))
                        .imagerView(itemTeamAddListViewHead)
                        .errorPic(R.mipmap.default_image)
                        .build());
    }
}
