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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPhone;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private HashMap<String,Object> hashMapdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth =FirebaseAuth.getInstance();
        mDatabaseReference= FirebaseDatabase.getInstance().getReference();
    }


    public void LoginPage(View view){
        Intent intent =new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public  void createUser(View view){

        editTextName=(EditText)findViewById(R.id.register_editTextTextPersonName);
        editTextSurname=(EditText)findViewById(R.id.register_editTextTextPersonSurname);
        editTextPhone=(EditText)findViewById(R.id.register_editTextPhone);


        EditText editTextEmail=(EditText)findViewById(R.id.registered_editTextEmail);
        String email= editTextEmail.getText().toString();

        EditText editTextPassword=(EditText)findViewById(R.id.register_editTextPassword);
        String password=editTextPassword.getText().toString();

        EditText editTextConfirmPassword=(EditText)findViewById(R.id.register_editTextConfirmPassword);
        String confirmPasswod=editTextConfirmPassword.getText().toString();


        String name= editTextName.getText().toString();
        String surname=editTextSurname.getText().toString();
        String phone= editTextPhone.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)    ){
            Toast.makeText(RegisterActivity.this,"There is a empty area. Please fill",Toast.LENGTH_SHORT).show();
        }else {
            if (!password.equals(confirmPasswod)){
                Toast.makeText(RegisterActivity.this,"Password and Confirm Password must be the same",Toast.LENGTH_SHORT).show();
            }else {
                mAuth.createUserWithEmailAndPassword(email,password).
                        addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user=mAuth.getCurrentUser();
                                    System.out.println(user.getEmail());


                                    mUser=mAuth.getCurrentUser();

                                    hashMapdata=new HashMap<>();
                                    hashMapdata.put("userName",name);
                                    hashMapdata.put("userSurname",surname);
                                    hashMapdata.put("userPhone",phone);
                                    hashMapdata.put("userEmail",email);
                                    hashMapdata.put("userPassword",password);

                                    //mDatabaseReference=FirebaseDatabase.getInstance().getReference().child("users");

                                    mDatabaseReference.child("users").child(mUser.getUid()).
                                            setValue(hashMapdata).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this,"Succeed",Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                                startActivity(intent);

                                            }else {
                                                Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });




                                }else {
                                    Toast.makeText(RegisterActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }



    }

}