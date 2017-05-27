package com.dgg.hdforeman.mvp.model.been;

import com.dgg.hdforeman.mvp.model.api.Api;

import java.util.List;

/**
 * Created by kelvin on 2016/11/15.
 */

public class ProjectListResponse {
    private String code;
    private String msg;
    private List<ProjectContentBean> kj;
    private List<ProjectTotalBean> jg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<ProjectContentBean> getKj() {
        return kj;
    }

    public List<ProjectTotalBean> getJg() {
        return jg;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (code.equals(Api.RequestSuccess)) {
            return true;
        } else {
            return false;
        }
    }
}
