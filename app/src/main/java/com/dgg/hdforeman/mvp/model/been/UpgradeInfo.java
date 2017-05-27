package com.dgg.hdforeman.mvp.model.been;

import java.util.List;

/**
 * Created by jess on 21/11/2016 15:52
 * Contact with jess.yan.effort@gmail.com
 */

public class UpgradeInfo {

    /**
     * id : 37
     * ug_name : 卫生间增加数
     * ug_type : 7487485499003899904
     * ug_price : 2000
     * ug_prostage : 1
     * ug_period : 0
     * ug_unit : 个
     * ug_prostagename : 水电改造及成品保护阶段
     * ug_typename : 卫生间增加数
     */

    private InfoBean toilet;
    /**
     * id : 34
     * ug_name : 远程施工
     * ug_type : 7487478253666701312
     * ug_price : 100
     * ug_prostage : 5
     * ug_period : 0
     * ug_unit : 平方米(/㎡)
     * ug_prostagename : 竣工阶段
     * ug_typename : 远程施工
     */

    private List<InfoBean> action;
    /**
     * id : 33
     * ug_name : 总数
     * ug_type : 7487478146472873984
     * ug_price : 200
     * ug_prostage : 3
     * ug_period : 3
     * ug_unit : 米(m)
     * ug_prostagename : 泥作阶段
     * ug_typename :   石膏线使用（多填或不填）
     */

    private InfoBean line;
    /**
     * id : 30
     * ug_name : 增加水电位
     * ug_type : 7487478001622585344
     * ug_price : 300
     * ug_prostage : 1
     * ug_period : 3
     * ug_unit : 个
     * ug_prostagename : 水电改造及成品保护阶段
     * ug_typename :   增加水电位（多填或不填）
     */

    private InfoBean potential;
    /**
     * id : 31
     * ug_name : 木质基层电视墙（不含饰面材料）
     * ug_type : 7487477885465530368
     * ug_price : 800
     * ug_prostage : 4
     * ug_period : 2
     * ug_unit : 平方米(/㎡)
     * ug_prostagename : 墙面基层阶段
     * ug_typename :   电视墙造型（多填或不填）
     */

    private List<InfoBean> tv;

    public InfoBean getToilet() {
        return toilet;
    }

    public void setToilet(InfoBean toilet) {
        this.toilet = toilet;
    }

    public List<InfoBean> getAction() {
        return action;
    }

    public void setAction(List<InfoBean> action) {
        this.action = action;
    }

    public InfoBean getLine() {
        return line;
    }

    public void setLine(InfoBean line) {
        this.line = line;
    }

    public InfoBean getPotential() {
        return potential;
    }

    public void setPotential(InfoBean potential) {
        this.potential = potential;
    }


    public List<InfoBean> getTv() {
        return tv;
    }

    public void setTv(List<InfoBean> tv) {
        this.tv = tv;
    }

    public static class InfoBean {
        private String id;
        private String ug_name;
        private long ug_type;
        private int ug_price;
        private int ug_prostage;
        private String ug_period;
        private String input_num;
        private String ug_unit;
        private String ug_prostagename;
        private String ug_typename;


        public String getInput_num() {
            return input_num;
        }

        public void setInput_num(String input_num) {
            this.input_num = input_num;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUg_name() {
            return ug_name;
        }

        public void setUg_name(String ug_name) {
            this.ug_name = ug_name;
        }

        public long getUg_type() {
            return ug_type;
        }

        public void setUg_type(long ug_type) {
            this.ug_type = ug_type;
        }

        public int getUg_price() {
            return ug_price;
        }

        public void setUg_price(int ug_price) {
            this.ug_price = ug_price;
        }

        public int getUg_prostage() {
            return ug_prostage;
        }

        public void setUg_prostage(int ug_prostage) {
            this.ug_prostage = ug_prostage;
        }

        public String getUg_period() {
            return ug_period;
        }

        public void setUg_period(String ug_period) {
            this.ug_period = ug_period;
        }

        public String getUg_unit() {
            return ug_unit;
        }

        public void setUg_unit(String ug_unit) {
            this.ug_unit = ug_unit;
        }

        public String getUg_prostagename() {
            return ug_prostagename;
        }

        public void setUg_prostagename(String ug_prostagename) {
            this.ug_prostagename = ug_prostagename;
        }

        public String getUg_typename() {
            return ug_typename;
        }

        public void setUg_typename(String ug_typename) {
            this.ug_typename = ug_typename;
        }
    }

}
