package com.dgg.hdforeman.mvp.model.mine;

import android.app.Application;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.contract.mine.TeamContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.Team;
import com.dgg.hdforeman.mvp.model.been.TeamBean;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.model.been.TeamTypeNameBean;
import com.dgg.hdforeman.mvp.model.net.ApiUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/11/1.
 */

public class TeamModel implements TeamContract.Model {
    private Application mApplication;
    private AppComponent mAppComponent;

    public TeamModel(HDApplication app) {
        this.mApplication = app;
        this.mAppComponent = app.getAppComponent();
    }

    @Override
    public List<TeamBean> getMineTeamListData(List<Team> list) {
        List<TeamBean> teamList=new ArrayList<>();
        for (Team team: list) {
            TeamBean teamBean = new TeamBean();
            teamBean.setTeamType("0");
            List<TeamTypeNameBean> teamTypeNameList = new ArrayList<TeamTypeNameBean>();
            TeamTypeNameBean teamTypeNameBean = new TeamTypeNameBean();
            teamTypeNameBean.setTeamMemberNumber(team.getCount());
            teamTypeNameBean.setTeamName(team.getTypename());
            teamTypeNameList.add(teamTypeNameBean);
            teamBean.setTeamTypeList(teamTypeNameList);
            teamList.add(teamBean);
            for (TeamSearchResult teamSearchResult: team.getMember()) {
                TeamBean teamBean2 = new TeamBean();
                teamBean2.setTeamType("1");
                List<TeamSearchResult> teamSearchResultList = new ArrayList<>();
                teamSearchResultList.add(teamSearchResult);
                teamBean2.setTeamUserList(teamSearchResultList);
                teamList.add(teamBean2);
            }
        }
        return teamList;
    }

    @Override
    public Observable<BaseData<List<Team>>> getMineTeamListDatas() {
        return mAppComponent.serviceManager().getCommonService().teamAll(ApiUtil.getParams("ForemanApi","selectForemanMember",new JSONObject()));
    }
}
