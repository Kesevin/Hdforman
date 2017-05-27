package com.dgg.hdforeman.mvp.presenter.income;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.income.IncomeContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.Income;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/11.
 */
@ActivityScope
public class IncomePresenter extends BasePresenter<IncomeContract.Model,IncomeContract.View> {

    @Inject
    public IncomePresenter(IncomeContract.Model model, IncomeContract.View rootView) {
        super(model, rootView);
    }

    public void getIncomeData(){
        mModel.getIncomeData()
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.<BaseData<Income>>applySchedulers(mRootView))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseData<Income>>() {
                    @Override
                    public void call(BaseData<Income> incomeBaseData) {
                        if(incomeBaseData.isSuccess()){
                            mRootView.bindDataToView(incomeBaseData.getD());
                        }else{
                            mRootView.showMessage(incomeBaseData.getMsg());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        mRootView.showMessage("网络连接失败，请稍后重试");
                    }
                });
    }
}
