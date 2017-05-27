package com.dgg.hdforeman.mvp.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Rex on 2016/10/20.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;
    public TabAdapter(FragmentManager fm,List<Fragment> mFragments) {
        super(fm);
        this.mFragments=mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return null;
//    }
}
