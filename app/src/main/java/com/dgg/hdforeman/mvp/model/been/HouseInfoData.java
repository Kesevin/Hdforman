package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by kelvin on 2016/11/9.
 * 房产信息
 */

public class HouseInfoData implements Serializable{
    private String id;//项目id
    private String pm_cusname;//业主
    private String measure;//工地测量（0，未测量，1，已测量）
    private String pm_cuscontactno;//业主电话
    private String pm_housesname;//楼盘
    private String pm_state;//项目状态(默认 0未签约 1待开工(已签约) 2施工中 3 停工(暂停) 4 完工 5未签约工长已接手6工长已报价待签约)
    private String pm_housetype; //户型
    private String pm_acreage; //面积
    private String pricestate;//业主报价（0未报价，1已报价 ）
    private String pm_roomno;//单元
    private String pm_housesaddress;//地址

    public String getId() {
        return id;
    }

    public String getPm_cusname() {
        return pm_cusname;
    }

    public String getMeasure() {
        return measure;
    }

    public String getPm_cuscontactno() {
        return pm_cuscontactno;
    }

    public String getPm_housesname() {
        return pm_housesname;
    }

    public String getPm_state() {
        return pm_state;
    }

    public String getPm_housetype() {
        return pm_housetype;
    }

    public String getPm_acreage() {
        return pm_acreage;
    }

    public String getPricestate() {
        return pricestate;
    }

    public String getPm_roomno() {
        return pm_roomno;
    }

    public String getPm_housesaddress() {
        return pm_housesaddress;
    }
}
