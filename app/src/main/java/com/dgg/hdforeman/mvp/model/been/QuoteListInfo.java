package com.dgg.hdforeman.mvp.model.been;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by jess on 29/11/2016 16:11
 * Contact with jess.yan.effort@gmail.com
 */

public class QuoteListInfo {

    /**
     * id : 19
     * ug_type : 7484248082604101632
     * inlist : [{"id":19,"ug_name":"封阳台","ug_prostage":5},{"id":20,"ug_name":"钛合金门","ug_prostage":5},{"id":21,"ug_name":"窗台铺石材","ug_prostage":5},{"id":22,"ug_name":"贴墙纸","ug_prostage":5}]
     * ug_prostage : 5
     * ug_period : 1
     * ug_prostagename : 竣工阶段
     * ug_typename : 其他升级项
     */

    private String id;
    private long ug_type;
    private int ug_prostage;
    private String ug_period;
    private String ug_prostagename;
    private String ug_typename;
    /**
     * id : 19
     * ug_name : 封阳台
     * ug_prostage : 5
     */

    private List<InlistBean> inlist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUg_type() {
        return ug_type;
    }

    public void setUg_type(long ug_type) {
        this.ug_type = ug_type;
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

    public List<InlistBean> getInlist() {
        return inlist;
    }

    public void setInlist(List<InlistBean> inlist) {
        this.inlist = inlist;
    }

    public static class InlistBean implements MultiItemEntity{
        private String id;
        private String ug_name;
        private int ug_prostage;

        public boolean isselected() {
            return isselected;
        }

        public void setIsselected(boolean isselected) {
            this.isselected = isselected;
        }

        private boolean isselected;

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

        public int getUg_prostage() {
            return ug_prostage;
        }

        public void setUg_prostage(int ug_prostage) {
            this.ug_prostage = ug_prostage;
        }

        @Override
        public int getItemType() {
            if (ug_name.length()>8){
                return 2;
            }else {
                return 1;
            }
        }
    }
}
