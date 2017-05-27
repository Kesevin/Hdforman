package com.dgg.hdforeman.mvp.presenter.mine;

import android.util.Log;

import com.dgg.hdforeman.app.utils.ToastUtils;
import com.dgg.hdforeman.mvp.contract.mine.ScoreContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.ScoreBean;
import com.jess.arms.di.scope.ActivityScope;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/9.
 */
@ActivityScope
public class MineScorePresenter extends com.jess.arms.mvp.BasePresenter<ScoreContract.Model,ScoreContract.View>{

    private int pageStart=0;

    @Inject
    public MineScorePresenter(ScoreContract.Model model, ScoreContract.View rootView) {
        super(model, rootView);
    }

    public void getScoreListData(final boolean isPull, final int pageSize){
        if(isPull) pageStart=0;
        mModel.getScoreListData(pageStart,pageSize)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if(isPull){
                            mRootView.showLoading();
                        }else{
                            mRootView.startLoadMore();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        if(isPull){
                            mRootView.hideLoading();
                        }else{
                            mRootView.endLoadMore();
                        }
                    }
                })
                .subscribe(new Action1<BaseData<List<ScoreBean>>>() {
                    @Override
                    public void call(BaseData<List<ScoreBean>> listBaseData) {
                        if(listBaseData.isSuccess()){
                            if (listBaseData.getD()==null || (listBaseData.getD()!=null && listBaseData.getD().size()<pageSize)) {
                                mRootView.setLoadEnd(true);
                            }else{
                                mRootView.setLoadEnd(false);
                            }
                            if(pageStart==0 && (listBaseData.getD()==null || (listBaseData.getD()!=null && listBaseData.getD().size()==0))){
                                mRootView.showNoData();
                            }else{
                                mRootView.hintNodata();
                            }
                            mRootView.bindDataToRecylerView(listBaseData.getD());
                            pageStart+=pageSize;
                        }else{
                            ToastUtils.showToast(listBaseData.getMsg());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d("error",throwable.getMessage());
                        ToastUtils.showToast("网络连接失败，请稍后重试");
                    }
                });
    }
}
