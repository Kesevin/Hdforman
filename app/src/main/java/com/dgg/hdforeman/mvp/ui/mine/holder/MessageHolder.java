package com.dgg.hdforeman.mvp.ui.mine.holder;

import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.MessageBean;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/2.
 */

public class MessageHolder extends BaseHolder<MessageBean> {

    @BindView(R.id.message_date)
    TextView messageDate;
    @BindView(R.id.message_title)
    TextView messageTitle;
    @BindView(R.id.message_content)
    TextView messageContent;

    public MessageHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(MessageBean data) {
        messageTitle.setText(data.getTypeName());
        messageDate.setText(data.getCreatTime());
        messageContent.setText(data.getContent());
    }
}
