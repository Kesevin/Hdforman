package com.dgg.hdforeman.mvp.presenter.tool;

import com.dgg.hdforeman.app.utils.ToastUtils;
import com.dgg.hdforeman.mvp.contract.tool.AnnouncementContract;
import com.dgg.hdforeman.mvp.model.been.AnnouncementBean;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/2.
 */

@ActivityScope
public class AnnouncementPresenter extends BasePresenter<AnnouncementContract.Model,AnnouncementContract.View> {

    private static final String TAG = "AnnouncementPresenter";
    private int pageStart=0;

    @Inject
    public AnnouncementPresenter(AnnouncementContract.Model model,AnnouncementContract.View view) {
        super(model,view);
    }

    /**
     *
     * @param isPull 是否是下拉刷新  false加载更多，true刷新
     * @param pageSize
     */
    public void getAnnouncementListData(final boolean isPull, final int pageSize){
        if(isPull) pageStart=0;
        mModel.getAnnouncementListData(pageStart,pageSize)
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
                .subscribe(
                        new Action1<BaseData<List<AnnouncementBean>>>() {
                            @Override
                            public void call(final BaseData<List<AnnouncementBean>> listBaseData) {
                                if (listBaseData.isSuccess()) {
                                    if (listBaseData.getD().size()<pageSize) {
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
                                } else {
                                    ToastUtils.showToast(listBaseData.getMsg());
                                }
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                ToastUtils.showToast("网络连接失败，请稍后再试");
                            }
                        });

    }
}
