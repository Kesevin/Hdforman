package com.dgg.hdforeman.mvp.model.been;

import java.util.List;

/**
 * Created by tsang on 2016/10/25.
 */

public class ConstructProgress {


    /**
     * d : [{"ps_id":5,"ps_proid":"1","PS_STNO":1,"ps_stname":"水电及改造保护阶段","ps_period":15,"ps_startdate":"2016-10-27 00:00:00","ps_enddate":"2016-10-27 00:00:00","ps_state":0,"list":[{"sn_id":0,"sn_stname":"铲墙及门窗保护","sn_state":0},{"sn_id":1,"sn_stname":"墙地面固封","sn_state":1},{"sn_id":2,"sn_stname":"卫生间下水道改造","sn_state":0},{"sn_id":3,"sn_stname":"打拆墙体","sn_state":0}]},{"ps_id":6,"ps_proid":"1","PS_STNO":2,"ps_stname":"木工及其他隐蔽阶段","ps_period":12,"ps_startdate":"2016-10-24 00:00:00","ps_enddate":"2016-10-24 00:00:00","ps_state":0,"list":[{"sn_id":4,"sn_stname":"铲墙及门窗保护","sn_state":0},{"sn_id":5,"sn_stname":"墙地面固封","sn_state":0},{"sn_id":6,"sn_stname":"马桶安装","sn_state":0},{"sn_id":7,"sn_stname":"打拆墙体","sn_state":0}]},{"ps_id":7,"ps_proid":"1","PS_STNO":3,"ps_stname":"泥作工程阶段","ps_period":3,"ps_startdate":"2016-10-25 00:00:00","ps_enddate":"2016-10-25 00:00:00","ps_state":0,"list":[{"sn_id":8,"sn_stname":"地板水泥铺设","sn_state":0},{"sn_id":9,"sn_stname":"墙面水泥工作","sn_state":0}]},{"ps_id":8,"ps_proid":"1","PS_STNO":4,"ps_stname":"墙面基层处理阶段","ps_period":1,"ps_startdate":"2016-10-25 00:00:00","ps_enddate":"2016-10-25 00:00:00","ps_state":0,"list":[]}]
     * code : 0
     * msg : 操作成功
     */

    /**
     * ps_id : 5
     * ps_proid : 1
     * PS_STNO : 1
     * ps_stname : 水电及改造保护阶段
     * ps_period : 15.0
     * ps_startdate : 2016-10-27 00:00:00
     * ps_enddate : 2016-10-27 00:00:00
     * ps_state : 0
     * list : [{"sn_id":0,"sn_stname":"铲墙及门窗保护","sn_state":0},{"sn_id":1,"sn_stname":"墙地面固封","sn_state":1},{"sn_id":2,"sn_stname":"卫生间下水道改造","sn_state":0},{"sn_id":3,"sn_stname":"打拆墙体","sn_state":0}]
     */

    private List<PDBean> d;

    public List<PDBean> getD() {
        return d;
    }

    public void setD(List<PDBean> d) {
        this.d = d;
    }

    public static class PDBean{
        private List<DBean> stage;
        private int shutdown;

        public List<DBean> getStage() {
            return stage;
        }

        public void setStage(List<DBean> stage) {
            this.stage = stage;
        }

        public int getShutdown() {
            return shutdown;
        }

        public void setShutdown(int shutdown) {
            this.shutdown = shutdown;
        }

        public static class DBean {
            private String ps_id;
            private String ps_proid;
            private int PS_STNO;
            private String ps_stname;
            private double ps_period;
            private String ps_startdate;
            private String ps_enddate;
            private int ps_state;
            private List<ListBean> list;

            public void setPs_state(int ps_state) {
                this.ps_state = ps_state;
            }

            public String getPs_id() {
                return ps_id;
            }

            public String getPs_proid() {
                return ps_proid;
            }

            public int getPS_STNO() {
                return PS_STNO;
            }

            public String getPs_stname() {
                return ps_stname;
            }

            public double getPs_period() {
                return ps_period;
            }

            public String getPs_startdate() {
                return ps_startdate;
            }

            public String getPs_enddate() {
                return ps_enddate;
            }

            public int getPs_state() {
                return ps_state;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private String sn_id;
                private String sn_stname;
                private int sn_state;
                private boolean isFirst;
                private boolean isEnd;
                private String sn_enddate;
                private int statgeNum;
                private boolean isCanFinish;

                public boolean isCanFinish() {
                    return isCanFinish;
                }

                public void setCanFinish(boolean canFinish) {
                    isCanFinish = canFinish;
                }

                public int getStatgeNum() {
                    return statgeNum;
                }

                public void setStatgeNum(int statgeNum) {
                    this.statgeNum = statgeNum;
                }

                public String getSn_enddate() {
                    return sn_enddate;
                }

                public void setSn_enddate(String sn_enddate) {
                    this.sn_enddate = sn_enddate;
                }

                public boolean isFirst() {
                    return isFirst;
                }

                public void setFirst(boolean first) {
                    isFirst = first;
                }

                public boolean isEnd() {
                    return isEnd;
                }

                public void setEnd(boolean end) {
                    isEnd = end;
                }

                public String getSn_id() {
                    return sn_id;
                }

                public String getSn_stname() {
                    return sn_stname;
                }

                public int getSn_state() {
                    return sn_state;
                }

                public void setSn_state(int sn_state) {
                    this.sn_state = sn_state;
                }
            }
        }
    }



}
