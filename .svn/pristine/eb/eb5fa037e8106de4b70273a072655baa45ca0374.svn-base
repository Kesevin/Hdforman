package com.dgg.hdforeman.mvp.ui;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.app.utils.CommonUtil;
import com.dgg.hdforeman.app.utils.DensityUtil;
import com.dgg.hdforeman.app.utils.UpdateManager;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.di.commponent.DaggerMainComponent;
import com.dgg.hdforeman.di.module.MainModule;
import com.dgg.hdforeman.mvp.contract.MainContract;
import com.dgg.hdforeman.mvp.model.been.TabEntity;
import com.dgg.hdforeman.mvp.model.been.VersionInfo;
import com.dgg.hdforeman.mvp.presenter.MainPresenter;
import com.dgg.hdforeman.mvp.ui.income.fragment.IncomeFragment;
import com.dgg.hdforeman.mvp.ui.mine.fragment.MineFragment;
import com.dgg.hdforeman.mvp.ui.project.activity.BacksActivity;
import com.dgg.hdforeman.mvp.ui.project.fragment.ProjectFragment;
import com.dgg.hdforeman.mvp.ui.tool.fragment.ToolFragment;
import com.dgg.hdforeman.mvp.ui.widget.UnSlidingViewPager;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.jess.arms.utils.DataHelper;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static com.jess.arms.utils.DataHelper.CURRENT_USER_ID;

/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
             佛祖保佑       永无BUG
*/

public class MainActivity extends BacksActivity<MainPresenter> implements MainContract.View ,RadioGroup.OnCheckedChangeListener{


    @BindView(R.id.main_pager)
    UnSlidingViewPager mainPager;
    @BindView(R.id.tab_project)
    RadioButton tabProject;
    @BindView(R.id.tab_income)
    RadioButton tabIncome;
    @BindView(R.id.tab_tool)
    RadioButton tabTool;
    @BindView(R.id.tab_mine)
    RadioButton tabMine;
    @BindView(R.id.tab_group)
    RadioGroup tabGroup;
    @BindView(R.id.tab_lay)
    CommonTabLayout mTabLayout;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private TabAdapter mAdapter;
    private long exitTime = 0;
    MsgView rtv_2_2;
    private String[] mTitles = {"项目", "收入", "工具", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.project_nor, R.mipmap.income_nor,
            R.mipmap.tool_nor, R.mipmap.me_nor};
    private int[] mIconSelectIds = {
            R.mipmap.project_pre, R.mipmap.income_pre,
            R.mipmap.tool_pre, R.mipmap.me_pre};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.main_activity, null, false);
    }
    @Override
    protected void initData() {
        String userId = DataHelper.getStringSF(this, CURRENT_USER_ID);
        JPushInterface.setAlias(this, userId, new TagAliasCallback() {
            @Override
            public void gotResult(int arg0, String arg1, Set<String> arg2) {
                // arg1=0成功
            }
        });
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new ProjectFragment());
        mFragments.add(new IncomeFragment());
        mFragments.add(new ToolFragment());
        mFragments.add(new MineFragment());
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mainPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mTabLayout.showDot(3);
        mTabLayout.setMsgMargin(3, 0 ,-5);
      rtv_2_2 = mTabLayout.getMsgView(3);
        if (rtv_2_2 != null) {
            UnreadMsgUtils.setSize(rtv_2_2,DensityUtil.getPercentWidthSize(15));
        }
        rtv_2_2.setVisibility(View.GONE)  ;
        mAdapter = new TabAdapter(getSupportFragmentManager(), mFragments);
        mainPager.setAdapter(mAdapter);
        mainPager.setOffscreenPageLimit(3);
        tabGroup.setOnCheckedChangeListener(this);
        mPresenter.checkVersion();
    }


//    @Subscriber(tag = "", mode = ThreadMode.MAIN)
//    public void updateTab(boolean b) {
//
//    }
@Override
protected void setupActivityComponent(AppComponent appComponent) {
    DaggerMainComponent.builder().appComponent(appComponent).mainModule(new MainModule(this)).build().inject(this);
}
    @Subscriber(tag = "new_message", mode = ThreadMode.MAIN)
    public void showPoint(boolean b) {
        if(b){
        rtv_2_2.setVisibility(View.VISIBLE);}else{
            rtv_2_2.setVisibility(View.GONE)  ;
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        boolean flag = DataHelper.getBooleanSF(this,"newMsg");
        if(flag){
            rtv_2_2.setVisibility(View.VISIBLE);
        }else {
            rtv_2_2.setVisibility(View.GONE);
        }

    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.tab_project:
                mainPager.setCurrentItem(0);
                break;
            case R.id.tab_income:
                mainPager.setCurrentItem(1);
                break;
            case R.id.tab_tool:
                mainPager.setCurrentItem(2);
                break;
            case R.id.tab_mine:
                mainPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    protected boolean useEventBus() {
        return true;
    }


    @Override
    public void compareVersion(VersionInfo data) {
        if (data.getAppvalue() <= CommonUtil.getVersion(this))
            return;
        UpdateManager manager = new UpdateManager(MainActivity.this, data.getUpdateremark(), data.getAppversion(), data.getUploadurl(), data.getIfupdate());
        manager.showNoticeDialog();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            // AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // builder.setMessage("你确定退出吗？")
            // .setCancelable(false)
            // .setPositiveButton("确定",
            // new DialogInterface.OnClickListener() {
            // public void onClick(DialogInterface dialog,
            // int id) {
            // finish();
            // System.exit(0);
            // }
            // })
            // .setNegativeButton("返回",
            // new DialogInterface.OnClickListener() {
            // public void onClick(DialogInterface dialog,
            // int id) {
            // dialog.cancel();
            // }
            // });
            // AlertDialog alert = builder.create();
            // alert.show();
            // return true;
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                moveTaskToBack(false);
//                home键退出
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.addCategory(Intent.CATEGORY_HOME);
//                this.startActivity(intent);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
