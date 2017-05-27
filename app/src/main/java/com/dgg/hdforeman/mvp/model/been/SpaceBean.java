package com.dgg.hdforeman.mvp.model.been;

import com.dgg.hdforeman.app.config.GlobalConfig;
import com.dgg.hdforeman.app.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class SpaceBean {

    /**
     * allforests : 230
     * spacename : [{"id":7481705570114998000,"pe_name":"厨房","pe_perimeter":10,"pe_acreage":100},{"id":7481705579501851000,"pe_name":"厕所","pe_perimeter":10,"pe_acreage":15},{"id":7481706052799697000,"pe_name":"厨房","pe_perimeter":10,"pe_acreage":100},{"id":7481706052799697000,"pe_name":"厕所","pe_perimeter":10,"pe_acreage":15}]
     * allperimeter : 40
     * proid : 4
     */

    private float allforests;
    private String proid;
    private List<SpacenameEntity> spacename;
    private String pm_housetype;

    public String getPm_housetype() {
        return pm_housetype;
    }

    public void setPm_housetype(String pm_housetype) {
        this.pm_housetype = pm_housetype;
    }

    public float getAllforests() {
        return CommonUtil.numformater4(allforests);
    }

    public void setAllforests(float allforests) {
        this.allforests = CommonUtil.numformater4(allforests);
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public List<SpacenameEntity> getSpacename() {
        if (spacename == null) {
            spacename = new ArrayList<>();
        }
        return spacename;
    }


    public void setSpacename(List<SpacenameEntity> spacename) {
        this.spacename = spacename;
    }

    public static class SpacenameEntity implements Cloneable{


        public SpacenameEntity copy() {
            try {
                return (SpacenameEntity) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                return null;
            }
        }


        /**
         * id : 7481705570114998000
         * pe_name : 厨房
         * pe_perimeter : 10
         * pe_acreage : 100
         */


        private long id;
        private String pe_name;
        private float pe_acreage;

        public int getSpacetype() {

            if (pe_name.startsWith(GlobalConfig.names[0])) {
                return 1;
            } else if (pe_name.startsWith(GlobalConfig.names[1])) {
                return 2;
            } else if (pe_name.startsWith(GlobalConfig.names[2])) {
                return 3;
            } else if (pe_name.startsWith(GlobalConfig.names[3])) {
                return 4;
            } else if (pe_name.startsWith(GlobalConfig.names[4])) {
                return 5;
            } else {
                return 0;
            }
        }

        public void setSpacetype(int spacetype) {
            this.spacetype = spacetype;
        }

        private int spacetype;

        private boolean isSelect;
        private float input_acreage;
        private float pre_acreage;

        public float getPre_acreage() {
            return pre_acreage;
        }

        public void setPre_acreage(float pre_acreage) {
            this.pre_acreage = pre_acreage;
        }

        public float getInput_acreage() {
            return input_acreage;
        }

        public void setInput_acreage(float input_acreage) {
            this.input_acreage = input_acreage;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public List<CalItem> getCalItems() {
            return calItems;
        }

        public void setCalItems(List<CalItem> calItems) {
            this.calItems = calItems;
        }

        private List<CalItem> calItems;

        public List<CalItem> getCallItems() {
            if (calItems == null) {
                calItems = new ArrayList<>();
            }
            return calItems;
        }

        public float getLen() {
            return len;
        }

        public void setLen(float len) {
            this.len = len;
        }

        public float getWid() {
            return wid;
        }

        public void setWid(float wid) {
            this.wid = wid;
        }

        private float len;
        private float wid;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPe_name() {
            return pe_name;
        }

        public void setPe_name(String pe_name) {
            this.pe_name = pe_name;
        }

        public float getPe_acreage() {
            return CommonUtil.numformater4(pe_acreage);
        }

        public void setPe_acreage(float pe_acreage) {

            this.pe_acreage = CommonUtil.numformater4(pe_acreage);
        }
    }

    public static class CalItem {
        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public float getResult() {
            return CommonUtil.numformater4(result);
        }

        public void setResult(float result) {
            this.result = CommonUtil.numformater4(result);
        }

        public String expression;

        public String getIdexpression() {
            return idexpression;
        }

        public void setIdexpression(String idexpression) {
            this.idexpression = idexpression;
        }

        public String idexpression;
        public float result;
    }
}
