package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.model.been.ProjectPriceBean;
import com.jess.arms.utils.CharactorHandler;

import butterknife.BindView;

/**
 * Created by kelvin on 2016/11/15.
 */

public class ProjectListPriceHolder extends BaseHolder<ProjectPriceBean> {

    private HDApplication mApplication;

    @BindView(R.id.pu_ugname)
    TextView mName;
    @BindView(R.id.pu_ugprice)
    TextView mPrice;

    public ProjectListPriceHolder(View itemView) {
        super(itemView);
        mApplication = (HDApplication) itemView.getContext().getApplicationContext();
    }

    @Override
    public void setData(ProjectPriceBean data) {
        mName.setText(CharactorHandler.excludeEmpty(data.getChargeItem()));
        mPrice.setTextColor(mApplication.getResources().getColor(R.color.textRedColor));
        mPrice.setText(CharactorHandler.excludeEmpty(data.getTotalPrice()) + "元");
    }
}
