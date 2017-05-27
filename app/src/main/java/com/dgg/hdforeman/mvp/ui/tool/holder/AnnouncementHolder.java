package com.dgg.hdforeman.mvp.ui.tool.holder;

import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.AnnouncementBean;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/11/2.
 */

public class AnnouncementHolder extends BaseHolder<AnnouncementBean>{
    @BindView(R.id.anno_title)
    TextView annoTitle;
    @BindView(R.id.anno_time)
    TextView annoTime;

    public AnnouncementHolder(View view) {
        super(view);
        AutoUtils.auto(view);
        ButterKnife.bind(this,view);
    }

    @Override
    public void setData(AnnouncementBean data) {
        annoTitle.setText(data.getNt_title());
        SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(data.getNt_sendtime());
            String str = sdf.format(date);
            annoTime.setText(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
