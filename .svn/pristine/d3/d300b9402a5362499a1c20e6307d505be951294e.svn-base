package com.dgg.hdforeman.mvp.model.been;

import com.dgg.hdforeman.mvp.model.api.Api;

import java.io.Serializable;

/**
 * Created by HSAEE on 2016/11/9.
 */

public class BaseJson<T> implements Serializable {
    private String code;
    private String msg;
    private T d;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getD() {
        return d;
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
