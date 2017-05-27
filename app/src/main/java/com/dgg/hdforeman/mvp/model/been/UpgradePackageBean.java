package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by kelvin on 2016/11/15.
 */

public class UpgradePackageBean {
    private String pu_peid;//所属项目空间ID
    private String id;//升级包id
    private String pu_ugid;//升级包id
    private String pu_ugno; //升级包编号
    private String pu_ugname; //升级包名称
    private String pu_ugunit;//单位
    private String pu_pename;//空间
    private String pu_ugprice;//单价
    private String  pu_number;

    public String getPu_peid() {
        return pu_peid;
    }

    public String getId() {
        return id;
    }

    public String getPu_ugid() {
        return pu_ugid;
    }

    public String getPu_ugno() {
        return pu_ugno;
    }

    public String getPu_ugname() {
        return pu_ugname;
    }

    public String getPu_ugunit() {
        return pu_ugunit;
    }

    public String getPu_pename() {
        return pu_pename;
    }

    public String getPu_ugprice() {
        return pu_ugprice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPu_number() {
        return pu_number;
    }

    public void setPu_number(String pu_number) {
        this.pu_number = pu_number;
    }

    public void setPu_peid(String pu_peid) {
        this.pu_peid = pu_peid;
    }

    public void setPu_pename(String pu_pename) {
        this.pu_pename = pu_pename;
    }

    public void setPu_ugid(String pu_ugid) {
        this.pu_ugid = pu_ugid;
    }

    public void setPu_ugname(String pu_ugname) {
        this.pu_ugname = pu_ugname;
    }

    public void setPu_ugno(String pu_ugno) {
        this.pu_ugno = pu_ugno;
    }

    public void setPu_ugprice(String pu_ugprice) {
        this.pu_ugprice = pu_ugprice;
    }

    public void setPu_ugunit(String pu_ugunit) {
        this.pu_ugunit = pu_ugunit;
    }
}
