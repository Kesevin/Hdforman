package com.dgg.hdforeman.mvp.contract.mine;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.TeamAdd;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.ui.base.ListBaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/27.
 */

public interface TeamAddSearchContract {
    interface View extends ListBaseView {
        String getSearchCondition();//获取输入框搜索条件
        void bindDataToListView(List<TeamSearchResult> listTeamAdd);
        void removeAdd(int position);
    }

    interface Model {
        Observable<BaseData<List<TeamSearchResult>>> getSearchTeamData(String searchCondition);
        Observable<TeamAdd> addTeamUser(Long grid);
//        Observable<BaseData<List<TeamSearchResult>>> getSearchTeamDataAll();
    }

    interface NetUtil{
        void addTeamUserUtil(Long grid,int position);
    }
}
