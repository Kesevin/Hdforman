package com.dgg.hdforeman.mvp.model.db;

import com.dgg.hdforeman.app.HDApplication;
import com.dgg.hdforeman.mvp.model.been.DaoSession;
import com.dgg.hdforeman.mvp.model.been.User;
import com.dgg.hdforeman.mvp.model.been.UserDao;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;


public class UserHelper implements BaseHelper<User>
{

    public static final long NO_ID = -1l;
    private static UserHelper instance;
    private UserDao mUserDao;

    private UserHelper()
    {
        DaoSession daoSession = HDApplication.getInstance().getDaoSession();
        mUserDao = daoSession.getUserDao();
    }


    public static UserHelper getInstance()
    {
        if (instance == null) {
                        synchronized (UserHelper.class) {
                                if (instance == null) {
                                       instance = new UserHelper();
                                    }
                           }
                  }
               return instance;
    }

    @Override
    public void insert(User user) {
        try {
            mUserDao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(User user)
    {
        try {
            mUserDao.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void clear() {
        try {
            mUserDao.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(User user) {
        try {
            mUserDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public User find(WhereCondition condition) {
        User user = mUserDao.queryBuilder().where(condition).unique();
        return user;
    }

    @Override
    public List<User> findList(WhereCondition condition) {
        List<User> list = mUserDao.queryBuilder().where(condition).list();
        return list;
    }
    public List<User> all() {
            return mUserDao.queryBuilder().list();
    }

    public void Logout()
    {
        User user = mUserDao.queryBuilder().where(UserDao.Properties.Online.eq(true)).unique();
        user.setOnline(false);
        mUserDao.update(user);
    }
}
