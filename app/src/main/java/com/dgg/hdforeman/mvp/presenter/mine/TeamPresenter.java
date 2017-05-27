package com.dgg.hdforeman.mvp.presenter.mine;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.contract.mine.TeamContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.Team;
import com.dgg.hdforeman.mvp.model.mine.TeamModel;
import com.dgg.hdforeman.app.utils.ToastUtils;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/1.
 */

public class TeamPresenter {

    private TeamContract.Model model;
    private TeamContract.View view;
    private HDApplication app;

    public TeamPresenter(TeamContract.View view, HDApplication app) {
        this.view=view;
        this.app=app;
        model=new TeamModel(app);
    }

//    public List<TeamBean> getTeamDataList(){
//        return model.getMineTeamListData();
//    }

    public void getTeamDataLists(){
        model.getMineTeamListDatas()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.showLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        view.hideLoading();
                    }
                })
                .subscribe(new Action1<BaseData<List<Team>>>() {
                    @Override
                    public void call(BaseData<List<Team>> listBaseData) {
                        if (listBaseData.isSuccess()) {
                            view.bindDataToRecycleView(model.getMineTeamListData(listBaseData.getD()));
                            if(listBaseData.getD()==null || (listBaseData.getD()!=null && listBaseData.getD().size()==0)){
                                view.showNoData();
                            }else{
                                view.hintNodata();
                            }
                        } else {
                            ToastUtils.showToast(listBaseData.getMsg());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ToastUtils.showToast("网络连接失败，请稍后重试");
                    }
                });
    }
}
