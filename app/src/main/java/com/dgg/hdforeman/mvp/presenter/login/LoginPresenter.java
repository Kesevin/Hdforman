package com.dgg.hdforeman.mvp.presenter.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.RxUtils;
import com.dgg.hdforeman.mvp.contract.login.LoginContract;
import com.dgg.hdforeman.mvp.model.been.BaseData;
import com.dgg.hdforeman.mvp.model.been.LoginData;
import com.dgg.hdforeman.mvp.model.been.User;
import com.dgg.hdforeman.mvp.ui.MainActivity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;

import org.greenrobot.greendao.rx.RxDao;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;

/**
 * Created by Rex on 2016/10/27.
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {

    private LoginContract.View loginView;
    private LoginContract.Model loginModel;
    private Context mContext;
    private RxDao<User, Long> useDao;
    private RxErrorHandler mErrorHandler;
    private Application mApplication;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , RxErrorHandler handler, Application application) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
    }


    public void login() {
        if (TextUtils.isEmpty(mRootView.getPhone())) {
            mRootView.showMessage("请输入用户名！");
            return;
        }
//        if (!mRootView.getPhone().startsWith("1")) {
//            mRootView.showMessage("请输入正确的手机号！");
//            return;
//        }
        if (TextUtils.isEmpty(mRootView.getPassWord())) {
            mRootView.showMessage("请输入密码！");
            return;
        }
        Timber.tag(TAG).d(mRootView.getPhone() + " " + mRootView.getPassWord());
        String encryptString = CommonUtil.getPass(mRootView.getPassWord());
        Timber.tag(TAG).d("明文: " + encryptString);
        Timber.tag(TAG).d("密文: " + CommonUtil.encryptDES(encryptString, CommonUtil.KEY));

        mModel.login(mRootView.getPhone(), CommonUtil.encryptDES(encryptString, CommonUtil.KEY))
                .compose(RxUtils.<BaseData<LoginData>>applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseData<LoginData>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseData<LoginData> logindata) {
                        mRootView.hideLoading();
                        if (logindata.isSuccess()) {
                            User mUser = logindata.getD().getUser();
                            mUser.setBank(logindata.getD().getBank());
                            mUser.setBankno(logindata.getD().getBankno());
                            mUser.setGrade(logindata.getD().getGrade());
                            mUser.setServicetel(logindata.getD().getServicetel());
                            HDApplication app = (HDApplication) mApplication;
                            app.userId = mUser.getUsid();
//                            mUser.setOnline(true);
                            mModel.SaveUserInfo(app, mUser);
                            mModel.savePhone(mApplication, mRootView.getPhone());
                            mRootView.showMessage("登录成功!");
                            mRootView.launchActivity(new Intent(mApplication, MainActivity.class));
                            mRootView.killMyself();
                        } else {
                            mRootView.showMessage(logindata.getMsg());
                        }

                    }
                });
//
    }

}
