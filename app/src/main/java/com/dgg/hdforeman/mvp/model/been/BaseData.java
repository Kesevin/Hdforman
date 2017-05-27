package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by Rex on 2016/10/18.
 */

public class BaseData<T> implements Serializable {

    private String code;
    private String msg;
    private T d;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getD() {
        return d;
    }

    public void setD(T d) {
        this.d = d;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (code.equals("0")) {
            return true;
        } else {
            return false;
        }
    }
}
