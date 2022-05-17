package com.example.shopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class EditProfile extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseDatabase firebaseDatabase;

    private FirebaseUser mUser;
    private DatabaseReference mDatabaseReference;


    private EditText editTextPhoneProfile;
    private EditText editTextEmailProfile;
    private EditText editTextPasswordProfile, editTextComfirmPasswordProfile;
    private ImageView imageViewEditProfilePhoto;


    private EditText editTextProfileName,editTextProfileSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);



        editTextProfileName =(EditText)findViewById((R.id.editTextNameProfile));
        editTextProfileSurname =(EditText)findViewById((R.id.editTextSurnameProfile));

        editTextPhoneProfile =(EditText)findViewById(R.id.editTextPhoneProfile);
        editTextEmailProfile =(EditText)findViewById(R.id.editTextEmailAddressProfile);
        editTextPasswordProfile =(EditText)findViewById(R.id.editTextPasswordProfile);
        editTextComfirmPasswordProfile =(EditText)findViewById(R.id.editTextPasswordComfirmProfile);


        imageViewEditProfilePhoto=(ImageView)findViewById(R.id.imageViewEditProfilePhoto);

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();




        firebaseDatabase.getReference().child("users").child(FirebaseAuth.getInstance().getUid()).
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        User user=snapshot.getValue(User.class);

                        if(!TextUtils.isEmpty(user.getProfileImageUrl())){

                            Picasso.get().load(user.getProfileImageUrl()).placeholder(R.drawable.profilresim)
                                    .into(imageViewEditProfilePhoto);
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        mDatabaseReference= FirebaseDatabase.getInstance().getReference();







    }

    public void UploadData(View view){
        String name =editTextProfileName.getText().toString();
        String surname=editTextProfileSurname.getText().toString();
        String phone= editTextPhoneProfile.getText().toString();
        String email= editTextEmailProfile.getText().toString();
        String passwordChange= editTextPasswordProfile.getText().toString().trim();

        String confirm= editTextComfirmPasswordProfile.getText().toString();


        if(!name.trim().isEmpty()){

            mDatabaseReference.child("users").child(mAuth.getUid()).child("userName").
                    setValue(name);
         }
        if(!surname.trim().isEmpty()){
            mDatabaseReference.child("users").child(mAuth.getUid()).child("userSurname").
                    setValue(surname);
        }
        if(!phone.trim().isEmpty()){
            mDatabaseReference.child("users").child(mAuth.getUid()).child("userPhone").
                    setValue(phone);
        }
        if(!email.trim().isEmpty()){

            mUser=mAuth.getInstance().getCurrentUser();

            mUser.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        mDatabaseReference.child("users").child(mAuth.getUid()).child("userEmail").
                                setValue(email);
                    }
                }
            });

        }
        if(!passwordChange.trim().isEmpty() && !confirm.trim().isEmpty() ){

            if (!passwordChange.equals(confirm)){
                Toast.makeText(EditProfile.this,"Password and Confirm Password must be the same",Toast.LENGTH_SHORT).show();
            }else {

                mUser = mAuth.getInstance().getCurrentUser();

                mUser.updatePassword(passwordChange).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        mDatabaseReference.child("users").child(mAuth.getUid()).child("userPassword").
                                setValue(passwordChange);
                    }
                });



            }
        }

               Intent intent=new Intent(EditProfile.this,MainActivity.class);
               Toast.makeText(EditProfile.this,"Saved",Toast.LENGTH_SHORT).show();
               startActivity(intent);


    }



}