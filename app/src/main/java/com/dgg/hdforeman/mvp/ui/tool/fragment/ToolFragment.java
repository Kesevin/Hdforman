package com.dgg.hdforeman.mvp.ui.tool.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.config.GlobalConfig;
import com.dgg.hdforeman.mvp.model.api.Api;
import com.dgg.hdforeman.mvp.ui.base.BaseFragment;
import com.dgg.hdforeman.mvp.ui.tool.activity.AnnouncementActivity;
import com.dgg.hdforeman.mvp.ui.tool.activity.AnnouncementDetailActivity;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Rex on 2016/10/20.
 */

public class ToolFragment extends BaseFragment {

    @BindView(R.id.toolbar_back)
    ImageButton back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.more_btn)
    ImageButton moreBtn;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.title_lay)
    AutoFrameLayout titleLay;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;
    @BindView(R.id.right_arrow)
    TextView rightArrow;
    @BindView(R.id.announcement_layout)
    AutoLinearLayout announcementLayout;
    @BindView(R.id.biaozhun_layout)
    AutoLinearLayout biaozhunLayout;
    @BindView(R.id.zhucai_layout)
    AutoLinearLayout zhucaiLayout;
    @BindView(R.id.jichubao_layout)
    AutoLinearLayout jichubaoLayout;
    @BindView(R.id.mingxibiao_layout)
    AutoLinearLayout mingxibiaoLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tool_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView() {
        title.setText("工具");
        back.setVisibility(View.GONE);
    }

    @OnClick({R.id.announcement_layout, R.id.biaozhun_layout, R.id.zhucai_layout, R.id.jichubao_layout, R.id.mingxibiao_layout})
    public void onClick(View view) {
        String url= Api.APP_DOMAIN+"/orgmain.nk?id=";
        Bundle bundle=new Bundle();
        switch (view.getId()) {
            case R.id.announcement_layout:
                readyGo(AnnouncementActivity.class);
                break;
            case R.id.biaozhun_layout:
                bundle.putString("url",url+"1");
                bundle.putString("title","基础装修施工工艺及验收标准");
                readyGo(AnnouncementDetailActivity.class,bundle);
                break;
            case R.id.zhucai_layout:
                bundle.putString("url",url+"2");
                bundle.putString("title","主材安装施工工艺及验收标准");
                readyGo(AnnouncementDetailActivity.class,bundle);
                break;
            case R.id.jichubao_layout:
                bundle.putString("url",url+"3");
                bundle.putString("title","标准基础包");
                readyGo(AnnouncementDetailActivity.class,bundle);
                break;
            case R.id.mingxibiao_layout:
                bundle.putString("url",url+"4");
                bundle.putString("title","个性项报价明细表");
                readyGo(AnnouncementDetailActivity.class,bundle);
                break;
        }
    }
}
