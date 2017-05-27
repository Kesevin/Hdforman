package com.dgg.hdforeman.app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dgg.hdforeman.mvp.ui.mine.activity.MessageActivity;
import com.dgg.hdforeman.mvp.ui.tool.activity.AnnouncementActivity;
import com.jess.arms.utils.DataHelper;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/11/10.
 */

public class JPushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private static Long newsId;
    private static String url;
    private static int newsType=0;
    private static String title;
    private static String proid;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
//        Log.d(TAG, "[JPushReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[JPushReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[JPushReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//            processCustomMessage(context, bundle);
//            intentMsg(context,bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[JPushReceiver] 接收到推送下来的通知:" + bundle.getString(JPushInterface.EXTRA_EXTRA));
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            DataHelper.SetBooleanSF(context, "newMsg", true);
            EventBus.getDefault().post(true,"new_message");
            Log.d(TAG, "[JPushReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[JPushReceiver] 用户点击打开了通知");
            intentMsg(context,bundle);
            //打开自定义的Activity
//            Intent i = new Intent(context, AnnouncementActivity.class);
//            i.putExtras(bundle);
//            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//            context.startActivity(i);

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[JPushReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[JPushReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            Log.d(TAG, "[JPushReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            }else if(key.equals(JPushInterface.EXTRA_NOTIFICATION_TITLE)){
                title=bundle.getString(key);
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();
                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        if(myKey.equals("newsId") && !json.optString(myKey).isEmpty()){
                            newsId=Long.parseLong(json.optString(myKey));
                        }else if(myKey.equals("url") && !json.optString(myKey).isEmpty()){
                            url=json.optString(myKey);
                        }else if(myKey.equals("type") && !json.optString(myKey).isEmpty()){
                            newsType=Integer.parseInt(json.optString(myKey));
                        }else if(myKey.equals("proid") && !json.optString(myKey).isEmpty()){
                            proid=json.optString(myKey);
                        }
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }
            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        Log.d(TAG, "[JPushReceiver] onReceive - " + ", extras: " + sb.toString());
        return sb.toString();
    }

    private static void intentMsg(Context context,Bundle bundle){
//        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        printBundle(bundle);
//        Log.i("msg","extras======"+extras);
//        if (!extras.isEmpty()) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (null != extraJson && extraJson.length() > 0) {
//                        String newId=extraJson.getString("newId");
//                        String proId=extraJson.getString("proid");
//                        String url=extraJson.getString("url");
//                        int newsType=extraJson.getInt("type");
                        Intent intent = new Intent();
                        Class<?> cls=null;
                        switch (newsType){//(1为公告2为停工复工类3为收入4为升级项材料5施工图片)
                            case 1:
                                cls= AnnouncementActivity.class;
                                break;
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                cls=MessageActivity.class;
                                Bundle bundle2 = new Bundle();//用bundle封装数据
                                bundle2.putString("type","2");
                                bundle2.putString("proId",proid);
                                intent.putExtras(bundle2);
                                break;
                            default:
                                Log.d(TAG, "[JPushReceiver] onReceive - No Type");
                                cls=AnnouncementActivity.class;
                                break;
                        }
//                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        intent.setClass(context,cls);
                        context.startActivity(intent);
//                    }
//                } catch (JSONException e) {
//                     e.printStackTrace();
//                }

            }

//        Intent i = new Intent(context, AnnouncementActivity.class);
//        i.putExtra("url",url);
//        i.putExtra("title",title);
////        i.putExtras(bundle);
//        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//        context.startActivity(i);
//    }


    //send msg to MainActivity
//    private void processCustomMessage(Context context, Bundle bundle) {
//        if (MainActivity.isForeground) {
//            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//            if (!ExampleUtil.isEmpty(extras)) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (null != extraJson && extraJson.length() > 0) {
//                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//                    }
//                } catch (JSONException e) {
//
//                }
//
//            }
//            context.sendBroadcast(msgIntent);
//        }
//    }
}
