package com.dgg.hdforeman.mvp.model.been;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kelvin on 2016/11/9.
 * 团队信息
 */

public class TeamData implements Serializable {
    private String ps_wtname;//工种名称
    private String ps_wtid;//工种编号
    private List<WorkerInfo> te;//工种数组

    public String getPs_wtname() {
        return ps_wtname;
    }

    public String getPs_wtid() {
        return ps_wtid;
    }

    public List<WorkerInfo> getTe() {
        return te;
    }
}
