package com.dgg.hdforeman.mvp.contract.mine;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.Team;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.dgg.hdforeman.mvp.ui.base.ListBaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/1.
 */

public interface TeamContract {
    interface View extends ListBaseView {
        void bindDataToRecycleView(List<TeamBean> teamList);
    }
    interface Model {
        List<TeamBean> getMineTeamListData(List<Team> list);
        Observable<BaseData<List<Team>>> getMineTeamListDatas();
    }
}
