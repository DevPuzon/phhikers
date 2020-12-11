package com.softwaresolution.phhikers.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.softwaresolution.phhikers.Activities.CheckListAct;
import com.softwaresolution.phhikers.Activities.History;
import com.softwaresolution.phhikers.Activities.MountainMain;
import com.softwaresolution.phhikers.Activities.TipsMain;
import com.softwaresolution.phhikers.R;

public class FragmentMainActivity extends Fragment {
    View v;
    View nav_mountain,nav_hikes,nav_tips,nav_checks;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_main_activity, container, false);
        nav_mountain = v.findViewById(R.id.nav_mountain);
        nav_hikes = v.findViewById(R.id.nav_hikes);
        nav_tips = v.findViewById(R.id.nav_tips);
        nav_checks = v.findViewById(R.id.nav_checks);
        
        nav_mountain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { 
                startActivity(new Intent(getContext(), MountainMain.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        nav_hikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), History.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        nav_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TipsMain.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        nav_checks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CheckListAct.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        return  v;
    }
}