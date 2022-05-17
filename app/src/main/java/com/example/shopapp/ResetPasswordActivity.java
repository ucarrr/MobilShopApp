package com.example.shopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText editTextResetEmail;
    private Button sendResetButton;
    private TextView resetBack;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        editTextResetEmail=(EditText)findViewById(R.id.editText_ResetEmail);
        sendResetButton=(Button)findViewById(R.id.button_ResetPassword);
        resetBack=(TextView)findViewById(R.id.ResetBack);


        resetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
            }
        });



        mAuth=FirebaseAuth.getInstance();













    }

    public void resetPasswordClick(View view){

        String emailReset=editTextResetEmail.getText().toString();

        System.out.println(emailReset);

        if(TextUtils.isEmpty(emailReset)){

            Toast.makeText(ResetPasswordActivity.this,"Please Enter a Email",Toast.LENGTH_SHORT).show();

        }else{

            mAuth.sendPasswordResetEmail(emailReset).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull  Task<Void> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(ResetPasswordActivity.this,"Please Check your Email Account...",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));

                    }else{
                        Toast.makeText(ResetPasswordActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }
}