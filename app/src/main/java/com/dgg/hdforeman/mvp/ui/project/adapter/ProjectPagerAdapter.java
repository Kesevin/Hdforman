package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import com.jess.arms.base.BaseFragment;

import java.util.List;


/**
 * Created by twick on 2016/10/12.
 */

public class ProjectPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments;
    private List<String> mStrList;

    public ProjectPagerAdapter(FragmentManager fm, List<BaseFragment> mFragments, List<String> mStrList) {
        super(fm);
        this.mFragments = mFragments;
        this.mStrList = mStrList;
    }


    public void clear() {
        for (BaseFragment fragment : mFragments) {
            if (fragment != null && fragment.isAdded()) {
                fragment.onDestroy();
            }
        }
        mFragments.clear();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mStrList != null && mStrList.size() != 0) {
            return mStrList.get(position);
        }
        return super.getPageTitle(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
     /*   View view = mFragments.get(position).getView();
        if (view != null) {
            container.removeView(view);
        }*/
        super.destroyItem(container, position, object);
    }
}
