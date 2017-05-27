package com.dgg.hdforeman.app.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.widget.UpdateProgressDialog;
import com.dgg.hdforeman.mvp.ui.widget.VersionUpdateDialog;
import com.jess.arms.utils.DataHelper;
import com.jess.arms.utils.UiUtils;
import com.triggertrap.seekarc.SeekArc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kelvin on 2016/10/27.
 */

public class UpdateManager {
    private Context mContext; //上下文

    private String apkUrl;//apk下载地址
    private static final String savePath = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/WaiQin/FileCache"; //apk保存到SD卡的路径
    private String saveFileName; //完整路径名

    private SeekArc mProgress; //下载进度条控件
    private int progress; //下载进度

    private static final int DOWNLOADING = 1; //表示正在下载
    private static final int DOWNLOADED = 2; //下载完毕
    private static final int DOWNLOAD_FAILED = 3; //下载失败

    private boolean cancelFlag = false; //取消下载标志位

    private String serverVersion; //从服务器获取的版本号
//    private double clientVersion = 1.0; //客户端当前的版本号

    private String updateDescription = ""; //更新内容描述信息
    private boolean forceUpdate = true; //是否强制更新

    private VersionUpdateDialog updateDialog;//表示提示对话框
    private UpdateProgressDialog progressDialog; //进度条对话框
    private TextView mProgressContent;//百分比内容

    /**
     * 构造函数
     */
    public UpdateManager(Context context, String updateDescription, String serverVersion, String apkUrl, int ifupdate) {
        this.mContext = context;
        this.updateDescription = updateDescription;
        this.serverVersion = serverVersion;
        this.apkUrl = apkUrl;


        if (ifupdate == 0) {
            this.forceUpdate = true;
        }
    }

    /**
     * 显示更新对话框
     */
    public void showNoticeDialog() {
        VersionUpdateDialog.Builder dialog = new VersionUpdateDialog.Builder(mContext);
        dialog.setTitle("发现新版本 ：" + serverVersion);
        dialog.setMessage(updateDescription);
        dialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showDownloadDialog();
            }

        });
        //是否强制更新
        if (!forceUpdate) {
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int arg1) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            });
        }
        updateDialog = dialog.create();
        updateDialog.setCancelable(false);
        updateDialog.setCanceledOnTouchOutside(false);
        updateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (forceUpdate) {
                    return true;
                } else {
                    updateDialog.dismiss();
                    return false;
                }

            }
        });

        updateDialog.show();
    }

    /**
     * 显示进度条对话框
     */
    public void showDownloadDialog() {
        UpdateProgressDialog.Builder dialog = new UpdateProgressDialog.Builder(mContext);
//        dialog.setTitle("正在更新");
        View v = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress_layout, null);
        mProgress = (SeekArc) v.findViewById(R.id.update_progress);
        mProgressContent = (TextView) v.findViewById(R.id.update_progressContent);

        dialog.setContentView(v);
        dialog.animationStyle(R.style.WaitDialog);

//        //如果是强制更新，则不显示取消按钮
//        if (forceUpdate == false) {
//            dialog.setNegativeButton("取消", new OnClickListener() {
//                @Override
//                public void onClick(DialogInterface arg0, int arg1) {
//                    // TODO Auto-generated method stub
//                    arg0.dismiss();
//                    cancelFlag = false;
//                }
//            });
//        }

        progressDialog = dialog.create();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (forceUpdate) {
                    return true;
                } else {
                    cancelFlag = true;
                    progressDialog.dismiss();
                    return false;
                }
            }
        });
        progressDialog.show();
        //下载apk
        downloadAPK();
    }

    /**
     * 下载apk的线程
     */
    public void downloadAPK() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    URL url = new URL("http://apk.r1.market.hiapk.com/data/upload/apkres/2016/10_19/16/com.tencent.mtt_044304.apk");
                    URL url = new URL(apkUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    long length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

//                    File file = new File(savePath);
//                    if (!file.exists()) {
//                        file.mkdirs();
//                    }
                    File ApkFile = new File(DataHelper.getCacheFile(mContext), "/WaiQin_" + serverVersion + "外勤.apk");

//                    saveFileName = savePath + "/WaiQin_" + serverVersion + "外勤.apk";
//                    File ApkFile = new File(saveFileName);

                    if (!ApkFile.exists()) {
                        ApkFile.createNewFile();
                    }

                    FileOutputStream fos = new FileOutputStream(ApkFile);

                    int count = 0;
                    byte buf[] = new byte[4096];

                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        //更新进度
                        mHandler.sendEmptyMessage(DOWNLOADING);
                        if (numread <= 0) {
                            //下载完成通知安装
                            mHandler.sendEmptyMessage(DOWNLOADED);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelFlag); //点击取消就停止下载.

                    fos.close();
                    is.close();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 更新UI的handler
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case DOWNLOADING:
                    mProgress.setProgress(progress);
                    mProgressContent.setText(progress + "%");
                    break;
                case DOWNLOADED:
                    installAPK();
                    break;
                case DOWNLOAD_FAILED:
                    UiUtils.SnackbarText("网络异常，请稍后再试!");
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 下载完成后自动安装apk
     */
    public void installAPK() {
//        File apkFile = new File(saveFileName);
        File apkFile = new File(DataHelper.getCacheFile(mContext), "/WaiQin_" + serverVersion + "外勤.apk");
        if (!apkFile.exists()) {
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }
}