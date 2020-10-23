package com.softwaresolution.phhikers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Activities.Auth.AuthLogin;
import com.softwaresolution.phhikers.R;

public class TrackLocation extends AppCompatActivity {
    String TAG = "TrackLocation";
    WebView webView;
    RelativeLayout relative;
    ImageView img_feet;
    String url_tracmyloc="https://www.google.com/maps/dir//16.5976158,\n" +
            "120.8990976/@14.5863374,121.0391959",

            url_track="https://www.google.com/maps/dir/16.2911167,120.6332874/Mount+Ulap,+Tuba,+Benguet/@16.2905278,120.6313748,16.25z/data=!4m8!4m7!1m0!1m5!1m1!1s0x339109782260604b:0x77b32d74b730ec29!2m2!1d120.6309664!2d16.2900455";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String title = getIntent().getStringExtra("title");
        url_track = getIntent().getStringExtra("url_tracker");
        getSupportActionBar().setTitle(title);
        init();
        relative = findViewById(R.id.relative);
        img_feet = findViewById(R.id.img_feet);
        relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFeet();
            }
        });
    }

    boolean is_feet = true;
    private void onClickFeet() {
        if (is_feet){
//            Track my location
            webView.loadUrl(url_tracmyloc);
            Log.d(TAG,url_tracmyloc);
            relative.setBackgroundColor(Color.parseColor("#ffffff"));
            img_feet.setImageResource(R.drawable.nofeet);
        }else{
//            Not Track my location
            webView.loadUrl(url_track);
            relative.setBackgroundColor(Color.parseColor("#1A237E"));
            img_feet.setImageResource(R.drawable.feet);
        }
        is_feet = !is_feet;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private SharedPreferences saveData;
    private SharedPreferences.Editor editor;
    private void init() {
        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setDrawingCacheEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url_track);
        saveData = getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();
        if (!TextUtils.isEmpty(saveData.getString("latlang",null))){
            String latlang  =saveData.getString("latlang",null);
            getmyloc_url(latlang);
        }else {
            FancyToast.makeText(this,"Can't get your current location",
                    FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
            onBackPressed();
        }
    }

    private void getmyloc_url(String latlng){
//        String a = "https://www.google.com/maps/dir/16.5894318,120.8968223/Mount+Pulag,+Kabayan,+Benguet/@16.5851432,120.8981936";
//        //9
//        String[] as = a.split("/");
//        as[5] = "14.5863374,121.0391959";
//        String url = "";
//        for (String aa : as) {
//            url = url +"/"+ aa;
//        }
//        url =url.substring(1,url.length();
//        System.out.println(url.substring(1,url.length()));
        try {
            Log.d(TAG,latlng);
            String a = url_track;
            Log.d(TAG,url_track);
            //9
            String[] as = a.split("/");
            as[5] = latlng;
            as[8] = "";
            String url = "";
            for (String aa : as) {
                url = url +"/"+ aa;
                Log.d(TAG,url);
            }
            url =url.substring(1,url.length());
            url_tracmyloc = url;
            Log.d(TAG,url);
        }catch (Exception ex){
            Log.e(TAG,ex.getLocalizedMessage());
        }
    }

}