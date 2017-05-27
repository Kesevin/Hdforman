package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by Administrator on 2016/10/21.
 */

public class TeamUserBean {
    private int id;
    private String headUrl;//工人头像
    private String userName;//工人名
    private int phoneNumber;//电话号码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
