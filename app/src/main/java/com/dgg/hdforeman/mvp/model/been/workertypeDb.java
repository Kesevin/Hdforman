package com.dgg.hdforeman.mvp.model.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author:zhangjing
 * 作用:
 * return:
 */
@Entity
public class workertypeDb {
    private int parentid;
    private String wt_name;
@Generated(hash = 515180919)
public workertypeDb(int parentid, String wt_name) {
    this.parentid = parentid;
    this.wt_name = wt_name;
}
@Generated(hash = 2099588063)
public workertypeDb() {
}
public int getParentid() {
    return this.parentid;
}
public void setParentid(int parentid) {
    this.parentid = parentid;
}
public String getWt_name() {
    return this.wt_name;
}
public void setWt_name(String wt_name) {
    this.wt_name = wt_name;
}
}
