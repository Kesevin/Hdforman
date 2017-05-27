package com.dgg.hdforeman.mvp.ui.project.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dgg.hdforeman.R;
import com.dgg.hdforeman.di.commponent.AppComponent;
import com.dgg.hdforeman.mvp.contract.project.ConstructMaterialContract;
import com.dgg.hdforeman.mvp.model.been.ProjectInfoResponse;
import com.dgg.hdforeman.mvp.presenter.project.ConstructMeterialPresenter;
import com.dgg.hdforeman.mvp.presenter.project.TypeItem1;
import com.dgg.hdforeman.mvp.presenter.project.TypeItem2;
import com.dgg.hdforeman.mvp.presenter.project.TypeItem3;
import com.dgg.hdforeman.mvp.presenter.project.TypeItem4;
import com.dgg.hdforeman.mvp.ui.project.adapter.BagPagerAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.BaseAdapter;
import com.dgg.hdforeman.mvp.ui.project.adapter.ProjectPagerAdapter;
import com.dgg.hdforeman.mvp.ui.project.fragment.MaterialBagFragment;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.utils.PermissionUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment.FILL_LIST_ITEM;
import static com.dgg.hdforeman.mvp.ui.project.fragment.DuringConstructFragment.TAB_METHOD;


/**
 * Created by kelvin on 2016/11/3.
 */

public class ConstructMaterialActivity extends BacksActivity implements ConstructMaterialContract.View {

    @BindView(R.id.toolbar_right)
    TextView toolbarRight;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.bag_viewpager)
    ViewPager bagViewpager;
    public static final int ORDER_AUXILIARY = 1;//辅材
    public static final int ORDER_MAIN = 2;//主材
    public static final int ORDER_HOUSEHOLD = 3;//家电
    public static final int ORDER_FURNITURE = 4;//家具
    public static final int ORDER_SOFTOUTFIT = 5;//软装

    public static final int TYPE_AUXILIARY = 1;
    public static final int TYPE_MAIN = 2;

    private List<MaterialBagFragment> fragments;
    private List<String> mTabNameList;
    private BagPagerAdapter mBagPagerAdapter;
    private ProjectInfoResponse mData;

    private BaseFragment previousFragment;

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.construct_material_activity, null, false);
    }

    @Override
    protected void initData() {
        Bundle bd = getIntent().getExtras();
        mData = (ProjectInfoResponse) bd.getSerializable("material");
        mTabNameList = new ArrayList<String>();
        mTabNameList.add(getString(R.string.bag_auxiliary));
        mTabNameList.add(getString(R.string.bag_main));
        mTabNameList.add("家电");
        mTabNameList.add("家具");
        mTabNameList.add("软装");
        fragments = new ArrayList<MaterialBagFragment>();
        MaterialBagFragment fragment1 = MaterialBagFragment.newInstance(ORDER_AUXILIARY, TYPE_AUXILIARY, new TypeItem1(mHDApplication, this));
        MaterialBagFragment fragment2 = MaterialBagFragment.newInstance(ORDER_MAIN, TYPE_MAIN, new TypeItem2(mHDApplication, this));
        MaterialBagFragment fragment3 = MaterialBagFragment.newInstance(ORDER_HOUSEHOLD, TYPE_AUXILIARY, new TypeItem3(mHDApplication, this));
        MaterialBagFragment fragment4 = MaterialBagFragment.newInstance(ORDER_FURNITURE, TYPE_MAIN, new TypeItem4(mHDApplication, this));
        MaterialBagFragment fragment5 = MaterialBagFragment.newInstance(ORDER_SOFTOUTFIT, TYPE_MAIN, new TypeItem4(mHDApplication, this));
        fragment1.setArguments(bd);
        fragment2.setArguments(bd);
        fragment3.setArguments(bd);
        fragment4.setArguments(bd);
        fragment5.setArguments(bd);
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);

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
        mBagPagerAdapter = new BagPagerAdapter(getSupportFragmentManager(), fragments, mTabNameList);
        bagViewpager.setOffscreenPageLimit(4);
        bagViewpager.setAdapter(mBagPagerAdapter);
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout() {
        for (int i = 0; i < mTabNameList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        tabLayout.setupWithViewPager(bagViewpager);
    }


    /**
     *  添加viewPager, TabLayout监听事件
     */
    private void initListener() {
        if (bagViewpager != null)
            bagViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        if (tabLayout != null)
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    bagViewpager .setCurrentItem(tab.getPosition());//选择对应的页面
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
    protected void setupActivityComponent(AppComponent appComponent) {

    }


    @Override
    public void setAdapter(BaseAdapter adapter) {

    }

    @Override
    public void launchActivity(Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @OnClick(R.id.toolbar_right)
    public void onClick() {
        callPhone();
    }

    /**
     * 给业主打电话
     */
    public void callPhone() {
        PermissionUtil.callPhone(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                if (ActivityCompat.checkSelfPermission(ConstructMaterialActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + mData.getPm_cuscontactno())));
            }
        }, RxPermissions.getInstance(this), this, mHDApplication.getAppComponent().rxErrorHandler());
    }
}
