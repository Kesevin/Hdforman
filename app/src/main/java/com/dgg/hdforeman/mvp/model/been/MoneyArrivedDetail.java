package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by Administrator on 2016/11/17.
 * housesname        楼盘
 housesaddress  楼盘地址
 create_time      结算时间
 money                  工程款
 stname                阶段名称
 cusname              业主姓名
 prosum                项目款
 */

public class MoneyArrivedDetail {
    private String housesname;
    private String housesaddress;
    private String create_time;
    private String money;
    private String stname;
    private String cusname;
    private String prosum;

    public String getHousesname() {
        return housesname;
    }

    public void setHousesname(String housesname) {
        this.housesname = housesname;
    }

    public String getHousesaddress() {
        return housesaddress;
    }

    public void setHousesaddress(String housesaddress) {
        this.housesaddress = housesaddress;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStname() {
        return stname;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getProsum() {
        return prosum;
    }

    public void setProsum(String prosum) {
        this.prosum = prosum;
    }
}
