package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by kelvin on 2016/11/11.
 */

public class ProjectAcceptResponse implements Serializable{
    private String id;//阶段id
    private String ps_stname; //阶段名称
    private int ps_state; //阶段状态(默认0 未完工  1施工中 2停工中 3待验收 4验收中 5待付款 6已付款(完工))//前台展示判断<=3为待验收4为验收中>=5为已验收
    private int isAccept;

    public int getIsAccept() {
        return isAccept;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPs_stname(String ps_stname) {
        this.ps_stname = ps_stname;
    }

    public void setPs_state(int ps_state) {
        this.ps_state = ps_state;
    }

    public String getId() {
        return id;
    }

    public String getPs_stname() {
        return ps_stname;
    }

    public int getPs_state() {
        return ps_state;
    }
}
