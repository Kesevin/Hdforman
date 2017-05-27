package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;
import android.view.View;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.LimitTimeContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.BudgetSheetBean;
import com.dgg.hdforeman.mvp.model.been.ProStage;
import com.dgg.hdforeman.mvp.ui.project.adapter.ConstructionPeriodAdapter;
import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Rex on 2016/11/28.
 */

@ActivityScope
public class LimitTimePresenter extends BasePresenter<LimitTimeContract.Model, LimitTimeContract.View> implements ConstructionPeriodAdapter.OnItemClickListener {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private RxPermissions mRxPermissions;
    private ImageLoader mImageLoader;
    private List<Object> mData = new ArrayList<Object>();
    private ConstructionPeriodAdapter mAdapter;

    @Inject
    public LimitTimePresenter(LimitTimeContract.Model model, LimitTimeContract.View rootView
            , RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mRxPermissions = rxPermissions;
        this.mImageLoader = imageLoader;
    }

    public void getBudgetLimitData(String proid, String date) {
        mModel.getBudgetSheetData(proid, date)
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.<BaseData<BudgetSheetBean>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseData<BudgetSheetBean>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData<BudgetSheetBean> budgetSheetBeanBaseData) {
                        if (budgetSheetBeanBaseData.isSuccess()) {
                            mRootView.bindDataToView(budgetSheetBeanBaseData.getD());
                        } else {
                            mRootView.showMessage(budgetSheetBeanBaseData.getMsg());
                        }
                    }
                });
    }

    public void sendCustomer(String pcs_proid) {
        mRootView.showWaiting();
        mModel.sendToCustomer(pcs_proid) .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<BaseData<String>>() {

                    @Override
                    public void call(BaseData<String> mData) {
                        mRootView.hideWaiting();
                        if (mData.isSuccess()) {
                            mRootView.showMessage("发送成功");
//                            mRootView.killMyself();
                        } else {
                            mRootView.showMessage(mData.getMsg());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mRootView.hideWaiting();
                        mRootView.showMessage(throwable.getMessage());
                    }
                });
    }

    public void initAdapter() {
        mAdapter = new ConstructionPeriodAdapter(mData);
        mRootView.setAdapter(mAdapter);
        mAdapter.setItemClickListener(this);
    }

    public void transformData(List<ProStage> proStage) {
        mData.clear();
        for (int i = 0; i < proStage.size(); i++) {
            mData.add(proStage.get(i));
        }
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItem(View view, int position, Object item) {
        if(mData.get(position) instanceof  ProStage){
            ProStage   tmp =(ProStage) mData.get(position);
//            ProStage    tmp =(ProStage) item;
            if (!tmp.isExpand()) {
                List<ProStage.StageItem > itemData =  tmp.getList();
                itemData.get(0).setFirst(true);
                itemData.get(itemData.size()-1).setLast(true);
                mData.addAll(position + 1, itemData);
                tmp.setExpand(true);
            } else {
                for (int i = 0; i < tmp.getList().size(); i++) {
                    mData.remove(position + 1);
                }
                tmp.setExpand(false);
            }
            mAdapter.notifyDataSetChanged();

        }

    }
}