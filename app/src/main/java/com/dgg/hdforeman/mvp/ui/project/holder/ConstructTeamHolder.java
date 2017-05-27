package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.WorkerBean;

import butterknife.BindView;


/**
 * Created by kelvin on 2016/11/3.
 */

public class ConstructTeamHolder extends BaseHolder<WorkerBean> {


    @BindView(R.id.tv_members_title)
    TextView mWorkerTypeName;

    public ConstructTeamHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(WorkerBean data) {
        mWorkerTypeName.setText(data.getWorker().getName());
    }
}
