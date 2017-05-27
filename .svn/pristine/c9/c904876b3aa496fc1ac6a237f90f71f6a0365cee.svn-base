package com.dgg.hdforeman.mvp.model.net;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by tsang on 2016/10/10.
 */

public class ApiUtil {
    public static final String generalCls = "ForemanApi";
    public static final String loginCls = "LoginApi";

    public static final String progressStr = "{\"d\":[{\"ps_id\":5,\"ps_proid\":\"1\",\"PS_STNO\":1,\"ps_stname\":\"水电及改造保护阶段\",\"ps_period\":15.0,\"ps_startdate\":\"2016-10-27 00:00:00\",\"ps_enddate\":\"2016-10-27 00:00:00\",\"ps_state\":0,\"list\":[{\"sn_id\":0,\"sn_stname\":\"铲墙及门窗保护\",\"sn_state\":0},{\"sn_id\":1,\"sn_stname\":\"墙地面固封\",\"sn_state\":1},{\"sn_id\":2,\"sn_stname\":\"卫生间下水道改造\",\"sn_state\":0},{\"sn_id\":3,\"sn_stname\":\"打拆墙体\",\"sn_state\":0}]},{\"ps_id\":6,\"ps_proid\":\"1\",\"PS_STNO\":2,\"ps_stname\":\"木工及其他隐蔽阶段\",\"ps_period\":12.0,\"ps_startdate\":\"2016-10-24 00:00:00\",\"ps_enddate\":\"2016-10-24 00:00:00\",\"ps_state\":0,\"list\":[{\"sn_id\":4,\"sn_stname\":\"铲墙及门窗保护\",\"sn_state\":0},{\"sn_id\":5,\"sn_stname\":\"墙地面固封\",\"sn_state\":0},{\"sn_id\":6,\"sn_stname\":\"马桶安装\",\"sn_state\":0},{\"sn_id\":7,\"sn_stname\":\"打拆墙体\",\"sn_state\":0}]},{\"ps_id\":7,\"ps_proid\":\"1\",\"PS_STNO\":3,\"ps_stname\":\"泥作工程阶段\",\"ps_period\":3.0,\"ps_startdate\":\"2016-10-25 00:00:00\",\"ps_enddate\":\"2016-10-25 00:00:00\",\"ps_state\":0,\"list\":[{\"sn_id\":8,\"sn_stname\":\"地板水泥铺设\",\"sn_state\":0},{\"sn_id\":9,\"sn_stname\":\"墙面水泥工作\",\"sn_state\":0}]},{\"ps_id\":8,\"ps_proid\":\"1\",\"PS_STNO\":4,\"ps_stname\":\"墙面基层处理阶段\",\"ps_period\":1.0,\"ps_startdate\":\"2016-10-25 00:00:00\",\"ps_enddate\":\"2016-10-25 00:00:00\",\"ps_state\":0,\"list\":[]}],\"code\":\"0\",\"msg\":\"操作成功\"}";

    public static final String projectInfoStr = "{\"d\":[{\"id\":1,\"pm_no\":\"3423c88d-99b0-11e6-bb8a-dd6b8143cd3c\",\"pm_housesname\":\"中德英伦联邦\",\"pm_roomno\":\"12栋1单元1004号\",\"pm_housesaddress\":\"成都市青羊区草市街110号\",\"pm_cusname\":\"dksaflff\",\"pm_cuscontactno\":\"18609975932\",\"pm_startdate\":\"2016-09-30 00:00:00\",\"pm_stopdate\":\"2016-09-26 00:00:00\",\"pm_planfinishdate\":\"2016-10-08 00:00:00\",\"pm_finishdate\":\"2016-10-08 00:00:00\",\"pm_stopdays\":0,\"stage\":\"水电及改造保护阶段\",\"stageState\":0},{\"id\":12,\"pm_housesname\":\"中德英伦联邦\",\"pm_roomno\":\"12栋1单元1004号\",\"pm_housesaddress\":\"成都市青羊区草市街110号\",\"pm_cusname\":\"wnag\",\"pm_cuscontactno\":\"13322222222\",\"pm_startdate\":\"2016-09-29 00:00:00\",\"pm_stopdate\":\"2016-11-04 00:00:00\",\"pm_finishdate\":\"2016-10-22 00:00:00\",\"pm_stopdays\":0,\"stage\":\"水电及改造保护阶段\",\"stageState\":0}],\"code\":\"0\",\"msg\":\"操作成功\"}";

    public static final String projectDetailStr = "{\"d\":[{\"pm_no\":\"1065453545600\",\"pm_cusname\":\"陈东\",\"pm_acreage\":80.0,\"pm_housesname\":\"中德英伦联邦\",\"pm_housetype\":\"三室两厅\",\"pm_housesaddress\":\"成都市青羊区草市街110号\",\"pm_roomno\":\"12栋1单元1004号\",\"pm_startdate\":\"2016-09-30 00:00:00\",\"pm_stopdate\":\"2016-10-26 00:00:00\",\"pm_finishdate\":\"2016-10-08 00:00:00\",\"pm_stopdays\":0,\"pm_prices\":24000.0,\"stage\":\"水电及改造保护阶段\",\"stagestate\":0}],\"code\":\"0\",\"msg\":\"操作成功\"}";


    public static String getParams(String className, String methodName, JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        try {
            json.put("c", className);
            json.put("m", methodName);
            json.put("d", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static <T> Observable<T> mockGetData(final Class<T> cls, final String json) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    Thread.sleep(1500);
                    T t = new Gson().fromJson(json, cls);
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
