package com.dgg.hdforeman.mvp.ui.mine.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/10/21.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_about_activity);
    }

    @Override
    protected void initView() {
        title.setText("关于小顶");
    }

    @OnClick(R.id.toolbar_back)
    public void onClick() {
        finish();
    }
}
