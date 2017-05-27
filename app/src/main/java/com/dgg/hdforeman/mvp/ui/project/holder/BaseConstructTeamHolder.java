package com.dgg.hdforeman.mvp.ui.project.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/15.
 */

public abstract class BaseConstructTeamHolder extends RecyclerView.ViewHolder{
    public BaseConstructTeamHolder(View itemView) {
        super(itemView);
        AutoUtils.auto(itemView);
        ButterKnife.bind(this, itemView);
    }

    public abstract void bindItemView(ConstructTeamBean teamBean);
}
