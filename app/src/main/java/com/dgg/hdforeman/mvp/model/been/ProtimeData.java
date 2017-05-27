package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by kelvin on 2016/11/9.
 */

public class ProtimeData implements Serializable{
    private String pm_startdate;//开始时间
    private String pm_planfinishdate;//预计竣工时间

    public String getPm_startdate() {
        return pm_startdate;
    }

    public String getPm_planfinishdate() {
        return pm_planfinishdate;
    }
}
