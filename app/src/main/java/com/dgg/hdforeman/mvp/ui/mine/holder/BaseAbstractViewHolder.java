package com.dgg.hdforeman.mvp.ui.mine.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/1.
 */

public abstract class BaseAbstractViewHolder extends RecyclerView.ViewHolder{
    public BaseAbstractViewHolder(View itemView) {
        super(itemView);
        AutoUtils.auto(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bindItemView(TeamBean teamBean);
}
