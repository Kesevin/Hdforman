package com.dgg.hdforeman.mvp.ui.project.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.dgg.hdforeman.mvp.ui.project.fragment.MaterialBagFragment;
import com.jess.arms.base.BaseFragment;

import java.util.List;


/**
 * Created by twick on 2016/10/12.
 */

public class BagPagerAdapter extends FragmentPagerAdapter {
    private List<MaterialBagFragment> mFragments;
    private List<String> mStrTitle;

    public BagPagerAdapter(FragmentManager fm, List<MaterialBagFragment> mFragments, List<String> mStrTitle) {
        super(fm);
        this.mFragments = mFragments;
        this.mStrTitle = mStrTitle;
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
        if (mStrTitle != null && mStrTitle.size() != 0) {
            return mStrTitle.get(position);
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
