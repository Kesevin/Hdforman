package com.dgg.hdforeman.mvp.model.been;

import java.util.List;

/**
 * Created by tsang on 2016/10/27.
 */

public class ProjectDetail {

    /**
     * d : [{"pm_no":"1065453545600","pm_cusname":"陈东","pm_acreage":80,"pm_housesname":"中德英伦联邦","pm_housetype":"三室两厅","pm_housesaddress":"成都市青羊区草市街110号","pm_roomno":"12栋1单元1004号","pm_startdate":"2016-09-30 00:00:00","pm_stopdate":"2016-10-26 00:00:00","pm_finishdate":"2016-10-08 00:00:00","pm_stopdays":0,"pm_prices":24000,"stage":"水电及改造保护阶段","stagestate":0}]
     * code : 0
     * msg : 操作成功
     */

    private String code;
    private String msg;
    /**
     * pm_no : 1065453545600
     * pm_cusname : 陈东
     * pm_acreage : 80.0
     * pm_housesname : 中德英伦联邦
     * pm_housetype : 三室两厅
     * pm_housesaddress : 成都市青羊区草市街110号
     * pm_roomno : 12栋1单元1004号
     * pm_startdate : 2016-09-30 00:00:00
     * pm_stopdate : 2016-10-26 00:00:00
     * pm_finishdate : 2016-10-08 00:00:00
     * pm_stopdays : 0
     * pm_prices : 24000.0
     * stage : 水电及改造保护阶段
     * stagestate : 0
     */

    private List<DBean> d;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DBean> getD() {
        return d;
    }

    public void setD(List<DBean> d) {
        this.d = d;
    }

    public static class DBean {
        private String pm_no;
        private String pm_cusname;
        private double pm_acreage;
        private String pm_housesname;
        private String pm_housetype;
        private String pm_housesaddress;
        private String pm_roomno;
        private String pm_startdate;
        private String pm_stopdate;
        private String pm_finishdate;
        private int pm_stopdays;
        private double pm_prices;
        private String stage;
        private int stagestate;

        public String getPm_no() {
            return pm_no;
        }

        public void setPm_no(String pm_no) {
            this.pm_no = pm_no;
        }

        public String getPm_cusname() {
            return pm_cusname;
        }

        public void setPm_cusname(String pm_cusname) {
            this.pm_cusname = pm_cusname;
        }

        public double getPm_acreage() {
            return pm_acreage;
        }

        public void setPm_acreage(double pm_acreage) {
            this.pm_acreage = pm_acreage;
        }

        public String getPm_housesname() {
            return pm_housesname;
        }

        public void setPm_housesname(String pm_housesname) {
            this.pm_housesname = pm_housesname;
        }

        public String getPm_housetype() {
            return pm_housetype;
        }

        public void setPm_housetype(String pm_housetype) {
            this.pm_housetype = pm_housetype;
        }

        public String getPm_housesaddress() {
            return pm_housesaddress;
        }

        public void setPm_housesaddress(String pm_housesaddress) {
            this.pm_housesaddress = pm_housesaddress;
        }

        public String getPm_roomno() {
            return pm_roomno;
        }

        public void setPm_roomno(String pm_roomno) {
            this.pm_roomno = pm_roomno;
        }

        public String getPm_startdate() {
            return pm_startdate;
        }

        public void setPm_startdate(String pm_startdate) {
            this.pm_startdate = pm_startdate;
        }

        public String getPm_stopdate() {
            return pm_stopdate;
        }

        public void setPm_stopdate(String pm_stopdate) {
            this.pm_stopdate = pm_stopdate;
        }

        public String getPm_finishdate() {
            return pm_finishdate;
        }

        public void setPm_finishdate(String pm_finishdate) {
            this.pm_finishdate = pm_finishdate;
        }

        public int getPm_stopdays() {
            return pm_stopdays;
        }

        public void setPm_stopdays(int pm_stopdays) {
            this.pm_stopdays = pm_stopdays;
        }

        public double getPm_prices() {
            return pm_prices;
        }

        public void setPm_prices(double pm_prices) {
            this.pm_prices = pm_prices;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public int getStagestate() {
            return stagestate;
        }

        public void setStagestate(int stagestate) {
            this.stagestate = stagestate;
        }
    }

    @Override
    public String toString() {
        return "ProjectDetail{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", d=" + d +
                '}';
    }
}
