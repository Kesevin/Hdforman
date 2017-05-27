package com.dgg.hdforeman.mvp.ui.income.adapter;

import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.IncomeWaitForPayment;
import com.dgg.hdforeman.mvp.ui.income.holder.WaitForPaymentHolder;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;

import java.util.List;


public class WaitPayAdapter extends BaseAdapter<IncomeWaitForPayment> {

	private int state;

	public WaitPayAdapter(List<IncomeWaitForPayment> infos,int state) {
		super(infos);
		this.state=state;
	}

	@Override
	public BaseHolder getHolder(View v) {
		return new WaitForPaymentHolder(v,state);
	}

	@Override
	public int getLayoutId() {
		return R.layout.wait_pay_list_item;
	}

}
