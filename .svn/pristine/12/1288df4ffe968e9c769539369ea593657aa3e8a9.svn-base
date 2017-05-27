package com.dgg.hdforeman.mvp.presenter.mine;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.contract.mine.TeamAddSearchContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.TeamAdd;
import com.dgg.hdforeman.mvp.model.been.TeamSearchResult;
import com.dgg.hdforeman.mvp.model.mine.TeamAddSearchModel;
import com.dgg.hdforeman.app.utils.ToastUtils;

import org.simple.eventbus.EventBus;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/27.
 */

public class TeamAddSearchPresenter {
    private TeamAddSearchContract.View mView;
    private TeamAddSearchModel mModel;
    private HDApplication app;

    public TeamAddSearchPresenter(TeamAddSearchContract.View view, HDApplication app) {
        this.mView = view;
        this.mModel = new TeamAddSearchModel(app);
        this.app = app;
    }

//    private void getSearchTeamDataAll(){
//        mModel.getSearchTeamDataAll()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<BaseData<List<TeamSearchResult>>>() {
//                    @Override
//                    public void call(BaseData<List<TeamSearchResult>> listBaseData) {
//                        if (listBaseData.isSuccess()) {
//                            mView.bindDataToListView(listBaseData.getD());
//                        } else {
//                            ToastUtils.showToast(listBaseData.getMsg());
//                        }
//                    }
//                });
//    }

    public void getSearchTeamData() {
        String searchText = mView.getSearchCondition();
        if (searchText.isEmpty()) {
            return;
        }
        mModel.getSearchTeamData(searchText)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mView.hideLoading();
                    }
                })
                .subscribe(new Action1<BaseData<List<TeamSearchResult>>>() {
                    @Override
                    public void call(BaseData<List<TeamSearchResult>> listBaseData) {
                        if (listBaseData.isSuccess()) {
                            mView.bindDataToListView(listBaseData.getD());
                            if(listBaseData.getD()==null || (listBaseData.getD()!=null && listBaseData.getD().size()==0)){
                                mView.showNoData();
                            }else{
                                mView.hintNodata();
                            }
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

    public void addTeamUser(Long grid,final int position) {
        if (grid == null) {
            return;
        }
        mModel.addTeamUser(grid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TeamAdd>() {
                    @Override
                    public void call(TeamAdd teamAdd) {
                        if (teamAdd.getCode().equals("0")) {
                            ToastUtils.showToast("添加成功");
                            mView.removeAdd(position);
                            EventBus.getDefault().post(true,"teamRefresh");
                            mView.killMyself();
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
