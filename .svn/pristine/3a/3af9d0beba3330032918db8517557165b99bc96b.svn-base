package com.dgg.hdforeman.mvp.presenter.project;


import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.FreeTemContract;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.FreeTermlistBean;
import com.dgg.hdforeman.mvp.ui.project.adapter.FreeTermAdapter;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class FreeTermPresenter extends BasePresenter<FreeTemContract.Model,FreeTemContract.View> implements FreeTermAdapter.OnOptionclickListener {
    private RxErrorHandler mRxerrorHandler;
    private Application mapplication;
    private FreeTermAdapter adapter;
    private List<FreeTermlistBean> datas=new ArrayList<>();
    private BaseJson<List<FreeTermlistBean>> response;
    @Inject
    public FreeTermPresenter(FreeTemContract.Model model, FreeTemContract.View rootView, RxErrorHandler mRxerrorHandler, Application mapplication) {
        super(model, rootView);
        this.mRxerrorHandler = mRxerrorHandler;
        this.mapplication = mapplication;
    }
    public void loadInitdata(String id){
        adapter=new FreeTermAdapter(datas);
        mRootView.initRecylerView(adapter);
        adapter.setListener(this);
        View view= LayoutInflater.from(mapplication).inflate(R.layout.empty_frenterm_list,null,false);
        adapter.setEmptyView(view);
        mModel.getFreeTerms(id).compose(RxUtils.<BaseJson<List<FreeTermlistBean>>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson<List<FreeTermlistBean>>>(mRxerrorHandler) {
                    @Override
                    public void onNext(final BaseJson<List<FreeTermlistBean>> listBaseJson) {
                        response=listBaseJson;
                        if (listBaseJson.isSuccess()){
                            mModel.delall().subscribe(new Action1<Void>() {
                                @Override
                                public void call(Void aVoid) {
                                    //成功的情况
                                    if (datas!=null) {
                                        mModel.insertdatas(listBaseJson.getD()).observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new Action1<Iterable<FreeTermlistBean>>() {
                                                    @Override
                                                    public void call(Iterable<FreeTermlistBean> freeTermlistBeen) {

                                                    }
                                                });
                                        mModel.queryAllFree().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<FreeTermlistBean>>() {
                                            @Override
                                            public void call(List<FreeTermlistBean> freeTermlistBeen) {
                                                adapter.addData(freeTermlistBeen);
                                            }
                                        });
                                    }
                                }
                            });


                        }else {
                            //失败显示服务器返回信息
                            mRootView.showMessage(listBaseJson.getMsg());
                        }
                    }
                });
    }
    public void loadDatafromDb(String id){
        if (adapter==null){
            adapter=new FreeTermAdapter(datas);
            mRootView.initRecylerView(adapter);
            adapter.setListener(this);
            View view= LayoutInflater.from(mapplication).inflate(R.layout.empty_frenterm_list,null,false);
            adapter.setEmptyView(view);
        }
        mModel.queryById(id).compose(RxUtils.<List<FreeTermlistBean>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<List<FreeTermlistBean>>(mRxerrorHandler) {
                    @Override
                    public void onNext(List<FreeTermlistBean> freeTermlistBeen) {
                        if (freeTermlistBeen!=null){
                            adapter.setNewData(freeTermlistBeen);
                        }
                    }
                });
    }
    public Observable<List<FreeTermlistBean>> getFreedatas(String id){
       return mModel.queryById(id);
    }
    @Override
    public void del(final int pos, FreeTermlistBean freeTermlistBean) {
        mRootView.showMessage("删除成功");
        mModel.delFreeTerm(freeTermlistBean).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        adapter.remove(pos);
                    }
                });
    }

    @Override
    public void update(final int pos, FreeTermlistBean freeTermlistBean) {
        mRootView.showMessage("更新成功");
        mModel.update(freeTermlistBean).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FreeTermlistBean>() {
                    @Override
                    public void call(FreeTermlistBean freeTermlistBean) {
                        adapter.notifyItemChanged(pos);
                    }
                });


    }
}
