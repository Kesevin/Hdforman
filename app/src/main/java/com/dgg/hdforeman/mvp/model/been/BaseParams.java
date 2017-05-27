package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by kelvin on 2016/11/9.
 */

public class BaseParams<T> implements Serializable{
    private String c;
    private String m;
    private T d;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public T getD() {
        return d;
    }

    public void setD(T d) {
        this.d = d;
    }
}
