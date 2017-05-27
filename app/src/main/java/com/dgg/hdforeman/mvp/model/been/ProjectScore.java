package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/14.
 */

public class ProjectScore implements Serializable{
    private float pm_gqgk;
    private float pm_fwtd;
    private Long id;
    private float pm_xtnl;
    private String pm_housesname;
    private float pm_ztjn;
    private String pm_housesaddress;

    public float getPm_gqgk() {
        return pm_gqgk;
    }

    public void setPm_gqgk(float pm_gqgk) {
        this.pm_gqgk = pm_gqgk;
    }

    public float getPm_fwtd() {
        return pm_fwtd;
    }

    public void setPm_fwtd(float pm_fwtd) {
        this.pm_fwtd = pm_fwtd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPm_xtnl() {
        return pm_xtnl;
    }

    public void setPm_xtnl(float pm_xtnl) {
        this.pm_xtnl = pm_xtnl;
    }

    public String getPm_housesname() {
        return pm_housesname;
    }

    public void setPm_housesname(String pm_housesname) {
        this.pm_housesname = pm_housesname;
    }

    public float getPm_ztjn() {
        return pm_ztjn;
    }

    public void setPm_ztjn(float pm_ztjn) {
        this.pm_ztjn = pm_ztjn;
    }

    public String getPm_housesaddress() {
        return pm_housesaddress;
    }

    public void setPm_housesaddress(String pm_housesaddress) {
        this.pm_housesaddress = pm_housesaddress;
    }

    public String getAverage(){
        return String.format("%.1f",(pm_gqgk+pm_fwtd+pm_xtnl+pm_ztjn)/4);
    }
}
