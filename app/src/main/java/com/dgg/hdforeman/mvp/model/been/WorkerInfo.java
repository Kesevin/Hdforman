package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by kelvin on 2016/11/3.
 * 工人信息
 */

public class WorkerInfo implements Serializable{
    private String ps_wkid;//工人id
    private String ps_wkname;//工人名称
    private String wk_headpic;//工人头像

    public String getPs_wkid() {
        return ps_wkid;
    }

    public void setPs_wkid(String ps_wkid) {
        this.ps_wkid = ps_wkid;
    }

    public String getPs_wkname() {
        return ps_wkname;
    }

    public void setPs_wkname(String ps_wkname) {
        this.ps_wkname = ps_wkname;
    }

    public String getWk_headpic() {
        return wk_headpic;
    }

    public void setWk_headpic(String wk_headpic) {
        this.wk_headpic = wk_headpic;
    }
}
