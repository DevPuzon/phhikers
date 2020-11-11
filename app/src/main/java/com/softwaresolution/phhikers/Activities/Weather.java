package com.softwaresolution.phhikers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Adapter.WeatherAdapter;
import com.softwaresolution.phhikers.Pojo.AppDatas;
import com.softwaresolution.phhikers.Pojo.Weather.Condition;
import com.softwaresolution.phhikers.Pojo.Weather.Current;
import com.softwaresolution.phhikers.Pojo.Weather.WeatherPojo;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Services.IServices;
import com.softwaresolution.phhikers.Services.ServiceMain;
import com.softwaresolution.phhikers.Utils.Loading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Weather extends AppCompatActivity {
    String TAG = "Weather";
    String loc_latlng;
    GridView gridView;
    TextView txt_c,txt_f,txt_humidity,txt_wkph,txt_wmph,txt_wdir;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Weather");
        loc_latlng = getIntent().getStringExtra("loc_latlng");
        Log.d(TAG,loc_latlng);
        onGetData();
        findViewById(R.id.card).setVisibility(View.GONE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private void onGetData() {
        final IServices iServices ;
        final Loading loading = new Loading(this);
        loading.loadDialog.show();
        iServices = ServiceMain.getWeatherClient().create(IServices.class);
        Call<Object> call = iServices.getListWeather(IServices.WeatherApi,loc_latlng,"1");
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                loading.loadDialog.dismiss();
                if (response.isSuccessful()){
                    String body = new Gson().toJson(response.body());
                    WeatherPojo weatherPojo =new Gson().fromJson(body,WeatherPojo.class);
                    Log.d(TAG, new Gson().toJson(weatherPojo));
                    initProp(weatherPojo);
                }
            }

            @Override
            public void onFailure(Call<Object> ArrayList, Throwable t) {
                loading.loadDialog.dismiss();
                Log.d(TAG,"error "+ t.getLocalizedMessage());
                FancyToast.makeText(Weather.this,
                        "Error something went wrong",FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,false).show();

            }
        });
    }

    public void initProp(WeatherPojo weatherPojo){
        Condition condition = weatherPojo.getCurrent().getCondition();
        Current current = weatherPojo.getCurrent();
        findViewById(R.id.card).setVisibility(View.VISIBLE);
        gridView = findViewById(R.id.gridView);
        WeatherAdapter adapter = new WeatherAdapter(this,weatherPojo);
        gridView.setAdapter(adapter);

        txt_c= findViewById(R.id.txt_c);
        txt_f= findViewById(R.id.txt_f);
        txt_humidity= findViewById(R.id.txt_humidity);
        txt_wkph= findViewById(R.id.txt_wkph);
        txt_wdir= findViewById(R.id.txt_wdir);
        txt_wmph= findViewById(R.id.txt_wmph);
        img = findViewById(R.id.img);
        Glide.with(this)
                .load("https:"+condition.getIcon())
                .centerCrop()
                .into(img);
        txt_c.setText(String.valueOf(current.getTempC())+"°");
        txt_f.setText(Html.fromHtml("<b>Fahrenheit : </b>"+ String.valueOf(current.getTempF())+"°"));
        txt_humidity.setText(Html.fromHtml("<b>Humidity : </b>"+ String.valueOf(current.getHumidity())));
        txt_wkph.setText(Html.fromHtml("<b>Wind kph : </b>"+ String.valueOf(current.getWindKph())));
        txt_wdir.setText(Html.fromHtml("<b>Wind dir. : </b>"+ String.valueOf(current.getWindDir())));
        txt_wmph.setText(Html.fromHtml("<b>Wind mph : </b>"+ String.valueOf(current.getWindMph())));
    }
}