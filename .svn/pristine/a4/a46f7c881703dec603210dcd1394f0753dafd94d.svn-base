package com.dgg.hdforeman.mvp.presenter.mine;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.ToastUtils;
import com.dgg.hdforeman.mvp.contract.mine.DataContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.MineData;
import com.dgg.hdforeman.mvp.model.mine.MineDataModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/28.
 */

public class MineDataPresenter {
    private DataContract.View mView;
    private MineDataModel mMineDataModel;
    private HDApplication app;
    public MineDataPresenter(DataContract.View view,HDApplication app){
        this.mView=view;
        mMineDataModel=new MineDataModel(app);
        this.app=app;
    }

    public void getMineData(){
        mMineDataModel.getMineData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseData<MineData>>() {
                    @Override
                    public void call(BaseData<MineData> mineDataBaseData) {
                        if (mineDataBaseData.isSuccess()) {
                            mView.bindDataToView(mineDataBaseData.getD());
                        } else {
                            ToastUtils.showToast(mineDataBaseData.getMsg());
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
