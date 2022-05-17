package com.example.shopapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;
    private ImageView imageViewPlus;
    private CircleImageView circleImageView;
    private TextView profileUserName ,profileEmail,profilePhone,profilePassword,
            textViewAddNewAddres,textViewProfileLocation;
    private DatabaseReference myRefProduct;
    private Button editbutton;

    Button logout;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseStorage=FirebaseStorage.getInstance();
        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        firebaseDatabase.getReference().child("users").child(FirebaseAuth.getInstance().getUid()).
                addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull   DataSnapshot snapshot) {

                User user=snapshot.getValue(User.class);



                Picasso.get().load(user.getProfileImageUrl()).placeholder(R.drawable.profilresim)
                .into(circleImageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data.getData() != null){
            Uri sfile=data.getData();
            circleImageView.setImageURI(sfile);


            final StorageReference reference=firebaseStorage.getReference().child("profile pictures").
                    child(mAuth.getInstance().getUid());

            reference.putFile(sfile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //Toast.makeText(getActivity(),"Upload",Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {


                            firebaseDatabase.getReference().child("users").child(mAuth.getUid()).child("profileImageUrl").
                                    setValue(uri.toString());
                            Toast.makeText(getActivity(),"Picture Upload",Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_account, container, false);
        logout=(Button)view.findViewById(R.id.logout);
        imageViewPlus=(ImageView)view.findViewById(R.id.imageViewPlus);
        circleImageView=(CircleImageView)view.findViewById(R.id.circleImageViewProfilePhoto);
        profileUserName=(TextView)view.findViewById(R.id.textViewProfileUserName);
        profileEmail=(TextView)view.findViewById(R.id.textViewEmail);
        profilePhone=(TextView)view.findViewById(R.id.textViewPhone);
        profilePassword=(TextView)view.findViewById(R.id.textViewPasword);
        editbutton=(Button)view.findViewById(R.id.buttonEditProfile);
        textViewAddNewAddres=(TextView)view.findViewById(R.id.textViewAddNewAddres);
        textViewProfileLocation=(TextView)view.findViewById(R.id.textViewProfileLocation);

        textViewAddNewAddres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),AdressActivity.class));
            }
        });
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),EditProfile.class);
                startActivity(intent);
            }
        });

        imageViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        profileInfo();
        loadLocation();

        return view;

    }

    public void profileInfo(){


        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        firebaseDatabase.getReference().child("users").child(FirebaseAuth.getInstance().getUid()).
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull   DataSnapshot snapshot) {

                        User user=snapshot.getValue(User.class);

                        profileUserName.setText(user.getUserName()+" "+user.getUserSurname());
                        profileEmail.setText(user.getUserEmail());
                        profilePhone.setText(user.getUserPhone());
                        profilePassword.setText(user.getUserPassword());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





    }


    public void loadLocation(){

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        firebaseDatabase.getReference().child("user_Adresses").child(FirebaseAuth.getInstance().getUid()).child("adresses").
                addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull   DataSnapshot snapshot) {

                        //Adress adress=snapshot.getValue(Adress.class);


                        if(snapshot.getValue(Adress.class) != null ){

                            Adress adress=snapshot.getValue(Adress.class);
                            textViewProfileLocation.setText(adress.getCity()+","+adress.getCountry());
                        }

                       /* if(!TextUtils.isEmpty(adress.getCity()) && !TextUtils.isEmpty(adress.getCountry())){

                        }*/




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

}