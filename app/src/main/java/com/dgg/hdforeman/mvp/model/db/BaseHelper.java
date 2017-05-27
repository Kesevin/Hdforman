package com.dgg.hdforeman.mvp.model.db;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by Rex on 2016/11/1.
 */

public interface BaseHelper<T> {


    public  void insert(T t);

    public  void delete(T t);

    public  void clear();

    public  void update(T t);

    public  T find(WhereCondition condition);

    public  List<T> findList(WhereCondition condition);

}
