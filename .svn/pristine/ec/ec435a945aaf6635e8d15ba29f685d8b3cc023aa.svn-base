package com.dgg.hdforeman.mvp.ui.project.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.contract.project.ProjectContract;
import com.dgg.hdforeman.mvp.presenter.project.TypeItem1;
import com.dgg.hdforeman.mvp.presenter.project.TypeItem2;
import com.dgg.hdforeman.mvp.presenter.project.TypeItem3;
import com.dgg.hdforeman.mvp.presenter.project.TypeItem4;
import com.dgg.hdforeman.mvp.ui.base.HDFragment;
import com.dgg.hdforeman.mvp.ui.project.adapter.ProjectPagerAdapter;
import com.jess.arms.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment.FILL_LIST_ITEM;
import static com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment.TAB_METHOD;

/**
 * Created by Rex on 2016/10/20.
 */

public class ProjectFragment extends HDFragment implements ProjectContract.View {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar_back)
    ImageButton back;
    @Nullable
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @Nullable
    @BindView(R.id.vp_project)
    ViewPager mViewPager;

    private List<String> mTabNameList;
    private ProjectPagerAdapter mAdapter;
    private List<BaseFragment> fragments;

    private BaseFragment previousFragment;

    public static final String CONSTRUCTIONT = "2";//施工中
    public static final String SIGNED = "0";//待签约
    public static final String PENDING_CONSTRUCTIONT = "1";//待施工
    public static final String COMPLETED = "4";//已完工

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @Override
    protected View initView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.project_fragment, null, false);
    }

    @Override
    protected void initData() {
        title.setText("项目");
        back.setVisibility(View.GONE);
        mTabNameList = new ArrayList<String>();
        mTabNameList.add(getString(R.string.str_during_construction));
        mTabNameList.add(getString(R.string.str_wait_sign));
        mTabNameList.add(getString(R.string.str_prepare_construction));
        mTabNameList.add(getString(R.string.str_did_construction));
        fragments = new ArrayList<BaseFragment>();

        fragments.add(DuringConstructFragment.newInstance(new TypeItem1(mHDApplication, this),CONSTRUCTIONT));
        fragments.add(DuringConstructFragment.newInstance(new TypeItem2(mHDApplication, this),SIGNED));
        fragments.add(DuringConstructFragment.newInstance(new TypeItem3(mHDApplication, this),PENDING_CONSTRUCTIONT));
        fragments.add(DuringConstructFragment.newInstance(new TypeItem4(mHDApplication, this),COMPLETED));
        initViewPager();//初始化viewPager
        initTabLayout();//初始化tabLayout
        initListener();//初始化监听器

        initFirstFragmentData();
    }

    /**
     * 填充第一个fragment数据
     */
    private void initFirstFragmentData(){
        fragments.get(0).setData();
    }

    /**
     * 初始化viewPager
     */
    private void initViewPager() {
        previousFragment = fragments.get(0);
        mAdapter = new ProjectPagerAdapter(getFragmentManager(), fragments, mTabNameList);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
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
     *  添加viewPager, TabLayout监听事件
     */
    private void initListener() {
        if (mViewPager != null)
            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    previousFragment = fragments.get(position);

                    Bundle bundle = new Bundle();//使用bundle对象和fragment通信
                    bundle.putInt(TAB_METHOD, FILL_LIST_ITEM);//指定方法
                    fragments.get(position).setData(bundle);//让当前的fragment刷新列表
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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void launchActivity(Intent intent) {
        getActivity().startActivity(intent);
    }

    @Override
    public void killMyself() {

    }
}
