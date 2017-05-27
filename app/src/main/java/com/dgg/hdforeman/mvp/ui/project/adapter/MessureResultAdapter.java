package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;

import java.util.List;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class MessureResultAdapter extends BaseQuickAdapter<SpaceBean.SpacenameEntity,BaseViewHolder>{
    public MessureResultAdapter(List<SpaceBean.SpacenameEntity> data) {
        super(R.layout.item_messureresult, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SpaceBean.SpacenameEntity spacenameEntity) {
        if (baseViewHolder.getAdapterPosition()==mData.size()-1){
            //隐藏分割线
            baseViewHolder.getView(R.id.divider_grey).setVisibility(View.INVISIBLE);
        }else {
            baseViewHolder.getView(R.id.divider_grey).setVisibility(View.VISIBLE);
        }
        baseViewHolder.setText(R.id.tv_pname, spacenameEntity.getPe_name());
        baseViewHolder.setText(R.id.tv_metre,spacenameEntity.getPe_acreage()+"m²");
    }
}
