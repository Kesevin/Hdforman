package com.dgg.hdforeman.mvp.ui.project.holder;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.model.been.UpgradePackageBean;
import com.jess.arms.utils.CharactorHandler;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;

/**
 * Created by kelvin on 2016/11/15.
 */

public class UpgradePackageHolder extends BaseHolder<UpgradePackageBean> {

    private final HDApplication mApplication;

    @Nullable
    @BindView(R.id.pu_ugname)
    TextView mName;
    @Nullable
    @BindView(R.id.pu_ugprice)
    TextView mPrice;
    @Nullable
    @BindView(R.id.upgradeLayout)
    AutoLinearLayout mAutoLinearLayout;

    public UpgradePackageHolder(View itemView) {
        super(itemView);
        mApplication = (HDApplication) itemView.getContext().getApplicationContext();
//        ViewGroup.LayoutParams params = itemView.getLayoutParams();
//        params.height = DensityUtil.getPercentHeightSize(mAutoLinearLayout.getLayoutParams().height);
//        itemView.setLayoutParams(params);
    }

    @Override
    public void setData(UpgradePackageBean data) {
        mName.setText(data.getPu_ugname());
//        mPrice.setText(data.getPu_ugprice());
        mPrice.setText( CharactorHandler.excludeEmpty(data.getPu_number())+data.getPu_ugunit());
    }
}
