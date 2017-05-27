package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by kelvin on 2016/11/9.
 */

public class InstageData implements Serializable{
    private String ps_state;//施工中
    private String ps_stname;//阶段

    public String getPs_state() {
        return ps_state;
    }

    public String getPs_stname() {
        return ps_stname;
    }
}
