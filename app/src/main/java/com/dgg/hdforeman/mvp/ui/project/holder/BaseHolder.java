package com.dgg.hdforeman.mvp.ui.project.holder;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;


/**
 * Created by kelvin on 2016/11/2.
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected OnViewClickListener mOnViewClickListener = null;
    protected final String TAG = this.getClass().getSimpleName();

    public BaseHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);//点击事件
        AutoUtils.autoSize(itemView);//适配
        bindTarget(this, itemView);//绑定
        if (isBindEventBus()) {
            EventBus.getDefault().register(this);//注册eventbus
        }
    }

    protected boolean isBindEventBus(){
        return false;
    }

    /**
     * 设置数据
     * 刷新界面
     *
     * @param
     */
    public abstract void setData(T data);

    @Override
    public void onClick(View view) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener.onViewClick(view, this.getPosition());
        }
    }

    public interface OnViewClickListener {
        void onViewClick(View view, int position);
    }

    public void setOnItemClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }

    public void bindTarget(Object target, Object source) {
        if (source instanceof Activity) {
            ButterKnife.bind(target, (Activity) source);
        } else if (source instanceof View) {
            ButterKnife.bind(target, (View) source);
        } else if (source instanceof Dialog) {
            ButterKnife.bind(target, (Dialog) source);
        }
    }
}
