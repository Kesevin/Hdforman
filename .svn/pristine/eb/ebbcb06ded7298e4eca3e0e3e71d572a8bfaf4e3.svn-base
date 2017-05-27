package com.dgg.hdforeman.mvp.model.been;

import java.util.List;

/**
 * author:zhangjing
 * 作用:
 * return:
 */

public class RequestSpaceBean {

    /**
     * proid : 项目id
     * allperimeter :
     * allforests :
     * spacename : [{"measureresult":"空间名称","perimeter":"周长","forests":"面积"}]
     */

    private String proid;
    private String allforests;
    private List<SpacenameEntity> spacename;

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getAllforests() {
        return allforests;
    }

    public void setAllforests(String allforests) {
        this.allforests = allforests;
    }

    public List<SpacenameEntity> getSpacename() {
        return spacename;
    }

    public void setSpacename(List<SpacenameEntity> spacename) {
        this.spacename = spacename;
    }

    public static class SpacenameEntity {
        /**
         * measureresult : 空间名称
         * perimeter : 周长
         * forests : 面积
         */

        private String measureresult;
        private String perimeter;
        private String forests;

        public int getSpacetype() {
            return spacetype;
        }

        public void setSpacetype(int spacetype) {
            this.spacetype = spacetype;
        }

        private int spacetype;

        public String getMeasureresult() {
            return measureresult;
        }

        public void setMeasureresult(String measureresult) {
            this.measureresult = measureresult;
        }

        public String getPerimeter() {
            return perimeter;
        }

        public void setPerimeter(String perimeter) {
            this.perimeter = perimeter;
        }

        public String getForests() {
            return forests;
        }

        public void setForests(String forests) {
            this.forests = forests;
        }
    }
}
