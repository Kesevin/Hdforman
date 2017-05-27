package com.dgg.hdforeman.mvp.ui.mine.holder;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.imageload.GlideImageLoader;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.dgg.hdforeman.mvp.ui.widget.CircleImageView;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import rx.functions.Action1;


/**
 * Created by Administrator on 2016/11/1.
 */

public class TeamUserListHolder extends BaseAbstractViewHolder{
    @BindView(R.id.item_team_listView_head)
    CircleImageView itemTeamListViewHead;
    @BindView(R.id.item_team_listView_name)
    TextView itemTeamListViewName;
    @BindView(R.id.item_team_listView_phoneBtn)
    Button itemTeamListViewPhoneBtn;
    private Activity mActivity;
    private Context mContext;
    private HDApplication application;
    private ImageLoader mImageLoader;
    public TeamUserListHolder(Context context, View itemView) {
        super(itemView);
        this.mActivity= (Activity) context;
        application = (HDApplication) itemView.getContext().getApplicationContext();
        this.mContext=context;
        this.mImageLoader=application.getAppComponent().imageLoader();
    }

    @Override
    public void bindItemView(TeamBean teamBean) {
        mImageLoader.loadImage(mContext,
                GlideImageConfig
                        .builder()
                        .url(GlideImageLoader.getImageUrl(teamBean.getTeamUserList().get(0).getWk_headpic(),"100","100"))
                        .imagerView(itemTeamListViewHead)
                        .errorPic(R.mipmap.default_image)
                        .build());
        itemTeamListViewName.setText(teamBean.getTeamUserList().get(0).getWk_name());
        itemTeamListViewPhoneBtn.setTag(teamBean.getTeamUserList().get(0).getWk_contactno());
        itemTeamListViewPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber= (String) itemTeamListViewPhoneBtn.getTag();
                callPhone(phoneNumber);
            }
        });
    }

    public void callPhone(final String phone) {
        RxPermissions.getInstance(mContext)
                .request(
                        Manifest.permission.CALL_PHONE
                ).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean) {
                    mContext.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                            + phone)));
                } else {

                }
            }
        });
    }
}
