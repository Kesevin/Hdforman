package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by kelvin on 2016/11/9.
 */

public class MaterialRequest {
    private String pf_proid ;
    private int order;
    private int pf_type;
    private int pageStart ;
    private int pageSize;
    public MaterialRequest(String pf_proid,  int order,int type, int pageStart, int pageSize) {
        this.pf_proid = pf_proid;
        this.pageStart = pageStart;
        this.pageSize = pageSize;
        this.order = order;
        this.pf_type = type;
    }
    public MaterialRequest() {
    }
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public String getPf_proid() {
        return pf_proid;
    }

    public void setPf_proid(String pf_proid) {
        this.pf_proid = pf_proid;
    }

    public int getPf_type() {
        return pf_type;
    }

    public void setPf_type(int pf_type) {
        this.pf_type = pf_type;
    }
}
