package com.softwaresolution.phhikers.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.softwaresolution.phhikers.Adapter.CheckListAdapter;
import com.softwaresolution.phhikers.Adapter.EmerTipsAdapter;
import com.softwaresolution.phhikers.Pojo.Checklist;
import com.softwaresolution.phhikers.Pojo.CusCheckList;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.ALLDATAS;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ChPrimary extends Fragment {
    String TAG = "ChPrimary";
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=  inflater.inflate(R.layout.fragment_ch_primary, container, false);
        init();
        return v;
    }

    private SharedPreferences saveData;
    private SharedPreferences.Editor editor;
    private RecyclerView recyclerView;
    private CheckListAdapter adapter;
    ArrayList<CusCheckList> list = new ArrayList<>();
    private void init() {
        saveData = getContext().getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();
        recyclerView = v.findViewById(R.id.recycler);

        if (TextUtils.isEmpty(saveData.getString("ch_primary",null))){
            ArrayList<Checklist> nonlist = ALLDATAS.appDatas.getChecklist();
            for (int i = 0 ; i < nonlist.size() ;i++){
                list.add(new CusCheckList(nonlist.get(i).getName(),false));
            }
            saveChecks();
        }else{
            Type typeMyType = new TypeToken<ArrayList<CusCheckList>>(){}.getType();
            list =  new Gson().fromJson(saveData.getString("ch_primary",null), typeMyType);
        }

        Log.d(TAG, new Gson().toJson(list));
        adapter = new CheckListAdapter(getContext(),list,"ch_primary");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void saveChecks(){
        saveData = getContext().getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();
        editor.putString("ch_primary", new Gson().toJson(list));
        editor.apply();
        editor.commit();
    }
}