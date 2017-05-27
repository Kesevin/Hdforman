package com.dgg.hdforeman.mvp.ui.tool.adapter;

import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.AnnouncementBean;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.dgg.hdforeman.mvp.ui.tool.holder.AnnouncementHolder;

import java.util.List;

public class AnnouncementAdapter extends BaseAdapter<AnnouncementBean> {

	public AnnouncementAdapter(List<AnnouncementBean> infos) {
		super(infos);
	}

	@Override
	public BaseHolder<AnnouncementBean> getHolder(View v) {
		return new AnnouncementHolder(v);
	}

	@Override
	public int getLayoutId() {
		return R.layout.announcement_list_item;
	}
}
