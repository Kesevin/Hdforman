package com.dgg.hdforeman.mvp.presenter.mine;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.ToastUtils;
import com.dgg.hdforeman.mvp.contract.mine.TeamAddContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.TeamAdd;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.model.mine.TeamAddModel;
import com.dgg.hdforeman.mvp.model.mine.TeamAddSearchModel;

import org.simple.eventbus.EventBus;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/31.
 */

public class TeamAddPresenter {

    private TeamAddContract.View view;
    private HDApplication app;
    private TeamAddModel mModel;
    private TeamAddSearchModel mSearchModel;
    private int pageStart=0;

    public TeamAddPresenter(TeamAddContract.View view, HDApplication app){
        this.view=view;
        this.app=app;
        mModel=new TeamAddModel(app);
        mSearchModel=new TeamAddSearchModel(app);
    }

    public void getTeamNoForemanData(final boolean isPull,final int pageSize){
        if(isPull) pageStart=0;
        mModel.getTeamNoForemanData(pageStart,pageSize)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if(isPull){
                            view.showLoading();
                        }else{
                            view.startLoadMore();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        if(isPull){
                            view.hideLoading();
                        }else{
                            view.endLoadMore();
                        }
                    }
                })
                .subscribe(new Action1<BaseData<List<TeamSearchResult>>>() {
                    @Override
                    public void call(BaseData<List<TeamSearchResult>> listBaseData) {
                        if (listBaseData.isSuccess()) {
                            if (listBaseData.getD().size()<pageSize) {
                                view.setLoadEnd(true);
                            }else{
                                view.setLoadEnd(false);
                            }
                            if(listBaseData.getD().size()!=0){
                                view.bindTeamDataToListView(listBaseData.getD());
                            }
                            if(pageStart==0 && (listBaseData.getD()==null || (listBaseData.getD()!=null && listBaseData.getD().size()==0))){
                                view.showNoData();
                            }else{
                                view.hintNodata();
                            }
                            pageStart+=pageSize;
                        } else {
                            ToastUtils.showToast(listBaseData.getMsg());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ToastUtils.showToast("网络连接失败，请检查您的网络!");
                    }
                });
    }

    public void addTeamUser(Long grid, final int position){
        if(grid==null){
            return;
        }
        mSearchModel.addTeamUser(grid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TeamAdd>() {
                    @Override
                    public void call(TeamAdd teamAdd) {
                        if (teamAdd.getCode().equals("0")) {
                            ToastUtils.showToast("添加成功");
                            view.removeAdd(position);
                            EventBus.getDefault().post(true,"teamRefresh");
                        } else {
                            ToastUtils.showToast(teamAdd.getMsg());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        ToastUtils.showToast("网络连接失败，请检查您的网络!");
                    }
                });
    }
}
