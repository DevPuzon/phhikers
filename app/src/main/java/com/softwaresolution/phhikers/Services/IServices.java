package com.softwaresolution.phhikers.Services;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IServices {

    @GET("882acb84")
    Call<Object> getListMountains();
    public String WeatherApi = "922e5e37e00840d186a92529201011";
    @GET("forecast.json")
    Call<Object> getListWeather(@Query("key")String api,
                                @Query("q")String q,
                                @Query("days")String days);
}
