package com.example.shopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdressActivity extends AppCompatActivity {


    private CircleImageView circleImageViewAddre;
    private HashMap<String,Object> hashMapdata;
    private EditText editText_adressName, editText_city,
            editText_district, editText_neighborhood, editText_decription,editText_Country;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);

        circleImageViewAddre =(CircleImageView)findViewById(R.id.adressActivityCircleImageview);
        editText_adressName =(EditText)findViewById(R.id.adressActivity_editTextAdressName);
        editText_city =(EditText)findViewById(R.id.adressActivity_editTextCity);
        editText_district =(EditText)findViewById(R.id.adressActivity_editTextDistrict);
        editText_neighborhood =(EditText)findViewById(R.id.adressActivity_editTextneighborhood);
        editText_decription =(EditText)findViewById(R.id.adressActivity_editTextDecription);
        editText_Country =(EditText)findViewById(R.id.adressActivity_editTextCountry);


        hashMapdata=new HashMap<>();


        mAuth =FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference();





    }

    public void addAddressActivity(View view){
        String adresName=editText_adressName.getText().toString();
        String country= editText_Country.getText().toString();
        String city= editText_city.getText().toString();
        String district= editText_district.getText().toString();
        String neighborhood= editText_neighborhood.getText().toString();
        String decription= editText_decription.getText().toString();


        if (TextUtils.isEmpty(adresName) || TextUtils.isEmpty(city) || TextUtils.isEmpty(country) || TextUtils.isEmpty(district)
                || TextUtils.isEmpty(neighborhood) || TextUtils.isEmpty(decription)    ){

            Toast.makeText(AdressActivity.this,"There is a empty area. Please fill",Toast.LENGTH_SHORT).show();
        }else {


            hashMapdata.put("UserID","");
            hashMapdata.put("adressName",adresName);
            hashMapdata.put("country",country);
            hashMapdata.put("city",city);
            hashMapdata.put("district",district);
            hashMapdata.put("neighborhood",neighborhood);
            hashMapdata.put("decription",decription);


            mDatabaseReference.child("user_Adresses").child(mUser.getUid()).child("adresses").
                    setValue(hashMapdata).addOnCompleteListener(AdressActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AdressActivity.this,"Succeed",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(AdressActivity.this,AccountFragment.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(AdressActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}