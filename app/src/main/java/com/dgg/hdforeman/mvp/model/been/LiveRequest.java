package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by kelvin on 2016/11/9.
 */

public class LiveRequest {
    private String projectId ;
    private int pageStart ;
    private int pageSize;

    public LiveRequest(String projectId, int pageStart, int pageSize) {
        this.projectId = projectId;
        this.pageStart = pageStart;
        this.pageSize = pageSize;
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

    public String getProid() {
        return projectId;
    }

    public void setProid(String projectId) {
        this.projectId = projectId;
    }
}
