package com.softwaresolution.phhikers.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.softwaresolution.phhikers.Adapter.EmerTipsAdapter;
import com.softwaresolution.phhikers.Adapter.GuideTipsAdapter;
import com.softwaresolution.phhikers.Pojo.EmergencyGuide;
import com.softwaresolution.phhikers.Pojo.GuidesTip;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.ALLDATAS;

import java.util.ArrayList;

public class EmergencyFrag extends Fragment {
    String TAG= "EmergencyFrag";
    View v ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_emergency, container, false);
         init();
         return v;
    }
    private RecyclerView recyclerView;
    private EmerTipsAdapter adapter;
    ArrayList<EmergencyGuide> list = new ArrayList<>();
    private void init() {
        recyclerView = v.findViewById(R.id.recycler);
        list = ALLDATAS.appDatas.getEmergencyGuides();
        Log.d(TAG, new Gson().toJson(list));
        adapter = new EmerTipsAdapter(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}