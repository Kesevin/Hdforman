package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.ConstructProgressContract;
import com.dgg.hdforeman.mvp.model.been.ConstructProgressBean;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructProgressHolder;
import com.dgg.hdforeman.mvp.ui.project.holder.ConstructProgressInfoHolder;

import java.util.List;


/**
 * Created by kelvin on 2016/11/7.
 */

public class ConstructProgressAdapter extends BaseAdapter<ConstructProgressBean> {

    private ConstructProgressContract.View mRootView;

    public ConstructProgressContract.View getRootView() {
        return mRootView;
    }

    public void setRootView(ConstructProgressContract.View rootView) {
        mRootView = rootView;
    }

    public ConstructProgressAdapter(List<ConstructProgressBean> infos) {
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
        return mInfos.get(position).getType() == 0 ? 0 : 1;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
            BaseHolder holder = new ConstructProgressHolder(view, getRootView());
            holder.setOnItemClickListener(new BaseHolder.OnViewClickListener() {//设置Item点击事件
                @Override
                public void onViewClick(View view, int position) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, mInfos.get(position), position);
                    }
                }
            });

            return holder;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_sub_item, parent, false);
            BaseHolder holder = new ConstructProgressInfoHolder(view,getRootView());
            holder.setOnItemClickListener(new BaseHolder.OnViewClickListener() {//设置Item点击事件
                @Override
                public void onViewClick(View view, int position) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, mInfos.get(position), position);
                    }
                }
            });
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            holder.setData(mInfos.get(position));
        } else {
            holder.setData(mInfos.get(position));
        }
    }


}
