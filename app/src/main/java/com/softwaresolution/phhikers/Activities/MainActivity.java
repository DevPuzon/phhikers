package com.softwaresolution.phhikers.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.softwaresolution.phhikers.Activities.Auth.UpdateAccount;
import com.softwaresolution.phhikers.Fragments.MountainMain;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.ALLDATAS;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LocationListener {

    private String TAG = "MainActivity";

    private ImageView img_profile;
    private TextView txt_email, txt_name;
    private NavigationView navigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MountainMain()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        getSupportActionBar().setTitle("Mountain list");

        ALLDATAS.init_data(MainActivity.this);
        init();
    }

    protected LocationManager locationManager;
    protected LocationListener locationListener;

    private void init() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                3000, 0, MainActivity.this);
    }


    private SharedPreferences saveData;
    private SharedPreferences.Editor editor;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MountainMain()).commit();
            getSupportActionBar().setTitle("Mountain list");
        }
        if (id == R.id.nav_tips){
            startActivity(new Intent(MainActivity.this,TipsMain.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        if (id == R.id.nav_checklist){
            startActivity(new Intent(MainActivity.this,CheckListAct.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        if (id == R.id.nav_acc){
            startActivity(new Intent(MainActivity.this, UpdateAccount.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        if (id == R.id.nav_logout){
            startActivity(new Intent(MainActivity.this,SplashScreen.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            saveData = getSharedPreferences("saveData", MODE_PRIVATE);
            editor = saveData.edit();
            editor.putString("user",null);
            editor.apply();
            editor.commit();
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onLocationChanged(Location location) {
        String plat = String.format("%.6f",location.getLatitude());
        String plang = String.format("%.6f",location.getLongitude());
        Log.d(TAG,"Latitude:" + plat + ", Longitude:" + plang);
        saveData = getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();
        editor.putString("latlang", plat+","+plang);
        editor.apply();
        editor.commit();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}