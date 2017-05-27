package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by jess on 18/11/2016 11:15
 * Contact with jess.yan.effort@gmail.com
 */

public class PackageInfo implements Serializable {


    /**
     * id : 7481995582580723712
     * pk_no : 10812010004482
     * pk_name : 299辅材套餐包(一)
     * pk_price : 299
     * pk_type : 2
     */

    private long id;
    private String pk_no;
    private String pk_name;
    private int pk_price;
    private int pk_type;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPk_no() {
        return pk_no;
    }

    public void setPk_no(String pk_no) {
        this.pk_no = pk_no;
    }

    public String getPk_name() {
        return pk_name;
    }

    public void setPk_name(String pk_name) {
        this.pk_name = pk_name;
    }

    public int getPk_price() {
        return pk_price;
    }

    public void setPk_price(int pk_price) {
        this.pk_price = pk_price;
    }

    public int getPk_type() {
        return pk_type;
    }

    public void setPk_type(int pk_type) {
        this.pk_type = pk_type;
    }
}
