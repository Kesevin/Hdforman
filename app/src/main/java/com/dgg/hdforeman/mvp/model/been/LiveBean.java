package com.dgg.hdforeman.mvp.model.been;

import java.util.List;

/**
 * Created by HSAEE on 2016/11/8.
 */

public class LiveBean {
    private String  pt_stno;
    private String pt_stname;
    private String pt_state;
    private String pt_enddate;
    private String pt_wkid;
    private String pt_wkname;
    private String wk_headpic;
    private String wk_worktypename;
    private String pt_picture;

    public String getPt_picture() {
        return pt_picture;
    }

    public void setPt_picture(String pt_picture) {
        this.pt_picture = pt_picture;
    }

    public String getPt_enddate() {
        return pt_enddate;
    }

    public void setPt_enddate(String pt_enddate) {
        this.pt_enddate = pt_enddate;
    }

    public String getPt_state() {
        return pt_state;
    }

    public void setPt_state(String pt_state) {
        this.pt_state = pt_state;
    }

    public String getPt_stname() {
        return pt_stname;
    }

    public void setPt_stname(String pt_stname) {
        this.pt_stname = pt_stname;
    }

    public String getPt_stno() {
        return pt_stno;
    }

    public void setPt_stno(String pt_stno) {
        this.pt_stno = pt_stno;
    }

    public String getPt_wkid() {
        return pt_wkid;
    }

    public void setPt_wkid(String pt_wkid) {
        this.pt_wkid = pt_wkid;
    }

    public String getPt_wkname() {
        return pt_wkname;
    }

    public void setPt_wkname(String pt_wkname) {
        this.pt_wkname = pt_wkname;
    }

    public String getWk_headpic() {
        return wk_headpic;
    }

    public void setWk_headpic(String wk_headpic) {
        this.wk_headpic = wk_headpic;
    }

    public String getWk_worktypename() {
        return wk_worktypename;
    }

    public void setWk_worktypename(String wk_worktypename) {
        this.wk_worktypename = wk_worktypename;
    }
}
