package com.dgg.hdforeman.mvp.ui.project.holder;

import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/15.
 */

public class ConstructTeamTypeHolder extends BaseConstructTeamHolder{

    @BindView(R.id.item_team_listView_teamType)
    TextView tvMemberTitle;

    public ConstructTeamTypeHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindItemView(ConstructTeamBean constructTeamBean) {
        tvMemberTitle.setText(constructTeamBean.getCcnstructTeamTypeList().get(0).getTeamName()
                +" ("+constructTeamBean.getCcnstructTeamTypeList().get(0).getTeamMemberNumber()+")");
    }
}
