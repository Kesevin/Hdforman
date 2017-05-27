package com.dgg.hdforeman.mvp.contract.mine;

import com.dgg.hdforeman.mvp.model.been.ModifyPassWord;
import com.jess.arms.mvp.BaseView;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/28.
 */

public interface ModifyPassWordContract {
    interface View extends BaseView {
        String getOldPassWord();
        String getNewPassWord();
        String getReNewPassWord();
    }
    interface Model {
        Observable<ModifyPassWord> modifyPassWord(String oldPwd,String newPwd);
    }
}
