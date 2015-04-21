package com.example.user.myfirstapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragment for Fragment1
                return new FragmentONE();
            case 1:
                //Fragment for Fragment2
                return new FragmentTWO();
            case 2:
                //Fragment for Fragment3
                return new FragmentTHREE();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 3; //No of Tabs
    }
}
