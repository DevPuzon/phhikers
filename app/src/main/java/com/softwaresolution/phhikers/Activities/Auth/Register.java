package com.softwaresolution.phhikers.Activities.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.softwaresolution.phhikers.Pojo.User;
import com.softwaresolution.phhikers.R;
import com.softwaresolution.phhikers.Utils.Loading;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Register extends AppCompatActivity {
    Loading loading;
    TextView txt_email,txt_password,txt_name,txt_conpassword
            ,txt_contact;
    MaterialButton btn_enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loading = new Loading(this);
        btn_enter = findViewById(R.id.btn_enter);
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister();
            }
        });
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        txt_conpassword = findViewById(R.id.txt_conpassword);
        txt_contact = findViewById(R.id.txt_contact);
        txt_name = findViewById(R.id.txt_name);


    }
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void onRegister(){
        String email = txt_email.getText().toString();
        String password= txt_password.getText().toString();
        String conpassword= txt_conpassword.getText().toString();
        String name= txt_name.getText().toString();
        String contact= txt_contact.getText().toString();
        if (TextUtils.isEmpty(email) ||TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(conpassword) ||TextUtils.isEmpty(name) ||
                TextUtils.isEmpty(contact)  ){
            FancyToast.makeText(Register.this,"Please fill all the data information.",
                    FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show();
            return;
        }
        if (!conpassword.equals(password)){
            FancyToast.makeText(Register.this,"Password and confirm password doesn't matched.",
                    FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show();
            return;
        }
        loading.loadDialog.show();
        String uid = UUID.randomUUID().toString();
        User userProfile = new User(email,password,name,contact,uid);
        final DocumentReference documentReference =
                db.collection("PhHikersUser")
                        .document(uid);
        documentReference.set(userProfile)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                loading.loadDialog.dismiss();
                FancyToast.makeText(Register.this,"Register successfully",
                        FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                onBackPressed();
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loading.loadDialog.dismiss();
                FancyToast.makeText(Register.this,"Something went wrong, please try again later",
                        FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
            }
        });
    }
}