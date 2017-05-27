package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.AddFreeTermContract;
import com.dgg.hdforeman.mvp.model.been.AddFreeTermBean;
import com.dgg.hdforeman.mvp.model.been.BaseJson;
import com.dgg.hdforeman.mvp.model.been.SpaceBean;
import com.dgg.hdforeman.mvp.model.been.Worker;
import com.dgg.hdforeman.mvp.model.been.workertypeDb;
import com.dgg.hdforeman.mvp.ui.project.adapter.ProductAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.WokernameAdapter;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class AddFreeTermPresenter extends BasePresenter<AddFreeTermContract.Model,AddFreeTermContract.View> implements WokernameAdapter.OnitemClickListener,ProductAdapter.ProductClickListener{
    private Application mapplication;
    private RxErrorHandler mRxerrorHandler;
    private WokernameAdapter adapter;
    private ProductAdapter padapter;
    private List<AddFreeTermBean> workertypeDbs=new ArrayList<>();
    private List<AddFreeTermBean> addFreeTermBeanList=new ArrayList<>();

    public void setPid(String pid) {
        this.pid = pid;
    }

    private String pid;//项目id
    @Inject
    public AddFreeTermPresenter(AddFreeTermContract.Model model, AddFreeTermContract.View rootView, RxErrorHandler mRxerrorHandler, Application mapplication) {
        super(model, rootView);
        this.mRxerrorHandler = mRxerrorHandler;
        this.mapplication = mapplication;
    }
    public void initdata(){
        adapter=new WokernameAdapter(workertypeDbs);
        padapter=new ProductAdapter(addFreeTermBeanList);
        adapter.setOnitemClickListener(this);
        padapter.setProductClickListener(this);
        mRootView.initrecylerview(adapter,padapter);
        mModel.loadDeployFreeTerm().compose(RxUtils.<BaseJson<List<AddFreeTermBean>>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseJson<List<AddFreeTermBean>>>(mRxerrorHandler){

                    @Override
                    public void onNext(BaseJson<List<AddFreeTermBean>> listBaseJson) {
                        if (listBaseJson.isSuccess()){
                            mModel.savaDataInDb(listBaseJson.getD());
                            mModel.getworkerType().observeOn(AndroidSchedulers.mainThread()).
                                    subscribe(new Action1<List<AddFreeTermBean>>() {
                                @Override
                                public void call(List<AddFreeTermBean> workertypeDbs) {
                                    adapter.setNewData(workertypeDbs);
                                }
                            });
                        }else {
                            mRootView.showMessage(listBaseJson.getMsg());
                        }
                    }
                });


    }
    /*点击工种*/
    @Override
    public void typeclick(AddFreeTermBean workertypeDb) {
        padapter.setNewData(mModel.getlistbyType(workertypeDb.getId()));
    }
    /*
    * 点击产品显示弹框
    * */
    @Override
    public void pclick(final AddFreeTermBean addFreeTermBean) {
        mModel.getMessurelist(pid).compose(RxUtils.<BaseJson<SpaceBean>>applySchedulers(mRootView)).subscribe(new Action1<BaseJson<SpaceBean>>() {
            @Override
            public void call(BaseJson<SpaceBean> spaceBeanBaseJson) {
                mRootView.showpopwindow(spaceBeanBaseJson.getD().getSpacename(),addFreeTermBean);
            }
        });

    }
    public void addFreeTerm2server(AddFreeTermBean addFreeTermBean, String pid, SpaceBean.SpacenameEntity spacenameEntity,String num){
        if (num==null||num.length()==0){
            mRootView.showMessage("请先输入数量");
            return;
        }
        mModel.addFree2server(addFreeTermBean,pid,spacenameEntity,num).compose(RxUtils.<Object[]>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<Object[]>(mRxerrorHandler){

                    @Override
                    public void onNext(Object[] objects) {
                        mRootView.hidepopwindow();
                        mRootView.showMessage("数据保存成功");
                    }
                });
    }
}
