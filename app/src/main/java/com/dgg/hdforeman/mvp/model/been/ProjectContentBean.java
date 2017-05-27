package com.dgg.hdforeman.mvp.model.been;

import java.util.List;

/**
 * Created by kelvin on 2016/11/15.
 */

public class ProjectContentBean {
    private String id;//空间id
    private String pe_acreage; //面价
    private String pe_name;//空间名称
    private String pu_ugname;
    private String pu_ugunit;
    private String pu_number;
    private List<UpgradePackageBean> m;


    private String pu_ugid;
    private String pu_ugprice;

    public String getPu_ugid() {
        return pu_ugid;
    }

    public String getPu_ugprice() {
        return pu_ugprice;
    }

    public String getPu_ugname() {
        return pu_ugname;
    }

    public String getPu_ugunit() {
        return pu_ugunit;
    }

    public String getPu_number() {
        return pu_number;
    }

    public String getId() {
        return id;
    }

    public String getPe_acreage() {
        return pe_acreage;
    }

    public String getPe_name() {
        return pe_name;
    }

    public List<UpgradePackageBean> getM() {
        return m;
    }
}
