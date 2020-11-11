package com.softwaresolution.phhikers.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softwaresolution.phhikers.Pojo.Weather.Forecast;
import com.softwaresolution.phhikers.Pojo.Weather.Hour;
import com.softwaresolution.phhikers.Pojo.Weather.WeatherPojo;
import com.softwaresolution.phhikers.R;

import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
public class WeatherAdapter extends BaseAdapter {
    Context context;
    List<Hour> hourList = new ArrayList<>();
    LayoutInflater inflter;
    public WeatherAdapter(Context applicationContext, WeatherPojo weatherPojo) {
        this.context = applicationContext;
        hourList = weatherPojo.getForecast().getForecastday().get(0).getHour();
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return hourList.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView txt_c,txt_time,txt_f;
        ImageView img;
        view = inflter.inflate(R.layout.item_weather, null);
        txt_c = view.findViewById(R.id.txt_c);
        txt_time = view.findViewById(R.id.txt_time);
        txt_f = view.findViewById(R.id.txt_f);
        img = view.findViewById(R.id.img);

        Hour hour = hourList.get(i);
        txt_c.setText(hour.getTempC()+"°");
        txt_f.setText(hour.getTempF()+"°");
        try{
            SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
            Date date = displayFormat.parse(hour.getTime().split(" ",2)[1]);

            txt_time.setText(parseFormat.format(date));
        }catch(ParseException pe){
            pe.printStackTrace();
        }
        Glide.with(context)
                .load("https:"+hour.getCondition().getIcon())
                .centerCrop()
                .into(img);
        return view;
    }
}