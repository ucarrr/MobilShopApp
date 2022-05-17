package com.example.shopapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;




public class LoginActivity extends AppCompatActivity {




    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mReference;

    private ImageView facebookbutton;
    private ImageView gmailbutton;
    private HashMap<String,Object> hashMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        gmailbutton=(ImageView)findViewById(R.id.imageButtonGmail);

        hashMap =new HashMap<>();
        mAuth=FirebaseAuth.getInstance();



         mUser=mAuth.getCurrentUser();

 /*       if(mUser != null){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }*/



















    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mUser = mAuth.getCurrentUser();
        if(mUser != null){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    public void resetPassword(View view){

        Intent intent=new Intent(LoginActivity.this,ResetPasswordActivity.class);
        startActivity(intent);

    }


    public void navigateUser(View view){

        Intent intent =new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }

    public void login(View view){

        EditText editTextEmail=(EditText) findViewById(R.id.login_editTextEmail);
        String email=editTextEmail.getText().toString();

        EditText editTextPassword=(EditText) findViewById(R.id.login_editTextPassword);
        String password=editTextPassword.getText().toString();

        Intent mainintent=new Intent(this,MainActivity.class);




        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    mUser=mAuth.getCurrentUser();
                    mReference=FirebaseDatabase.getInstance().getReference("users").child(mUser.getUid());


                    mReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                             for(DataSnapshot snp:snapshot.getChildren()){
                                 //String key=snp.getKey();
                                 //System.out.println(snp.getKey()+ " = " +snp.getValue());
                                // hashMap.put(snp.getKey(),snp.getValue());
                             }
                          //  mainintent.putExtra("map", hashMap);
                            startActivity(mainintent);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    });


                }else {
                    Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

   /* public void getUserInfo(){

        mUser=mAuth.getCurrentUser();
        String id = mUser.getUid();
        DatabaseReference username = mReference.child(id).child("username");
        username.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue().toString();
                System.out.println("usermane other: " + username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
*/
}

