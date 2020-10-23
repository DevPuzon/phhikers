package com.softwaresolution.phhikers.Fragments;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Activities.Auth.AuthLogin;
import com.softwaresolution.phhikers.Adapter.MountainListAdapter;
import com.softwaresolution.phhikers.Pojo.MountainList;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Services.IServices;
import com.softwaresolution.phhikers.Services.ServiceMain;
import com.softwaresolution.phhikers.Utils.ALLDATAS;
import com.softwaresolution.phhikers.Utils.Loading;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MountainMain extends Fragment {
    String TAG = "MountainMain";

    private RecyclerView recyclerView;
    private MountainListAdapter adapter;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_mountain_main, container, false);
        init();
        return  v;
    }

    ArrayList<MountainList> mountains = new ArrayList<>();
    private void init() {
        recyclerView = v.findViewById(R.id.recycler);
        mountains = ALLDATAS.appDatas.getMountainList();
        adapter = new MountainListAdapter(getContext(),mountains);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}