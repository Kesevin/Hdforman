package com.dgg.hdforeman.mvp.presenter.project;

import android.app.Application;

import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.project.AddMembersContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.ConstructTeamBean;
import com.dgg.hdforeman.mvp.model.been.Team;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
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
 * Created by Administrator on 2016/11/15.
 */

@ActivityScope
public class AddMembersPresenter extends BasePresenter<AddMembersContract.Model, AddMembersContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private RxPermissions mRxPermissions;
    private ImageLoader mImageLoader;

    @Inject
    public AddMembersPresenter(AddMembersContract.Model model, AddMembersContract.View rootView
            , RxErrorHandler handler, Application application, RxPermissions rxPermissions
            , ImageLoader imageLoader) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mRxPermissions = rxPermissions;
        this.mImageLoader = imageLoader;
    }

    public void getMembersList(final ArrayList<ConstructTeamBean> mPreList){
        mModel.getAddMembersListDatas()
                .subscribeOn(Schedulers.io())
                .compose(RxUtils.<BaseData<List<Team>>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseData<List<Team>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData<List<Team>> listBaseData) {
                        if(listBaseData.isSuccess()){
                            if(listBaseData.getD()==null || (listBaseData.getD()!=null && listBaseData.getD().size()==0)){
                                mRootView.showNoData();
                            }else{
                                mRootView.hintNodata();
                            }
                            mRootView.bindDataToView(mModel.getAddMembersListData(listBaseData.getD(),mPreList));
                        }else{
                            mRootView.showMessage(listBaseData.getMsg());
                        }
                    }
                });
    }

    public void addTeamMember(String proId,String workId){
        mModel.addTeamMember(proId,workId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ErrorHandleSubscriber<BaseData>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData baseData) {
                        if(baseData.isSuccess()){
                            mRootView.showMessage("添加成功");
                            mRootView.removeTeamList();
                            EventBus.getDefault().post(true,"refreshMember");
                        }else{
                            mRootView.showMessage(baseData.getMsg());
                        }
                    }
                });
    }

//    public void filterMemberList(List<ConstructTeamBean> preList){
//
//    }


}