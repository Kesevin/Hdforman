package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by Administrator on 2016/10/27.
 * {
 "d": {
 "wk_name": "穆念慈",
 "wk_contactno": "12365479846"
 },
 "code": "0",
 "msg": "操作成功"
 }
 */
public class TeamSearchResult {
    private Long wk_userid;
    private String wk_headpic;
    private String wk_name;//姓名
    private String wk_contactno;//联系电话

    public String getWk_name() {
        return wk_name;
    }

    public void setWk_name(String wk_name) {
        this.wk_name = wk_name;
    }

    public String getWk_contactno() {
        return wk_contactno;
    }

    public void setWk_contactno(String wk_contactno) {
        this.wk_contactno = wk_contactno;
    }

    public Long getWk_userid() {
        return this.wk_userid;
    }

    public void setWk_userid(Long wk_userid) {
        this.wk_userid = wk_userid;
    }

    public String getWk_headpic() {
        return this.wk_headpic;
    }

    public void setWk_headpic(String wk_headpic) {
        this.wk_headpic = wk_headpic;
    }
}
