package com.softwaresolution.phhikers.Services;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IServices {

    @GET("882acb84")
    Call<Object> getListMountains();
}
