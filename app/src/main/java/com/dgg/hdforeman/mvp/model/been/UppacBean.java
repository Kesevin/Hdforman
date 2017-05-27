package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by Administrator on 2016/11/29.
 * {
 "id":                                        //升级包id
 "pu_peid"                                //空间id
 "pu_ugname": "卧室数量",                    //升级包名称
 "pu_ugunit": "个",                         //升级包单位
 "pu_ugprice": 0,                          //升级包单价
 "pu_number": 1                            //升级包数量（面积）
 }
 */

public class UppacBean {
    private String id;//升级包id
    private String pu_peid;//空间id
    private String pu_ugname;//升级包名称
    private String pu_ugunit;//升级包单位
    private int ug_ifthird;//是否显示
    private double pu_ugprice;//升级包单价
    private double pu_number;//升级包数量（面积）

    public int getUg_ifthird() {
        return ug_ifthird;
    }

    public void setUg_ifthird(int ug_ifthird) {
        this.ug_ifthird = ug_ifthird;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPu_peid() {
        return pu_peid;
    }

    public void setPu_peid(String pu_peid) {
        this.pu_peid = pu_peid;
    }

    public String getPu_ugname() {
        return pu_ugname;
    }

    public void setPu_ugname(String pu_ugname) {
        this.pu_ugname = pu_ugname;
    }

    public String getPu_ugunit() {
        return pu_ugunit;
    }

    public void setPu_ugunit(String pu_ugunit) {
        this.pu_ugunit = pu_ugunit;
    }

    public Double getPu_ugprice() {
        return pu_ugprice;
    }

    public void setPu_ugprice(Double pu_ugprice) {
        this.pu_ugprice = pu_ugprice;
    }

    public double getPu_number() {
        return pu_number;
    }

    public void setPu_number(double pu_number) {
        this.pu_number = pu_number;
    }

    public Double getSumMoney(){
        Double money=pu_ugprice*pu_number;
        return  money;
    }
}
