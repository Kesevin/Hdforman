package com.dgg.hdforeman.mvp.model.been;


import java.util.List;


public class ProStage {

    private String ps_id;
    private String ps_proid;
    private String PS_STNO;
    private String ps_stname;
    private String ps_period;
    private String ps_state;
    private List<StageItem> list;
    private boolean expand=false;
    public List<StageItem> getList() {
        return list;
    }

    public void setList(List<StageItem> list) {
        this.list = list;
    }

    public String getPs_id() {
        return ps_id;
    }

    public void setPs_id(String ps_id) {
        this.ps_id = ps_id;
    }

    public String getPs_period() {
        return ps_period;
    }

    public void setPs_period(String ps_period) {
        this.ps_period = ps_period;
    }

    public String getPs_proid() {
        return ps_proid;
    }

    public void setPs_proid(String ps_proid) {
        this.ps_proid = ps_proid;
    }

    public String getPs_state() {
        return ps_state;
    }

    public void setPs_state(String ps_state) {
        this.ps_state = ps_state;
    }

    public String getPs_stname() {
        return ps_stname;
    }

    public void setPs_stname(String ps_stname) {
        this.ps_stname = ps_stname;
    }

    public String getPS_STNO() {
        return PS_STNO;
    }

    public void setPS_STNO(String PS_STNO) {
        this.PS_STNO = PS_STNO;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public class StageItem {
        private String sn_id;
        private String sn_stname;
        private String sn_state;
        private boolean first = false;
        private boolean last = false;

        public String getSn_id() {
            return sn_id;
        }

        public void setSn_id(String sn_id) {
            this.sn_id = sn_id;
        }

        public String getSn_state() {
            return sn_state;
        }

        public void setSn_state(String sn_state) {
            this.sn_state = sn_state;
        }

        public String getSn_stname() {
            return sn_stname;
        }

        public void setSn_stname(String sn_stname) {
            this.sn_stname = sn_stname;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }
    }

}
