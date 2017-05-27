package com.dgg.hdforeman.mvp.ui.income.holder;

import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.model.been.IncomeDetail;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/17.
 */

public class IncomeDetailHolder extends BaseHolder<IncomeDetail>{

    @BindView(R.id.project_item)
    TextView projectItem;
    @BindView(R.id.project_time)
    TextView projectTime;
    @BindView(R.id.will_time_layout)
    AutoLinearLayout willTimeLayout;
    @BindView(R.id.no_pay)
    TextView noPay;
    @BindView(R.id.money_lab)
    TextView moneyLab;
    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.variability_money)
    TextView variabilityMoney;
    @BindView(R.id.deal_state)
    TextView dealState;

    public IncomeDetailHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(IncomeDetail data) {
        projectItem.setText(data.getIn_remark());
        balance.setText(data.getIn_afusable());
        projectTime.setText(data.getCreate_time());
        if(data.getIn_type()==1){
        variabilityMoney.setText("+ ¥ "+CommonUtil.formatMoney(data.getIn_consume(),2));}
        else if(data.getIn_type()==2){
            variabilityMoney.setText("- ¥ "+CommonUtil.formatMoney(data.getIn_consume(),2));
        }else{
            variabilityMoney.setText("¥ "+CommonUtil.formatMoney(data.getIn_consume(),2));}

        if(data.getIn_state()==0){//0失败  1处理中  2处理完成  默认1处理中
            dealState.setVisibility(View.VISIBLE);
            dealState.setText("失败");
        }else if(data.getIn_state()==1){
            dealState.setVisibility(View.VISIBLE);
            dealState.setText("处理中");
        }else{
            dealState.setVisibility(View.GONE);
//            state.setText("处理完成");

        }
    }
}
