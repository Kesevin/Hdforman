package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by Rex on 2016/10/18.
 */

public class BaseData2<T> implements Serializable {

    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
