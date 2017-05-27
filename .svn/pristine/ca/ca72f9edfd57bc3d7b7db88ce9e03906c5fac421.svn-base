package com.dgg.hdforeman.mvp.ui.income.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ChargebackAdapter extends BaseAdapter {

	private Context mContext;

	private List mData;
	private LayoutInflater mInflater;
	public ChargebackAdapter(Context mContext, List mData) {
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
		mHolder.rightArrow.setVisibility(View.GONE);
		mHolder.timeLab.setText("増扣时间:");
		mHolder.moneyLab.setText("増扣原因:");
			return convertView;
	}
	public void updateItemView(ListView listView, int position) {
		int index = position - listView.getFirstVisiblePosition();
		if (index >= 0 && index < listView.getChildCount()) {
			View itemView = listView.getChildAt(index);
			getView(position, itemView, listView);
		}
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
		@BindView(R.id.money_lab)
		TextView moneyLab;
		@BindView(R.id.time_lab)
		TextView timeLab;
		@BindView(R.id.will_get_money)
		TextView willGetMoney;
		@BindView(R.id.variability_money)
		TextView variabilityMoney;

		
	}
}
