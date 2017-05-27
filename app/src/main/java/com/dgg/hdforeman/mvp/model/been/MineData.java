package com.dgg.hdforeman.mvp.model.been;

/**
 {
 "msg": "操作成功",
 "d": {
 "wk_grade": 0,
 "wk_bank": "未知银行",
 "wk_bankno": "****1237",
 "wk_identityno": "5002****2623",
 "wk_worktype": 0,
 "wk_contactno": "157****4450",
 "wk_homeaddress": "成都",
 "wk_workage": 1
 },
 "code": "0"
 }
 */

public class MineData {
    private String wk_contactno;//绑定手机号
    private int wk_workage;//装修经验
    private int wk_worktype;//工种
    private String wk_homeaddress;//常住地址
    private String wk_identityno;//身份证号
    private String wk_bankno;//银行卡号
    private float wk_grade;
    private String wk_bank;

    public String getWk_bank() {
        return wk_bank;
    }

    public void setWk_bank(String wk_bank) {
        this.wk_bank = wk_bank;
    }

    public float getWk_grade() {
        return wk_grade;
    }

    public void setWk_grade(float wk_grade) {
        this.wk_grade = wk_grade;
    }

    public String getWk_contactno() {
        return wk_contactno;
    }

    public void setWk_contactno(String wk_contactno) {
        this.wk_contactno = wk_contactno;
    }

    public int getWk_workage() {
        return wk_workage;
    }

    public void setWk_workage(int wk_workage) {
        this.wk_workage = wk_workage;
    }

    public int getWk_worktype() {
        return wk_worktype;
    }

    public void setWk_worktype(int wk_worktype) {
        this.wk_worktype = wk_worktype;
    }

    public String getWk_homeaddress() {
        return wk_homeaddress;
    }

    public void setWk_homeaddress(String wk_homeaddress) {
        this.wk_homeaddress = wk_homeaddress;
    }

    public String getWk_identityno() {
        return wk_identityno;
    }

    public void setWk_identityno(String wk_identityno) {
        this.wk_identityno = wk_identityno;
    }

    public String getWk_bankno() {
        return wk_bankno;
    }

    public void setWk_bankno(String wk_bankno) {
        this.wk_bankno = wk_bankno;
    }

    @Override
    public String toString() {
        return "MineData{" +
                "wk_contactno='" + wk_contactno + '\'' +
                ", wk_workage=" + wk_workage +
                ", wk_worktype=" + wk_worktype +
                ", wk_homeaddress='" + wk_homeaddress + '\'' +
                ", wk_identityno='" + wk_identityno + '\'' +
                ", wk_bankno='" + wk_bankno + '\'' +
                '}';
    }
}
