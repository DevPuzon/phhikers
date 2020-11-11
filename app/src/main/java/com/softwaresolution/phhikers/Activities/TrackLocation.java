package com.softwaresolution.phhikers.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Activities.Auth.AuthLogin;
import com.softwaresolution.phhikers.Pojo.MountainList;
import com.softwaresolution.phhikers.Pojo.SaveHikePojo;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Services.GeoWebViewClient;

public class TrackLocation extends AppCompatActivity {
    String TAG = "TrackLocation";
    WebView webView;
    RelativeLayout relative;
    ImageView img_feet;
    String url_tracmyloc="https://www.google.com/maps/dir//16.5976158,\n" +
            "120.8990976/@14.5863374,121.0391959",loc_latlang="",
            url_track="https://www.google.com/maps/dir/16.2911167,120.6332874/Mount+Ulap,+Tuba,+Benguet/@16.2905278,120.6313748,16.25z/data=!4m8!4m7!1m0!1m5!1m1!1s0x339109782260604b:0x77b32d74b730ec29!2m2!1d120.6309664!2d16.2900455";
    String mountain;
    TextView txt_timehike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String title = getIntent().getStringExtra("title");
        mountain = getIntent().getStringExtra("mountain");
        url_track = getIntent().getStringExtra("url_tracker");
        Log.d(TAG,url_track);
        getSupportActionBar().setTitle(title);
        init();
        txt_timehike = findViewById(R.id.txt_timehike);
        findViewById(R.id.v_timehike).setVisibility(View.GONE);
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
            handler.postDelayed(runnable, 1000);
            findViewById(R.id.v_timehike).setVisibility(View.VISIBLE);
        }else{
//            Not Track my location
            webView.loadUrl(url_track);
            relative.setBackgroundColor(Color.parseColor("#1A237E"));
            img_feet.setImageResource(R.drawable.feet);
            handler.removeCallbacks(runnable);
            timecount = 0;
            findViewById(R.id.v_timehike).setVisibility(View.GONE);
            this.onSavePrompt();
        }
        is_feet = !is_feet;
    }

    int timecount = 0;
    String hikeTime = "";
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Do the task...
            timecount++;
            hikeTime = sectoHms();
            txt_timehike.setText(sectoHms());
            handler.postDelayed(this, 1000);
        }
    };

    private String sectoHms(){
        int s = timecount;

        int sec = s % 60;
        int min = (s / 60)%60;
        int hours = (s/60)/60;
        String minS ="";
        String secS ="";
        String hrS ="";
        secS = String.valueOf(sec) + "secs. ";

        if(hours == 1){
            hrS = String.valueOf(hours) +"hr.";

        } if(hours > 1){
            hrS = String.valueOf(hours) + "hrs. ";
            System.out.println("hours  + minS +  secS" );

        } if(min == 1){
            minS = String.valueOf(min) + "min. ";

        } if(min > 1){
            minS = String.valueOf(min) + "mins. ";

        }
        return  hrS  + minS +  secS;
    }
    private void onSavePrompt() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        this.openSaveHike();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
            private void openSaveHike() {
                Intent intent = new Intent(TrackLocation.this,SaveHike.class)
                        .putExtra("mountain",mountain)
                        .putExtra("urlLoc",url_track)
                        .putExtra("hikeTime",hikeTime)
                        .putExtra("urlLocStarted",url_tracmyloc);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to save your hike?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
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
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.setDrawingCacheEnabled(true);
        webView.setWebViewClient(new GeoWebViewClient(this));
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
            loc_latlang = as[5];
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
    public  void onShowCompass(View v){
      startActivity(new Intent(this,Compass.class));
    }
    public  void onShowWeather(View v){
        startActivity(new Intent(this,Weather.class)
        .putExtra("loc_latlng",loc_latlang));
    }
}