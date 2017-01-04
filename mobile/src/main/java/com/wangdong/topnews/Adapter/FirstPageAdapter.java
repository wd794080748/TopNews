package com.wangdong.topnews.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by wd794 on 2017/1/4 0004.
 */

public class FirstPageAdapter extends FragmentPagerAdapter {
    private String[] titleName;
    private ArrayList<Fragment> fragmentArrayList;
    public FirstPageAdapter(FragmentManager fm,String[] titleName,ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.titleName=titleName;
        this.fragmentArrayList=fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titleName[position%titleName.length];
    }
}
