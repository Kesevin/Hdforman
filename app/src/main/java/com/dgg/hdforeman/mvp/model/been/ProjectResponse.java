package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kelvin on 2016/11/9.
 */

public class ProjectResponse implements Serializable{
    private String id;//项目ID
    private String pm_state; //项目状态0,1,2,3,4 //项目状态(默认 0未签约 1待开工(已签约) 2施工中 3 停工(暂停) 4 完工
    private String pm_takeover;//0未接手1已接手
    private String pm_acreage;//套内面积、
    private String pm_no;//项目编号
    private String pm_housesname;//房屋楼盘
    private String pm_roomno;//工程房号
    private String pm_housesaddress;//楼盘地址
    private String pm_cusname;//业主姓名
    private String pm_cuscontactno;//联系方式
    private String pm_startdate;//项目开工日期
    private String pm_stopdate;//项目停工时间
    private String pm_planfinishdate;//项目预计完成日期
    private String pm_finishdate;//项目实际完工日期
    private String pm_stopdays;//项目停工天数
    private String stage;//正在进行中的阶段
    private String stageState;//正在进行中的阶段的状态0未完成（正在进行中） 1 完成
    private String pm_quote;//报价金额
    private String pm_quotetime;

    public String getPm_quotetime() {
        return pm_quotetime;
    }

    public void setPm_quotetime(String pm_quotetime) {
        this.pm_quotetime = pm_quotetime;
    }

    public String getPm_quote() {
        return pm_quote;
    }

    public void setPm_quote(String pm_quote) {
        this.pm_quote = pm_quote;
    }

    private List<ProjectUpList> upList=new ArrayList<>();

    public List<ProjectUpList> getUpList() {
        return upList;
    }

    public void setUpList(List<ProjectUpList> upList) {
        this.upList = upList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPm_state() {
        return pm_state;
    }

    public String getPm_takeover() {
        return pm_takeover;
    }

    public String getPm_acreage() {
        return pm_acreage;
    }

    public String getPm_no() {
        return pm_no;
    }

    public String getPm_housesname() {
        return pm_housesname;
    }

    public String getPm_roomno() {
        return pm_roomno;
    }

    public String getPm_housesaddress() {
        return pm_housesaddress;
    }

    public String getPm_cusname() {
        return pm_cusname;
    }

    public String getPm_cuscontactno() {
        return pm_cuscontactno;
    }

    public String getPm_startdate() {
        return pm_startdate;
    }

    public String getPm_stopdate() {
        return pm_stopdate;
    }

    public String getPm_planfinishdate() {
        return pm_planfinishdate;
    }

    public String getPm_finishdate() {
        return pm_finishdate;
    }

    public String getPm_stopdays() {
        return pm_stopdays;
    }

    public String getStage() {
        return stage;
    }

    public String getStageState() {
        return stageState;
    }

    @Override
    public String toString() {
        return "ProjectResponse{" +
                "id='" + id + '\'' +
                ", pm_state='" + pm_state + '\'' +
                ", pm_takeover='" + pm_takeover + '\'' +
                ", pm_acreage='" + pm_acreage + '\'' +
                ", pm_no='" + pm_no + '\'' +
                ", pm_housesname='" + pm_housesname + '\'' +
                ", pm_roomno='" + pm_roomno + '\'' +
                ", pm_housesaddress='" + pm_housesaddress + '\'' +
                ", pm_cusname='" + pm_cusname + '\'' +
                ", pm_cuscontactno='" + pm_cuscontactno + '\'' +
                ", pm_startdate='" + pm_startdate + '\'' +
                ", pm_stopdate='" + pm_stopdate + '\'' +
                ", pm_planfinishdate='" + pm_planfinishdate + '\'' +
                ", pm_finishdate='" + pm_finishdate + '\'' +
                ", pm_stopdays='" + pm_stopdays + '\'' +
                ", stage='" + stage + '\'' +
                ", stageState='" + stageState + '\'' +
                '}';
    }
}
