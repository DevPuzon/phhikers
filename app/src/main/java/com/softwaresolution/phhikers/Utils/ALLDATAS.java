package com.softwaresolution.phhikers.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Pojo.AppDatas;
import com.softwaresolution.phhikers.Pojo.MountainList;
import com.softwaresolution.phhikers.Services.IServices;
import com.softwaresolution.phhikers.Services.ServiceMain;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ALLDATAS {
    static String TAG ="ALLDATAS";
    private static SharedPreferences saveData;
    private static SharedPreferences.Editor editor;
    public static AppDatas appDatas ;
    public static void init_data(final Context context){
        saveData = context.getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();
        if (TextUtils.isEmpty(saveData.getString("ALLDATAS",null))){
            onGetData(context);
        }else{
            appDatas = new Gson().fromJson(saveData.getString("ALLDATAS",null),AppDatas.class);
        }
    }

    private static void onGetData(final Context context) {
        final IServices iServices ;
        final Loading loading = new Loading(context);
        loading.loadDialog.show();
        iServices = ServiceMain.getMockyApiClient().create(IServices.class);
        Call<Object> call = iServices.getListMountains();
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                loading.loadDialog.dismiss();
                if (response.isSuccessful()){
                    Log.d(TAG, new Gson().toJson(response.body()));
                    saveData = context.getSharedPreferences("saveData", MODE_PRIVATE);
                    editor = saveData.edit();
                    editor.putString("ALLDATAS", new Gson().toJson(response.body()));
                    editor.apply();
                    editor.commit();
                    Log.d(TAG,saveData.getString("ALLDATAS",null));
                    appDatas = new Gson().fromJson(saveData.getString("ALLDATAS",null),AppDatas.class);
                }
            }

            @Override
            public void onFailure(Call<Object> ArrayList, Throwable t) {
                loading.loadDialog.dismiss();
                Log.d(TAG,"error "+ t.getLocalizedMessage());
                FancyToast.makeText(context,
                        "Error something went wrong",FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,false).show();

            }
        });
    }
}
