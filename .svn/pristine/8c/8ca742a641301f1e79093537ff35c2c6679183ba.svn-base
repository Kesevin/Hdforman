package com.dgg.hdforeman.mvp.ui.project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.ui.project.adapter.ProjectPagerAdapter;
import com.dgg.hdforeman.mvp.ui.project.fragment.BudgetFragment;
import com.dgg.hdforeman.mvp.ui.project.fragment.LimitTimeFragment;
import com.dgg.hdforeman.mvp.ui.widget.autolayout.AutoToolbar;
import com.jess.arms.base.BaseFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.project.activity.ProjectInfoActivity.PROJECTINFO_DATA;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by Administrator on 2016/11/28.
 */

public class BudgetSheetActivity extends BacksActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_menu)
    TextView rightMenu;
    @BindView(R.id.toolbar_back)
    ImageButton back;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.vp_project)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    AutoToolbar toolbar;

    private List<BaseFragment> fragmentList = new ArrayList<>();
    private List<String> mTabNameList = new ArrayList<>();
    private ProjectPagerAdapter mAdapter;
    private ProjectInfoResponse mData;

    @Override
    protected void ComponentInject() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.project_fragment, null);
    }

    @Override
    protected void initData() {
        title.setText("预算单");
        rightMenu.setVisibility(View.VISIBLE);
        rightMenu.setText("编辑");
        BudgetFragment mBudgetFragment = BudgetFragment.newInstance();
        LimitTimeFragment mLimitTimeFragment = new LimitTimeFragment();
        mData = (ProjectInfoResponse) getIntent().getExtras().getSerializable(PROJECTINFO_DATA);
        mTabNameList.add(getString(R.string.project_budget));
        mTabNameList.add(getString(R.string.project_limit_time));
        fragmentList.add(mBudgetFragment);
        fragmentList.add(mLimitTimeFragment);
        initViewPager();
        initTabLayout();
        initListener();
        mBudgetFragment.setData(mData);
        mLimitTimeFragment.setData(mData);
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        mAdapter = new ProjectPagerAdapter(getSupportFragmentManager(), fragmentList, mTabNameList);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout() {
        for (int i = 0; i < mTabNameList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab());
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }


    /**
     * 添加viewPager, TabLayout监听事件
     */
    private void initListener() {
        if (mViewPager != null)
            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        if (mTabLayout != null)
            mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    mViewPager.setCurrentItem(tab.getPosition());//选择对应的页面
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
    }

    @OnClick({R.id.toolbar_back, R.id.right_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                finish();
                break;
            case R.id.right_menu:
                launchWithData(ProjectQuoteActivity.class);
                break;
        }
    }

    /**
     * 附带数据跳转到相应页面
     */
    private void launchWithData(Class<?> cls) {
        Intent intent = new Intent(mApplication, cls);
        intent.putExtras(composeBundle(mData, PROJECTINFO_DATA));
        launchActivity(intent);
    }

    /**
     * 填充数据到bundle
     * @param data
     * @param type
     * @return
     */
    protected Bundle composeBundle(Serializable data, String type) {
        Bundle bundle = new Bundle();//用bundle封装数据
        bundle.putSerializable(type, data);
        return bundle;
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }
}