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
import com.softwaresolution.phhikers.Adapter.GuideTipsAdapter;
import com.softwaresolution.phhikers.Pojo.GuidesTip;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.ALLDATAS;

import java.util.ArrayList;

public class GuideTipsFrag extends Fragment {
    String TAG = "GuideTipsFrag";
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_guide_tips, container, false);
        init();
        return v;
    }
    private RecyclerView recyclerView;
    private GuideTipsAdapter adapter;
    ArrayList<GuidesTip> list = new ArrayList<>();
    private void init() {
        Log.d(TAG,"iinit");
        recyclerView = v.findViewById(R.id.recycler);
        list = ALLDATAS.appDatas.getGuidesTips();
        Log.d(TAG, new Gson().toJson(list));
        adapter = new GuideTipsAdapter(getContext(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}