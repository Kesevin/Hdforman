package com.dgg.hdforeman.mvp.ui.income.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.TimeUtil;
import com.dgg.hdforeman.mvp.model.been.IncomeWaitForPayment;
import com.dgg.hdforeman.mvp.ui.project.holder.BaseHolder;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dgg.hdforeman.app.utils.TimeUtil.YEAR_MONTH_DAY;

/**
 * Created by Administrator on 2016/11/16.
 */

public class WaitForPaymentHolder extends BaseHolder<IncomeWaitForPayment>{
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
    @BindView(R.id.time_lab)
    TextView timeLab;
    @BindView(R.id.money_lab)
    TextView moneyLab;
    private int state;

    public WaitForPaymentHolder(View itemView,int state) {
        super(itemView);
        AutoUtils.auto(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(this);
        this.state=state;
    }

    @Override
    public void setData(IncomeWaitForPayment incomeWaitForPayment) {
        neighbourhoods.setText(incomeWaitForPayment.getHousesname());
        floor.setText(incomeWaitForPayment.getHousesaddress());
        willPayTime.setText(TimeUtil.keepTimeYMD(incomeWaitForPayment.getCreate_time(),YEAR_MONTH_DAY));
        willGetMoney.setText("¥"+CommonUtil.formatMoney(incomeWaitForPayment.getMoney(),2));
        switch (state){
            case 1://待到款
//                rightArrow.setVisibility(View.GONE);
                variabilityMoney.setVisibility(View.GONE);
                if(incomeWaitForPayment.getPaystate()==1){//待到款（已付款）
                    willPayTime.setVisibility(View.VISIBLE);
                    timeLab.setText("预计到款时间:");
                    moneyLab.setText("预计到款:");
                    timeLab.setTextColor(Color.parseColor("#666666"));
                }else{//待到款（未付款）
                    timeLab.setText("业主未付款");
                    timeLab.setTextColor(Color.parseColor("#FA4F4F"));
                    willPayTime.setVisibility(View.GONE);
                }
                break;
            case 2://已到款
                variabilityMoney.setVisibility(View.GONE);
                timeLab.setText("到款时间:");
                moneyLab.setText("到款:");
                timeLab.setTextColor(Color.parseColor("#666666"));
                rightArrow.setVisibility(View.VISIBLE);
                willPayTime.setVisibility(View.VISIBLE);
                break;
            case 3://增扣款明细
                rightArrow.setVisibility(View.GONE);
                variabilityMoney.setVisibility(View.VISIBLE);
                timeLab.setText("增扣时间:");
                timeLab.setTextColor(Color.parseColor("#666666"));
                variabilityMoney.setText("+ ¥"+CommonUtil.formatMoney(incomeWaitForPayment.getMoney(),2));//增款
                if(incomeWaitForPayment.getJsproperty().equals("3")){//扣款
                    variabilityMoney.setText("- ¥"+CommonUtil.formatMoney(incomeWaitForPayment.getMoney(),2));
                }
                moneyLab.setText("增扣原因:");
                willGetMoney.setText(incomeWaitForPayment.getRemarks());
                break;
        }
    }

}
