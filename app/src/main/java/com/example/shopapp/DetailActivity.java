package com.example.shopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textViewProductName;
    TextView textViewProductResource;
    TextView textViewEstimate;
    TextView textViewDetailComments;
    TextView textViewDetailCost;
    Button buttonAddCartDetail;
    Products productsDetail;

    private FirebaseAuth mAuth;

    private DatabaseReference myRefProduct;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView=findViewById(R.id.imageViewDetail);
        textViewProductName=findViewById(R.id.textViewDetailProductName);
        textViewProductResource=findViewById(R.id.textViewDetailProductResource);
        textViewEstimate=findViewById(R.id.textViewDetailEstimate);
        textViewDetailComments=findViewById(R.id.textViewDetailComments);
        textViewDetailCost=findViewById(R.id.textViewDetailCost);
        buttonAddCartDetail =findViewById(R.id.Details_AddtoCartButton);


        productsDetail=(Products)getIntent().getSerializableExtra("product");


    /*    imageView.setImageResource(getResources().getIdentifier(productsDetail.getProduct_image(),
                        "drawable",getPackageName()));*/

        String url=productsDetail.getProduct_image();
        Picasso.get().load(url).into(imageView);
        textViewProductName.setText(productsDetail.getProduct_name());
        textViewProductResource.setText(productsDetail.getProduct_brand());
       // textViewProductResource.setText(productsDetail.getCategory_name());
        textViewEstimate.setText(productsDetail.getProduct_shipment());
        textViewDetailComments.setText(productsDetail.getProduct_Commets());

        textViewDetailCost.setText(productsDetail.getProduct_cost());
        Intent detailintent=new Intent(this,MainActivity.class);

        buttonAddCartDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String saveCurrentTime , SaveCurrentDate;
                Calendar calendar=Calendar.getInstance();

                SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd, yyyy");
                SaveCurrentDate=currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime=currentTime.format(calendar.getTime());

                final DatabaseReference cartlistref= FirebaseDatabase.getInstance().getReference("cartList");

                /*database = FirebaseDatabase.getInstance();
                myRefProduct = database.getReference("category");
*/
                final HashMap<String,Object> cartmap=new HashMap<>();
                cartmap.put("product_id",productsDetail.getProduct_id());
                cartmap.put("Product_name",productsDetail.getProduct_name());
                cartmap.put("Product_cost",productsDetail.getProduct_cost());
                cartmap.put("Product_image",productsDetail.getProduct_image());
                cartmap.put("Product_brand",productsDetail.getProduct_brand());
                cartmap.put("Product_shipment",productsDetail.getProduct_shipment());
                cartmap.put("Product_genderType",productsDetail.getProduct_genderType());
                cartmap.put("Time",saveCurrentTime);
                cartmap.put("Date",SaveCurrentDate);


                mAuth= FirebaseAuth.getInstance();
                cartlistref.child(mAuth.getUid())
                        .child("Products").child(productsDetail.getProduct_id()).updateChildren(cartmap).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    System.out.println("lkadsşlhasşpdaspşsapdasdasdasdasdasdasdsd");
                                    Toast.makeText(getApplicationContext(),productsDetail.getProduct_name()+" Added Card sss",Toast.LENGTH_SHORT).show();
                                    startActivity(detailintent);
                                }
                            }
                        });









            }
        });

    }
}