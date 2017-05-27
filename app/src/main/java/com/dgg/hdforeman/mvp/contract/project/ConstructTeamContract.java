package com.dgg.hdforeman.mvp.contract.project;

import android.content.Intent;

import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.ConstructTeam;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.dgg.hdforeman.mvp.model.been.WorkerBean;
import com.dgg.hdforeman.mvp.ui.base.ListBaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by kelvin on 2016/11/2.
 */

public interface ConstructTeamContract {
    interface View extends ListBaseView {
        void launchActivity(Intent intent);
        void deleteWorker(WorkerBean workerBean);
        void bindDataToView(List<ConstructTeamBean> data);
        void refreshAdapter(ConstructTeamBean constructTeamBean);
    }


    interface Model {
        Observable<BaseData<List<ConstructTeam>>> getConstructTeamList(String proId);
        List<ConstructTeamBean> getConstructTeamListData(List<ConstructTeam> list);
        Observable<BaseData> deleteTeamMember(String proId,ConstructTeamBean constructTeamBean);
    }

    interface NetUtil{
        void deleteTeamMemberUtil(ConstructTeamBean constructTeamBean);
    }

}
