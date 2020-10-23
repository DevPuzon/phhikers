package com.softwaresolution.phhikers.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.softwaresolution.phhikers.Fragments.ChLongHikes;
import com.softwaresolution.phhikers.Fragments.ChOvernight;
import com.softwaresolution.phhikers.Fragments.ChPrimary;
import com.softwaresolution.phhikers.Fragments.EmergencyFrag;
import com.softwaresolution.phhikers.Fragments.GuideTipsFrag;

public class CheckListPagerAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public CheckListPagerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ChPrimary chPrimary = new ChPrimary();
                return chPrimary;
            case 1:
                ChOvernight chOvernight = new ChOvernight();
                return chOvernight;
            case 2:
                ChLongHikes chLongHikes = new ChLongHikes();
                return chLongHikes;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}