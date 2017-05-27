package com.dgg.hdforeman.mvp.model.been;

import java.io.Serializable;

/**
 * Created by jess on 22/11/2016 15:24
 * Contact with jess.yan.effort@gmail.com
 */

public class StuffInfo implements Serializable {

    @Override
    public boolean equals(Object o) {
        return id.equals(((StuffInfo)o).getId());
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    /**
     * id : 7479513702333681664
     * price : 0
     * unit : 0
     * name : 打拆
     * parentid : 0
     */

    private String id;
    private float price;
    private String unit;
    private String name;
    private String parentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}
