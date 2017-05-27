package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/21.
 * "id": 7486427254940242000,                 //升级包ID
 "pu_ugname": "卧室数量",                   //升级包名称
 "pu_ugunit": "个",                        //升级包单位
 "pu_number": 2,                             //升级包数量
 "pu_confirm": 0                            //是否确认：0代表未确认1代表已确认
 */

public class ProjectUpList implements Serializable{
    private String id;
    private String pu_ugname;
    private String pu_ugunit;
    private String pu_number;
    private int pu_confirm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPu_number() {
        return pu_number;
    }

    public void setPu_number(String pu_number) {
        this.pu_number = pu_number;
    }

    public int getPu_confirm() {
        return pu_confirm;
    }

    public void setPu_confirm(int pu_confirm) {
        this.pu_confirm = pu_confirm;
    }
}
