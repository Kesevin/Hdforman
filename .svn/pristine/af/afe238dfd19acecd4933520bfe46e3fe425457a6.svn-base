package com.dgg.hdforeman.mvp.ui.tool.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.base.BaseActivity;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.jess.arms.utils.DataHelper;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.app.HDApplication.COOKIES;


public class AnnouncementDetailActivity extends BaseActivity {


    @BindView(R.id.toolbar_back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.myProgressBar)
    ProgressBar myProgressBar;
    @BindView(R.id.webview)
    WebView webview;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_detail_activity);
    }


    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        mUrl=bundle.getString("url");
        String titleStr=bundle.getString("title");
        title.setText(titleStr);
//        mUrl="http://www.baidu.com";
        WebSettings webSettings =   webview .getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                syncCookie(url);
                view.loadUrl(url);
                return true;
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    myProgressBar.setVisibility(View.GONE);
                } else {
                    if (View.GONE == myProgressBar.getVisibility()) {
                        myProgressBar.setVisibility(View.VISIBLE);
                    }
                    myProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        Log.i("msg","httpUrl=============="+mUrl);
        syncCookie(mUrl);
        webview.loadUrl(mUrl);
    }

    private void syncCookie(String url) {
        try {
            CookieSyncManager.createInstance(this);//创建一个cookie管理器
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除以前的cookie
            cookieManager.removeAllCookie();
            String cookies=DataHelper.getStringSF(this,COOKIES);
            String[] cookiess=cookies.split("&&&");
            for (String cookie:cookiess) {
                if(!TextUtils.isEmpty(cookie)){
                    cookieManager.setCookie(url,cookie);
                }
            }
            CookieSyncManager.getInstance().sync();//同步cookie
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({  R.id.toolbar_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
        }
    }
}
