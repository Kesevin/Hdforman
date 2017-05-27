package com.dgg.hdforeman.mvp.model.been;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author:zhangjing
 * 作用:
 * return:
 */
@Entity
public class FreeTermlistBean {


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * id : 7482442602642674000
     * puproid : 7481679699534680000
     * puugname : 水曲柳或胡桃木实木线条
     * puugprice : 100
     * puugunit : 平方米(/㎡)
     * punumber : 1
     * ugworktypename : 木工
     * pu_proid : 7481679699534680000
     */
    @Id(autoincrement = true)
    @SerializedName("pid")
    private Long id;
    @SerializedName("id")
    private String freeid;
    private String puugname;
    private String puugprice;
    private String puugunit;
    private String punumber;
    private String ugworktypename;
    private String pu_proid;

    public boolean issaved() {
        return issaved;
    }

    public void setIssaved(boolean issaved) {
        this.issaved = issaved;
    }

    private boolean issaved=false;
    public String getSpacename() {
        return spacename;
    }

    public void setSpacename(String spacename) {
        this.spacename = spacename;
    }

    private String spacename;

    public String getSpaceid() {
        return spaceid;
    }

    public void setSpaceid(String spaceid) {
        this.spaceid = spaceid;
    }

    private String spaceid;

    @Generated(hash = 485715635)
    public FreeTermlistBean(Long id, String freeid, String puugname,
            String puugprice, String puugunit, String punumber,
            String ugworktypename, String pu_proid, boolean issaved,
            String spacename, String spaceid) {
        this.id = id;
        this.freeid = freeid;
        this.puugname = puugname;
        this.puugprice = puugprice;
        this.puugunit = puugunit;
        this.punumber = punumber;
        this.ugworktypename = ugworktypename;
        this.pu_proid = pu_proid;
        this.issaved = issaved;
        this.spacename = spacename;
        this.spaceid = spaceid;
    }

    @Generated(hash = 122912576)
    public FreeTermlistBean() {
    }


    
    public String getPuugprice() {
        return puugprice;
    }

    public void setPuugprice(String puugprice) {
        this.puugprice = puugprice;
    }


    public String getPuugname() {
        return puugname;
    }

    public void setPuugname(String puugname) {
        this.puugname = puugname;
    }


    public String getPuugunit() {
        return puugunit;
    }

    public void setPuugunit(String puugunit) {
        this.puugunit = puugunit;
    }

    public String getPunumber() {
        return punumber;
    }

    public void setPunumber(String punumber) {
        this.punumber = punumber;
    }

    public String getUgworktypename() {
        return ugworktypename;
    }

    public void setUgworktypename(String ugworktypename) {
        this.ugworktypename = ugworktypename;
    }

    public String getPu_proid() {
        return pu_proid;
    }

    public void setPu_proid(String pu_proid) {
        this.pu_proid = pu_proid;
    }

    public String getFreeid() {
        return this.freeid;
    }

    public void setFreeid(String freeid) {
        this.freeid = freeid;
    }

    public boolean getIssaved() {
        return this.issaved;
    }


    
}
