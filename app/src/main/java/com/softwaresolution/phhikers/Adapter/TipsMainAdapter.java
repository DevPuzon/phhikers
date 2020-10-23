package com.softwaresolution.phhikers.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.softwaresolution.phhikers.Fragments.EmergencyFrag;
import com.softwaresolution.phhikers.Fragments.GuideTipsFrag;

public class TipsMainAdapter  extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public TipsMainAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GuideTipsFrag guideTipsFrag = new GuideTipsFrag();
                return guideTipsFrag;
            case 1:
                EmergencyFrag emergencyFrag = new EmergencyFrag();
                return emergencyFrag;
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