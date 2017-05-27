package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by Administrator on 2016/10/24.
 */

public class ScoreBean {
    private Long id;
    private String pm_housesname;//项目名称
    private String pm_housesaddress;//地址
    private String pm_realfinishdate;//完工时间
    private float pm_xtnl;//协调能力评分
    private float pm_fwtd;//服务态度评分
    private float pm_gqgk;//工期管控评分
    private float pm_ztjn;//专业技能评分

    public String getPm_realfinishdate() {
        return pm_realfinishdate;
    }

    public void setPm_realfinishdate(String pm_realfinishdate) {
        this.pm_realfinishdate = pm_realfinishdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPm_housesname() {
        return pm_housesname;
    }

    public void setPm_housesname(String pm_housesname) {
        this.pm_housesname = pm_housesname;
    }

    public String getPm_housesaddress() {
        return pm_housesaddress;
    }

    public void setPm_housesaddress(String pm_housesaddress) {
        this.pm_housesaddress = pm_housesaddress;
    }

    public float getPm_xtnl() {
        return pm_xtnl;
    }

    public void setPm_xtnl(float pm_xtnl) {
        this.pm_xtnl = pm_xtnl;
    }

    public float getPm_fwtd() {
        return pm_fwtd;
    }

    public void setPm_fwtd(float pm_fwtd) {
        this.pm_fwtd = pm_fwtd;
    }

    public float getPm_gqgk() {
        return pm_gqgk;
    }

    public void setPm_gqgk(float pm_gqgk) {
        this.pm_gqgk = pm_gqgk;
    }

    public float getPm_ztjn() {
        return pm_ztjn;
    }

    public void setPm_ztjn(float pm_ztjn) {
        this.pm_ztjn = pm_ztjn;
    }

    public float getAllScore() {
        return (float)(Math.round((pm_xtnl+pm_fwtd+pm_gqgk+pm_ztjn)/4*10))/10;
    }
}
