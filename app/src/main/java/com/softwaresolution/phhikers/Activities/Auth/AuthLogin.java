package com.softwaresolution.phhikers.Activities.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Activities.MainActivity;
import com.softwaresolution.phhikers.Activities.SplashScreen;
import com.softwaresolution.phhikers.Pojo.User;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.ALLDATAS;
import com.softwaresolution.phhikers.Utils.Loading;
import com.softwaresolution.phhikers.Utils.Permission;

public class AuthLogin extends AppCompatActivity {
    String TAG = "AuthLogin";
    TextView txt_email,txt_password;
    MaterialButton btn_enter;
    Loading loading;
    String[] PERMISSIONS = {
            Manifest.permission.LOCATION_HARDWARE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    int PERMISSION_ALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authetication);
        Permission permission = new Permission(this, this);
        if (!permission.hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        ALLDATAS.init_data(AuthLogin.this);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        btn_enter = findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });
        loading = new Loading(this);
    }
    public void onAdmin(View view){
        startActivity(new Intent(this,AdminLogin.class));
    }
    public void onRegister(View view){
        startActivity(new Intent(this,Register.class));
    }

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SharedPreferences saveData;
    private SharedPreferences.Editor editor;
    public void onLogin(){
        String email = txt_email.getText().toString();
        String password= txt_password.getText().toString();
        loading.loadDialog.show();
        if (TextUtils.isEmpty(email) ||TextUtils.isEmpty(password)  ){
            FancyToast.makeText(this,"Please fill all the data information.",
                    FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show();
            return;
        }
        loading.loadDialog.show();
        db.collection("PhHikersUser")
                .whereEqualTo("email",email)
                .whereEqualTo("password",password)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                loading.loadDialog.dismiss();
                if (task.isSuccessful()) {
                    User user = null;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        user = document.toObject(User.class);
                    }
                    if (user != null){
                        loading.loadDialog.dismiss();
                        FancyToast.makeText(AuthLogin.this,"Login successfully",
                                FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        saveData = getSharedPreferences("saveData", MODE_PRIVATE);
                        editor = saveData.edit();
                        editor.putString("user", new Gson().toJson(user));
                        editor.apply();
                        editor.commit();
                        startActivity(new Intent(AuthLogin.this, MainActivity.class));
                        AuthLogin.this.finish();
                    }else{
                        FancyToast.makeText(AuthLogin.this,"Credentials doesn't match",
                                FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show();
                    }
                    Log.d(TAG, new Gson().toJson(user));
                } else {
                    FancyToast.makeText(AuthLogin.this,"Something went wrong, please try again later",
                            FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                }
            }
        });
    }
}