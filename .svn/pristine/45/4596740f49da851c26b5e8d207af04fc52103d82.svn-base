package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;
import android.view.View;

import com.dgg.hdforeman.mvp.contract.project.FieldLiveContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.LiveBean;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.FieldLiveAdapter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by kelvin on 2016/11/8.
 */

@ActivityScope
public class FieldLivePresenter extends com.jess.arms.mvp.BasePresenter<FieldLiveContract.Model, FieldLiveContract.View> {
    private RxErrorHandler mErrorHandler;
    private List<LiveBean> mDatas = new ArrayList<>();
    private FieldLiveAdapter mAdapter;
    private String proid;
    int pageStart  ;
    int pageSize=10;

    @Inject
    public FieldLivePresenter(FieldLiveContract.Model model, FieldLiveContract.View view, RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader) {
        super(model, view);
        this.mErrorHandler = handler;
    }

    public void initAdapter() {
        mAdapter = new FieldLiveAdapter(mDatas);
        mAdapter.setRootView(mRootView);
        mRootView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {

            }
        });
    }

    public void requestList(String proid,final boolean refresh) {
        if (refresh) {
            pageStart = 0;
        }
        mModel.getSiteLiveData(proid,pageStart,pageSize)
                .subscribeOn(Schedulers.io())
//                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (refresh)
                            mRootView.showLoading();//显示上拉刷新的进度条
                        else
                            mRootView.startLoadMore();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (refresh)
                            mRootView.hideLoading();//隐藏上拉刷新的进度条
                        else
                            mRootView.endLoadMore();
                    }
                })
                .compose(((BaseActivity)mRootView).<BaseData<List<LiveBean>>>bindToLifecycle())
                .subscribe(new ErrorHandleSubscriber<BaseData<List<LiveBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData<List<LiveBean>> mData) {
                        if (mData.isSuccess()) {
                            List<LiveBean> tmp = mData.getD();
                            if (refresh){
                                mDatas.clear();
                                if(tmp.size()==0){
                                    mRootView.showNoData();
                                }else if(tmp.size()< pageSize){
                                    mRootView.hideNodata();
                                    mRootView.setIsLoad(true);
                                }else{
                                    mRootView.hideNodata();
                                    mRootView.setIsLoad(false);
                                }
                            }else{
                                if (tmp.size() < pageSize) {
                                    mRootView.setIsLoad(true);
                                }else{
                                    mRootView.setIsLoad(false);
                                }

                            }

                            for(LiveBean liveBean : tmp){
                                mDatas.add(liveBean);
                            }

                            mAdapter.notifyDataSetChanged();
                            pageStart+=pageSize;
                        }else{
                            mRootView.showMessage(mData.getMsg());
                        }
                    }
                });

    }

//    public void upLoadPicture(Intent data){
//
////        LiveBean liveBean = new LiveBean();
////        liveBean.setName("王思聪");
////        List<String> imgUrl = data.getStringArrayListExtra(HAS_SELECTED_IMG);
////        liveBean.setImgUrlList(imgUrl);
////
////        mDatas.add(0,liveBean);
//
//        mAdapter.notifyDataSetChanged();
//    }
}
