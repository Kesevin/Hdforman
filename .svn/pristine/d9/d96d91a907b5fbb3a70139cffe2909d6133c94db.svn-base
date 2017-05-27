package com.dgg.hdforeman.mvp.ui.mine.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.mvp.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2016/10/21.
 */

public class ContractActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.contract_title)
    TextView contractTitle;
    @BindView(R.id.contract_content)
    TextView contractContent;
    @BindView(R.id.contract_content_title)
    TextView contractContentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_contract_activity);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        title.setText("合同协议");
    }

    @OnClick(R.id.toolbar_back)
    public void onClick() {
        finish();
    }
}
