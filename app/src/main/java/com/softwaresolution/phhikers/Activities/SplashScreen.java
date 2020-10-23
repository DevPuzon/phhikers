package com.softwaresolution.phhikers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.softwaresolution.phhikers.Activities.Auth.AuthLogin;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.ALLDATAS;
import com.softwaresolution.phhikers.Utils.Permission;


public class SplashScreen extends AppCompatActivity {

    private SharedPreferences saveData;
    private SharedPreferences.Editor editor;
    private ImageView img_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        initViews();
    }

    private void initViews() {
        saveData = getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();
        img_logo = findViewById(R.id.img_logo);
        Animation blinkanim = AnimationUtils.loadAnimation(this, R.anim.blink_anim);
        img_logo.startAnimation(blinkanim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(saveData.getString("user",null))){
                    Intent intent_next=new Intent(getApplicationContext(), AuthLogin.class);
                    startActivity(intent_next);
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }else {
                    Intent intent_next=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent_next);
//                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
            }
        }, 3000);
    }
}