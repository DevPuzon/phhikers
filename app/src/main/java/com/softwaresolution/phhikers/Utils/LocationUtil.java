package com.softwaresolution.phhikers.Utils;

public class LocationUtil {

//    private void setLocationTxt(String location){
//        if (!TextUtils.isEmpty(txt_location.getText())){
//            return;
//        }
//        if (!TextUtils.isEmpty(location)){
//            loading.loadDialog.setTitle("Loading");
//            loading.loadDialog.show();
//            String lat = location.split(",")[0];
//            String lon = location.split(",")[1];
//            Log.d(TAG,lat+" = "+lon);
//            iServices = ServiceMain.getTagsPlaces().create(IServices.class);
//            Call<Object> call = iServices.getMapRes("json",lat,lon);
//            call.enqueue(new Callback<Object>() {
//                @Override
//                public void onResponse(Call<Object> call, Response<Object> response) {
//                    loading.loadDialog.dismiss();
//                    Log.d(TAG,"response.body() "+new Gson().toJson(response));
//                    try {
//                        JSONObject jsonObj = new JSONObject(new Gson().toJson(response.body()));
//                        String display_name = jsonObj.getString("display_name");
//                        txt_location.setText(display_name);
//                        Log.d(TAG,"display "+display_name);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Object> call, Throwable t) {
//                    Log.d(TAG,t.getLocalizedMessage());
//                    loading.loadDialog.dismiss();
//                }
//            });
//        }
//    }
//
//    private GoogleApiClient mGoogleApiClient;
//
//    private LocationRequest mLocationRequest;
//    private boolean checkPlayServices() {
//        int resultCode = GooglePlayServicesUtil
//                .isGooglePlayServicesAvailable(this);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
//                Toast.makeText(getApplicationContext(),
//                        "This device is supported. Please download google play services", Toast.LENGTH_LONG)
//                        .show();
//            } else {
//                Toast.makeText(getApplicationContext(),
//                        "This device is not supported.", Toast.LENGTH_LONG)
//                        .show();
//                finish();
//            }
//            return false;
//        }
//        return true;
//    }
//
//    private SharedPreferences locationSp;
//    private SharedPreferences.Editor locationEdit;
//    private String mylocation = "";
//    public void startFusedLocation() {
//        locationSp = getSharedPreferences("retailgate", MODE_PRIVATE);
//        locationEdit = locationSp.edit();
//        mylocation = locationSp.getString("mylocation", "");
//        if (mGoogleApiClient == null) {
//            mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
//                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                        @Override
//                        public void onConnectionSuspended(int cause) {
//                        }
//
//                        @Override
//                        public void onConnected(Bundle connectionHint) {
//
//                        }
//                    }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
//
//                        @Override
//                        public void onConnectionFailed(ConnectionResult result) {
//
//                        }
//                    }).build();
//            mGoogleApiClient.connect();
//        } else {
//            mGoogleApiClient.connect();
//        }
//        if(TextUtils.isEmpty(mylocation)){
//            loading = new Loading(this);
//            loading.loadDialog.setTitle("Wait to locate your location.");
//            loading.loadDialog.show();
//            setLocationTxt(mylocation);
//        }
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (TextUtils.isEmpty(mylocation)){
//                    findViewById(R.id.txt_location).setVisibility(View.GONE);
//                    loading.loadDialog.dismiss();
//                    loading.loadDialog.setTitle("Loading");
//                }
//            }
//        }, 35000);
//
//    }
//    public void stopFusedLocation() {
//        if (mGoogleApiClient != null) {
//            mGoogleApiClient.disconnect();
//        }
//    }
//
//    public void registerRequestUpdate(final LocationListener listener) {
//        mLocationRequest = LocationRequest.create();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setInterval(1000); // every second
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    LocationServices.FusedLocationApi.
//                            requestLocationUpdates(mGoogleApiClient, mLocationRequest,
//                                    listener);
//                } catch (SecurityException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    if (!isGoogleApiClientConnected()) {
//                        mGoogleApiClient.connect();
//                    }
//                    registerRequestUpdate(listener);
//                }
//            }
//        }, 1000);
//    }
//
//    public boolean isGoogleApiClientConnected() {
//        return mGoogleApiClient != null && mGoogleApiClient.isConnected();
//    }
//    @Override
//    protected void onStop() {
//        stopFusedLocation();
//        super.onStop();
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        mylocation = String.valueOf(location.getLatitude()) + "," +
//                String.valueOf(location.getLongitude());
//        Log.d(TAG, "mylocation getlcoation " + mylocation);
//        locationEdit.putString("mylocation", mylocation);
//        locationEdit.apply();
//        locationEdit.commit();
//        loading.loadDialog.dismiss();
//        loading.loadDialog.setTitle("Loading");
//        setLocationTxt(mylocation);
//    }
}
