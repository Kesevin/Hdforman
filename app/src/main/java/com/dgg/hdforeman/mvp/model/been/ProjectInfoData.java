package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kelvin on 2016/11/9.
 */

public class ProjectInfoData implements Serializable{
    private List<InstageData> instage;//正在施工中的阶段
    private List<ProtimeData> protime;//时间
    private List<TeamData> team; //团队数组

    public List<InstageData> getInstage() {
        return instage;
    }

    public List<ProtimeData> getProtime() {
        return protime;
    }

    public List<TeamData> getTeam() {
        return team;
    }
}
