package com.dgg.hdforeman.mvp.ui.mine.adapter;

import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.MessageBean;
import com.dgg.hdforeman.mvp.ui.mine.holder.MessageHolder;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2.
 */

public class MessageAdapter extends BaseAdapter<MessageBean> {

    public MessageAdapter(List<MessageBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<MessageBean> getHolder(View v) {
        return new MessageHolder(v);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_message;
    }
}
