package com.softwaresolution.phhikers.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

public class MountainMain extends AppCompatActivity {
    String TAG = "MountainMain";

    private RecyclerView recyclerView;
    private MountainListAdapter adapter;
    private EditText txt_search;
    ArrayList<MountainList> main_mountains = new ArrayList<>();
//    View v;
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        v =  inflater.inflate(R.layout.activity_mountain_main, container, false);
//        init();
//        return  v;
//    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain_main);
        getSupportActionBar().setTitle("Mountains");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        txt_search = findViewById(R.id.txt_search);
        main_mountains = ALLDATAS.appDatas.getMountainList();

        txt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String txt = txt_search.getText().toString();
                mountains = new ArrayList<>();
                if (!txt.equals("")){
                    Log.d(TAG,txt);
                    Log.d(TAG," main_mountains "+new Gson().toJson(main_mountains));
                     for (int i= 0 ; i < main_mountains.size() ; i ++){
                         String name = main_mountains.get(i).getName().toLowerCase();
                         Log.d(TAG,name);
                         if (name.contains(txt)){
                             mountains.add(main_mountains.get(i));
                         }
                     }
                }else{
                     mountains = main_mountains;
                }
                Log.d(TAG,new Gson().toJson(mountains));
                adapter = new MountainListAdapter(MountainMain.this,mountains);
                recyclerView.setLayoutManager(new LinearLayoutManager(MountainMain.this));
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        init();
    }

    ArrayList<MountainList> mountains = new ArrayList<>();
    private void init() {
        recyclerView = findViewById(R.id.recycler);
        mountains =(ArrayList<MountainList>) main_mountains.clone();
        adapter = new MountainListAdapter(this,mountains);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}