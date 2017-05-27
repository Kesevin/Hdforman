package com.dgg.hdforeman.mvp.model.been;

import java.util.List;

import static com.dgg.hdforeman.R.id.pm_xtnl;
import static com.dgg.hdforeman.R.id.pm_ztjn;

/**
 * Created by kelvin on 2016/11/15.
 */
public class IntermediateAcceptResponse {
    private String id;//阶段id
    private String ps_stno;//阶段编号
    private String ps_stname;//阶段名称
    private String ps_worknumber;//工程量
    private int ps_state;//阶段状态
    private String ps_enddate;//完工时间
    private String ps_passtime;//验收时间
    private float ps_wmxtnl;//协调能力得分
    private float ps_wmfwtd;//服务态度
    private float ps_wmgqgk;//工期管控
    private float ps_wmztjn; //个人技能
    private float ps_average;//平均分
    private String ps_yspicture;//施工图片集合
    private List<priceListBean> priceList;//工程量集合

    public float getAverage(){
        return (Math.round(ps_wmxtnl+ps_wmfwtd+ps_wmgqgk+ps_wmztjn)/4f*10f)/10f;
    }

    public List<priceListBean> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<priceListBean> priceList) {
        this.priceList = priceList;
    }

    public String getPs_yspicture() {
        return ps_yspicture;
    }

    public void setPs_yspicture(String ps_yspicture) {
        this.ps_yspicture = ps_yspicture;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPs_stno(String ps_stno) {
        this.ps_stno = ps_stno;
    }

    public void setPs_stname(String ps_stname) {
        this.ps_stname = ps_stname;
    }

    public void setPs_worknumber(String ps_worknumber) {
        this.ps_worknumber = ps_worknumber;
    }

    public void setPs_state(int ps_state) {
        this.ps_state = ps_state;
    }

    public void setPs_enddate(String ps_enddate) {
        this.ps_enddate = ps_enddate;
    }

    public void setPs_passtime(String ps_passtime) {
        this.ps_passtime = ps_passtime;
    }

    public float getPs_wmxtnl() {
        return ps_wmxtnl;
    }

    public void setPs_wmxtnl(float ps_wmxtnl) {
        this.ps_wmxtnl = ps_wmxtnl;
    }

    public float getPs_wmfwtd() {
        return ps_wmfwtd;
    }

    public void setPs_wmfwtd(float ps_wmfwtd) {
        this.ps_wmfwtd = ps_wmfwtd;
    }

    public float getPs_wmgqgk() {
        return ps_wmgqgk;
    }

    public void setPs_wmgqgk(float ps_wmgqgk) {
        this.ps_wmgqgk = ps_wmgqgk;
    }

    public float getPs_wmztjn() {
        return ps_wmztjn;
    }

    public void setPs_wmztjn(float ps_wmztjn) {
        this.ps_wmztjn = ps_wmztjn;
    }

    public String getId() {
        return id;
    }

    public String getPs_stno() {
        return ps_stno;
    }

    public String getPs_stname() {
        return ps_stname;
    }

    public String getPs_worknumber() {
        return ps_worknumber;
    }

    public int getPs_state() {
        return ps_state;
    }

    public String getPs_enddate() {
        return ps_enddate;
    }

    public String getPs_passtime() {
        return ps_passtime;
    }

    /**
     * "ps_spname": "强电改造点位",
     "priceid": 7492553697818578952,
     "ps_worknumber": 0
     */
    public class priceListBean{
        private String ps_spname;
        private String priceid;
        private String ps_worknumber;

        public String getPs_spname() {
            return ps_spname;
        }

        public void setPs_spname(String ps_spname) {
            this.ps_spname = ps_spname;
        }

        public String getPriceid() {
            return priceid;
        }

        public void setPriceid(String priceid) {
            this.priceid = priceid;
        }

        public String getPs_worknumber() {
            return ps_worknumber;
        }

        public void setPs_worknumber(String ps_worknumber) {
            this.ps_worknumber = ps_worknumber;
        }
    }
}
