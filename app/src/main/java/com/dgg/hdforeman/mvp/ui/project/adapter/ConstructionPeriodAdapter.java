package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.ConstructProgressContract;
import com.dgg.hdforeman.mvp.model.been.ConstructProgressBean;
import com.dgg.hdforeman.mvp.model.been.ImageInfo;
import com.dgg.hdforeman.mvp.model.been.ProStage;
import com.dgg.hdforeman.mvp.ui.project.holder.AddMemberItemModel_;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructPeriodChildHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructPeriodParentHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructProgressHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructProgressInfoHolder;

import java.util.List;
import java.util.Objects;


/**
 * Created by kelvin on 2016/11/7.
 */

public class ConstructionPeriodAdapter extends BaseAdapter<Object> {

    private ConstructionPeriodAdapter.OnItemClickListener mOnItemClickListener;


    public ConstructionPeriodAdapter(List<Object> infos) {
        super(infos);
    }

    @Override
    public BaseHolder getHolder(View v) {
        return null;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }


    /**
     * type == 0 代表group的布局
     * type == 1 代表item的布局
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
      if( mInfos.get(position) instanceof ProStage)
        return 0;
        else
          return 1;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
            BaseHolder holder = new ConstructPeriodParentHolder(view, mOnItemClickListener);
            return holder;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_sub_item, parent, false);
            BaseHolder holder = new ConstructPeriodChildHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
            holder.setData(mInfos.get(position));
    }

    public void setItemClickListener(ConstructionPeriodAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public interface OnItemClickListener{
        void onItem(View view,int position,Object mData);
    }

}
