package com.dgg.hdforeman.mvp.presenter.mine;

import android.util.Log;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.mvp.contract.mine.ModifyPassWordContract;
import com.dgg.hdforeman.mvp.model.been.ModifyPassWord;
import com.dgg.hdforeman.mvp.model.mine.ModifyPassWordModel;
import com.dgg.hdforeman.app.utils.ToastUtils;
import com.jess.arms.utils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/28.
 */

public class ModifyPassWordPresenter{
    private ModifyPassWordContract.View view;
    private HDApplication app;
    private ModifyPassWordModel model;
    public ModifyPassWordPresenter(ModifyPassWordContract.View view, HDApplication app){
        this.view=view;
        this.app=app;
        model=new ModifyPassWordModel(app);
    }

    public void modifyPassWord(){
        String oldPwd=view.getOldPassWord();
        String newPwd=view.getNewPassWord();
        String reNewPwd=view.getReNewPassWord();
        if(oldPwd.isEmpty()){
            ToastUtils.showToast("请输入原密码");
            return;
        }
        if(newPwd.isEmpty()){
            ToastUtils.showToast("请输入新密码");
            return;
        }
        if(reNewPwd.isEmpty()){
            ToastUtils.showToast("请再次输入新密码");
            return;
        }
        if (!newPwd.equals(reNewPwd)) {
            ToastUtils.showToast("两次新密码不一致");
            return;
        }
        String PASSKEY="cd962540";//加密密钥
        String oldPwdEncryptString=(int)(Math.random()*900)+100+oldPwd+((int)(Math.random()*900000)+100000);
        CommonUtil.logDebug("旧密码明文:"+oldPwdEncryptString);
        CommonUtil.logDebug("旧密码密文:"+CommonUtil.encryptDES(oldPwdEncryptString,PASSKEY));

        String newPwdEncryptString=(int)(Math.random()*900)+100+newPwd+((int)(Math.random()*900000)+100000);
        CommonUtil.logDebug("新密码明文:"+newPwdEncryptString);
        CommonUtil.logDebug("新密码密文:"+CommonUtil.encryptDES(newPwdEncryptString,PASSKEY));
        model.modifyPassWord(CommonUtil.encryptDES(oldPwdEncryptString,PASSKEY),CommonUtil.encryptDES(newPwdEncryptString,PASSKEY))
                .subscribeOn(Schedulers.io())
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                            @Override
                            public Observable<?> call(Throwable throwable) {
                                // For IOExceptions, we  retry
                                if (throwable instanceof IOException) {
//                                    return Observable.just(null);
                                    return Observable.timer(5, TimeUnit.SECONDS);
                                }
                                // For anything else, don't retry
                                return Observable.error(throwable);
                            }
                        });
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.showLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        view.hideLoading();
                    }
                })
                .subscribe(new Action1<ModifyPassWord>() {
                    @Override
                    public void call(ModifyPassWord modifyPassWord) {
                        if (modifyPassWord.getCode().equals("0")) {
                            ToastUtils.showToast("密码修改成功");
                            view.killMyself();
                        } else {
                            ToastUtils.showToast(modifyPassWord.getMsg());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.w("error",throwable.getMessage());
                        ToastUtils.showToast("网络连接失败，请检查网络");
                    }
                });
    }
}
