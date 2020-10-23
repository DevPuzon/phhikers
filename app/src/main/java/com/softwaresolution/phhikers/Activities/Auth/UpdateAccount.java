package com.softwaresolution.phhikers.Activities.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Pojo.User;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.Loading;

import java.util.UUID;

public class UpdateAccount extends AppCompatActivity {
    Loading loading;
    TextView txt_email,txt_password,txt_name,txt_conpassword
            ,txt_contact;
    MaterialButton btn_enter;
    User user;
    private SharedPreferences saveData;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);
        saveData = getSharedPreferences("saveData", MODE_PRIVATE);
        editor = saveData.edit();
        getSupportActionBar().setTitle("Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true); 

        loading = new Loading(this);
        btn_enter = findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpdate();
            }
        });
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        txt_conpassword = findViewById(R.id.txt_conpassword);
        txt_contact = findViewById(R.id.txt_contact);
        txt_name = findViewById(R.id.txt_name);

        user = new Gson().fromJson(saveData.getString("user",null),User.class);
        txt_email.setText(user.getEmail());
        txt_password.setText(user.getPassword());
        txt_conpassword.setText(user.getPassword());
        txt_contact.setText(user.getContact());
        txt_name.setText(user.getName());
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void onUpdate(){
        String email = txt_email.getText().toString();
        String password= txt_password.getText().toString();
        String conpassword= txt_conpassword.getText().toString();
        String name= txt_name.getText().toString();
        String contact= txt_contact.getText().toString();
        if (TextUtils.isEmpty(email) ||TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(conpassword) ||TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(contact)  ){
            FancyToast.makeText(UpdateAccount.this,"Please fill all the data information.",
                    FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show();
            return;
        }
        if (!conpassword.equals(password)){
            FancyToast.makeText(UpdateAccount.this,"Password and confirm password doesn't matched.",
                    FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show();
            return;
        }
        loading.loadDialog.show();
        final User userProfile = new User(email,password,name,contact,user.getUid());
        final DocumentReference documentReference =
                db.collection("PhHikersUser")
                        .document(user.getUid());
        documentReference.set(userProfile)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                loading.loadDialog.dismiss();
                saveData = getSharedPreferences("saveData", MODE_PRIVATE);
                editor = saveData.edit();
                editor.putString("user", new Gson().toJson(userProfile));
                editor.apply();
                editor.commit();
                FancyToast.makeText(UpdateAccount.this,"Updated successfully",
                        FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                onBackPressed();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loading.loadDialog.dismiss();
                FancyToast.makeText(UpdateAccount.this,"Something went wrong, please try again later",
                        FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
            }
        });
    }
}