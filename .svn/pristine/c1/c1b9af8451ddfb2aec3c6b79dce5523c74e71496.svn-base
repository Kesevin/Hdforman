package com.dgg.hdforeman.mvp.ui.income.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MoneyArrivedAdapter extends BaseAdapter {

	private Context mContext;

	private List mData;
	private LayoutInflater mInflater;
	public MoneyArrivedAdapter(Context mContext, List mData) {
		this.mContext = mContext;
		this.mData = mData;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder=null;

		if (convertView == null) {
					mHolder = new ViewHolder();
					convertView = mInflater.inflate(R.layout.wait_pay_list_item, null);
					ButterKnife.bind(mHolder, convertView);
					convertView.setTag(mHolder);

		}else{
					mHolder = (ViewHolder) convertView.getTag();
		}
		mHolder.variabilityMoney.setVisibility(View.GONE);
			return convertView;
	}

	public  class ViewHolder{

		@BindView(R.id.neighbourhoods)
		TextView neighbourhoods;
		@BindView(R.id.floor)
		TextView floor;
		@BindView(R.id.will_pay_time)
		TextView willPayTime;
		@BindView(R.id.right_arrow)
		TextView rightArrow;
		@BindView(R.id.will_time_layout)
		AutoLinearLayout willTimeLayout;
		@BindView(R.id.no_pay)
		TextView noPay;
		@BindView(R.id.will_get_money)
		TextView willGetMoney;
		@BindView(R.id.variability_money)
		TextView variabilityMoney;

		
	}
}
