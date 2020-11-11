package com.softwaresolution.phhikers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Adapter.HistoryAdapter;
import com.softwaresolution.phhikers.Pojo.SaveHikePojo;
import com.softwaresolution.phhikers.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class History extends AppCompatActivity {
    String TAG = "History";
    RecyclerView recyclerView;
    ArrayList<SaveHikePojo>list = new ArrayList<>();
    private SharedPreferences saveData;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("History");


        saveData = getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();


        Type typeMyType = new TypeToken<ArrayList<SaveHikePojo>>(){}.getType();
        String jsonResponse = saveData.getString("savehike","");
        if (!TextUtils.isEmpty(jsonResponse)){
            list =  new Gson().fromJson(jsonResponse, typeMyType);

            recyclerView = findViewById(R.id.recycler);
            HistoryAdapter adapter = new HistoryAdapter(this,list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
        }else{
            FancyToast.makeText(History.this,"No history yet",FancyToast.LENGTH_LONG
                    ,FancyToast.WARNING,false).show();
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}