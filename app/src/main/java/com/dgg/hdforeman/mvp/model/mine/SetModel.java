package com.dgg.hdforeman.mvp.model.mine;

import android.app.Application;
import android.content.Intent;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.app.utils.SPUtil;
import com.dgg.hdforeman.mvp.contract.mine.SetContract;
import com.dgg.hdforeman.mvp.model.api.cache.CacheManager;
import com.dgg.hdforeman.mvp.model.api.service.ServiceManager;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.been.User;
import com.dgg.hdforeman.mvp.model.been.UserDao;
import com.dgg.hdforeman.mvp.ui.login.LoginActivity;
import com.google.gson.Gson;
import com.jess.arms.mvp.BaseModel;
import com.jess.arms.utils.DataHelper;

import static com.dgg.hdforeman.app.HDApplication.COOKIES;
import static com.jess.arms.base.BaseActivity.IS_NOT_ADD_ACTIVITY_LIST;
import static com.jess.arms.base.BaseApplication.killAll;


/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Administrator on 2016/11/22.
 */

public class SetModel extends BaseModel<ServiceManager, CacheManager> implements SetContract.Model {
    private Gson mGson;
    private HDApplication mApplication;

    public SetModel(ServiceManager serviceManager, CacheManager cacheManager, Gson gson, Application application) {
        super(serviceManager, cacheManager);
        this.mGson = gson;
        this.mApplication = (HDApplication) application;
    }


    @Override
    public void outLogin() {
        mApplication.setCookie(null);
        DataHelper.removeSF(mApplication, DataHelper.CURRENT_USER_ID);
        DataHelper.removeSF(mApplication, COOKIES);
        killAll();
        Intent intent = new Intent(mApplication, LoginActivity.class);
        intent.putExtra(IS_NOT_ADD_ACTIVITY_LIST, true);//加此字段,killAll时也不回关闭此activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mApplication.startActivity(intent);
    }

    @Override
    public User getUser() {
        DaoSession daoSession = mApplication.getDaoSession();
        UserDao noteDao = daoSession.getUserDao();

        User user = noteDao.queryBuilder().where(UserDao.Properties.Usid.eq(
                Long.parseLong(DataHelper.getStringSF(mApplication
                        , SPUtil.CURRENT_USER_ID)))).build().unique();
        return user;
    }
}