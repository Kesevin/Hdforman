package com.dgg.hdforeman.mvp.model.been;

/**
 * author:zhangjing
 * 作用:添加自由项需要的请求类
 * return:
 */

public class ReqAddFreeTermBean {
    /*proid 项目id,
    spaceid 空间id,
    spacename 空间名称,
    termid 自由项id,
    number 数量*/
    private String proid;
    private String spaceid;
    private String spacename;
    private String termid;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private int number;
}
