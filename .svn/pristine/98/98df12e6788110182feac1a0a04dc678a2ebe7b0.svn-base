package com.dgg.hdforeman.mvp.ui.mine.holder;

import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.model.been.TeamBean;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/11/1.
 */

public class TeamTypeHolder extends BaseAbstractViewHolder{
    @BindView(R.id.item_team_listView_teamType)
    TextView itemTeamListViewTeamType;
    public TeamTypeHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindItemView(TeamBean teamBean) {
        itemTeamListViewTeamType.setText(teamBean.getTeamTypeList().get(0).getTeamName()+" ("+teamBean.getTeamTypeList().get(0)
                .getTeamMemberNumber()+")");
    }
}
