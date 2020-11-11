package com.softwaresolution.phhikers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Activities.Auth.AuthLogin;
import com.softwaresolution.phhikers.Pojo.MountainList;
import com.softwaresolution.phhikers.Pojo.SaveHikePojo;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Services.GeoWebViewClient;
import com.softwaresolution.phhikers.Utils.Loading;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class SaveHike extends AppCompatActivity {

    MountainList mountain;
    String urlLoc,hikeTime,urlLocStarted; 
    TextView txt_timehike,txt_exp,txt_name,txt_desc,txt_date;
    ImageView img; 
    WebView webMonLoc,webStrLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_hike);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Save hike");
        urlLoc = getIntent().getStringExtra("urlLoc");
        mountain = new Gson().fromJson(getIntent().getStringExtra("mountain"),MountainList.class); 
        hikeTime = getIntent().getStringExtra("hikeTime");
        urlLocStarted = getIntent().getStringExtra("urlLocStarted");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh:mm:ss a");
        String formattedDate = dateFormat.format(new Date()).toString();
        txt_exp = findViewById(R.id.txt_exp);
        txt_timehike = findViewById(R.id.txt_timehike);
        txt_date = findViewById(R.id.txt_date);
        txt_date.setText(formattedDate);
        txt_timehike.setText(hikeTime);
        txt_name = findViewById(R.id.txt_name);
        txt_desc = findViewById(R.id.txt_desc);
        img = findViewById(R.id.img);

        webMonLoc = findViewById(R.id.webMonLoc);
        webStrLoc = findViewById(R.id.webStrLoc);
        this.initHistory();
        this.initMountain();
        this.initWeb();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    private void initHistory(){
//          .putExtra("mountain",new Gson().toJson(mountain))
//                .putExtra("urlLoc",saveHikePojo.getUrlLoc())
//                .putExtra("hikeTime",saveHikePojo.getHikeTime())
//                .putExtra("urlLocStarted",saveHikePojo.getUrlLocStarted())
//                .putExtra("exp",saveHikePojo.getExp())
//                .putExtra("date",saveHikePojo.getDate())
//                .putExtra("history","History "+mountain.getName());

        String historyTitle = getIntent().getStringExtra("history");
        if (!TextUtils.isEmpty(historyTitle)){
            getSupportActionBar().setTitle(historyTitle);
            findViewById(R.id.v_btm).setVisibility(View.GONE);
            txt_exp.setEnabled(false);
            txt_date.setText(getIntent().getStringExtra("date"));
            txt_exp.setText(getIntent().getStringExtra("exp"));
        }
    }

    private void initWeb() {

        webMonLoc = (WebView) findViewById(R.id.webMonLoc);
        webMonLoc.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webMonLoc.getSettings().setBuiltInZoomControls(true);
        webMonLoc.getSettings().setJavaScriptEnabled(true);
        webMonLoc.getSettings().setGeolocationEnabled(true);
        webMonLoc.setDrawingCacheEnabled(true);
//        webMonLoc.setWebViewClient(new WebViewClient());
        webMonLoc.setWebViewClient(new GeoWebViewClient(this ));
        webMonLoc.loadUrl(urlLoc);


        webStrLoc = (WebView) findViewById(R.id.webStrLoc);
        webStrLoc.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webStrLoc.getSettings().setBuiltInZoomControls(true);
        webStrLoc.getSettings().setJavaScriptEnabled(true);
        webStrLoc.getSettings().setGeolocationEnabled(true);
        webStrLoc.setDrawingCacheEnabled(true);
//        webStrLoc.setWebViewClient(new WebViewClient());
        webStrLoc.setWebViewClient(new GeoWebViewClient(this));
        webStrLoc.loadUrl(urlLocStarted);
    }

    private void initMountain() {
        txt_name.setText(mountain.getName());
        String desc = "Location : "+mountain.getLocation()+"\nElevation : "+mountain.getElevation()+
                "\nDifficulty : "+mountain.getDifficulty();
        txt_desc.setText(desc);
        Glide.with(this)
                .load("https://lh3.googleusercontent.com/proxy/L18nSAVupOl5MqHRJz4tshJhTGrG4Ye1qdQ8gVwN6wDX-s6GkT6oMs6t66R9VjZapIINmFEdkyWCv9HM6l_betLGDEGMTz0HxYtxchzqDpMh8FVp-gzOj1wgZVvrhJhLrcXHCEBh")
                .centerCrop()
                .into(img);
        Glide.with(this)
                .load(mountain.getImageSrc())
                .centerCrop()
                .into(img);
    }

    private SharedPreferences saveData;
    private SharedPreferences.Editor editor;
    public void onSaveHike(View v){
        saveData = getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();
        ArrayList<SaveHikePojo> savehikes = new ArrayList<>();

        Type typeMyType = new TypeToken<ArrayList<SaveHikePojo>>(){}.getType();
        String jsonResponse = saveData.getString("savehike","");
        if (!TextUtils.isEmpty(jsonResponse)){
            savehikes =  new Gson().fromJson(jsonResponse, typeMyType);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh:mm:ss a");
        String formattedDate = dateFormat.format(new Date()).toString();
        SaveHikePojo saveHikePojo = new SaveHikePojo(mountain,
                txt_exp.getText().toString(),
                formattedDate,
                hikeTime,
                urlLoc,
                urlLocStarted);
        savehikes.add(saveHikePojo);
        editor.putString("savehike", new Gson().toJson(savehikes));
        editor.apply();
        editor.commit();
        final Loading loading = new Loading(this);
        loading.loadDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FancyToast.makeText(SaveHike.this,"Saved successfully",FancyToast.LENGTH_LONG
                        ,FancyToast.SUCCESS,false).show();
                loading.loadDialog.dismiss();
                onBackPressed();
            }
        }, 2500);
    }
}