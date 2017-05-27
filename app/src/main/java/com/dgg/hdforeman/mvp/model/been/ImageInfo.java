package com.dgg.hdforeman.mvp.model.been;

/**
 * Created by Rex on 2016/11/21.
 */

public class ImageInfo {
    private  String  path;
    // 0上传中，1 成功 ，2失败
    private int state;
    private int  position;
    private String backId;
    // 0本地，1 网络
    private int type;

    public ImageInfo(String path, int state,int type) {
        this.path = path;
        this.state = state;
        this.type=type;
    }

    public ImageInfo() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getBackId() {
        return backId;
    }

    public void setBackId(String backId) {
        this.backId = backId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    //    @Override
//    public boolean equals(Object obj) {
//        if(obj==null)
//            return false;
//        if(this == obj){
//            return true;
//        }
//        if (obj instanceof ImageInfo) {
//            ImageInfo other = (ImageInfo) .obj;
//            return  (other.path).equals(this.path);
//        }
//        return false;
//    }
}
