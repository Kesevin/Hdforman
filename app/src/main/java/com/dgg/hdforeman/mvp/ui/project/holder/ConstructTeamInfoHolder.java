package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.contract.project.ConstructTeamContract;
import com.dgg.hdforeman.mvp.model.been.WorkerBean;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by kelvin on 2016/11/3.
 */

public class ConstructTeamInfoHolder extends BaseHolder<WorkerBean> {

    private ConstructTeamContract.View mRootView;
    private WorkerBean mWorkerBean;

    @BindView(R.id.tv_mine_name)
    TextView mWorkerlName;
    public ConstructTeamInfoHolder(View itemView,ConstructTeamContract.View mRootView) {
        super(itemView);
        this.mRootView = mRootView;
    }

    @Override
    public void setData(WorkerBean data) {
        this.mWorkerBean = data;
        mWorkerlName.setText(data.getWorkerInfo().getPs_wkname());
    }

    @OnClick(R.id.ib_delete_member)
    public void onClicks(View v){
        mRootView.deleteWorker(mWorkerBean);
    }
}
