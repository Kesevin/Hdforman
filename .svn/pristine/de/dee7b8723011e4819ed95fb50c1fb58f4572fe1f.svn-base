package com.dgg.hdforeman.mvp.ui.income.adapter;

import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.IncomeDetail;
import com.dgg.hdforeman.mvp.ui.income.holder.IncomeDetailHolder;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;

import java.util.List;


public class IncomeDetailAdapter extends BaseAdapter<IncomeDetail> {

	public IncomeDetailAdapter(List<IncomeDetail> infos) {
		super(infos);
	}

	@Override
	public BaseHolder getHolder(View v) {
		return new IncomeDetailHolder(v);
	}

	@Override
	public int getLayoutId() {
		return R.layout.income_list_item;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		ViewHolder mHolder=null;
//
//		if (convertView == null) {
//					mHolder = new ViewHolder();
//					convertView = mInflater.inflate(R.layout.income_list_item, null);
//					ButterKnife.bind(mHolder, convertView);
//					convertView.setTag(mHolder);
//
//		}else{
//					mHolder = (ViewHolder) convertView.getTag();
//		}
//			return convertView;
//	}

//	public  class ViewHolder{
//
//		@BindView(R.id.project_item)
//		TextView projectItem;
//		@BindView(R.id.project_time)
//		TextView projectTime;
//		@BindView(R.id.will_time_layout)
//		AutoLinearLayout willTimeLayout;
//		@BindView(R.id.no_pay)
//		TextView noPay;
//		@BindView(R.id.money_lab)
//		TextView moneyLab;
//		@BindView(R.id.balance)
//		TextView balance;
//		@BindView(R.id.variability_money)
//		TextView variabilityMoney;
//		@BindView(R.id.state)
//		TextView state;
//
//
//	}
}
