package com.example.shopapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProduct;
    private ArrayList<Products> productsArrayList;
    private ProductAdapter productAdapter;


    private DatabaseReference myRefProduct;
    private FirebaseDatabase database;

    private Category category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        recyclerViewProduct=findViewById(R.id.Product_Activity_RecylerView);
        recyclerViewProduct.setHasFixedSize(true);
        recyclerViewProduct.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        Intent intent = getIntent();

        category= (Category) getIntent().getSerializableExtra("category");

        database = FirebaseDatabase.getInstance();
        myRefProduct = database.getReference("newProducts");

        productsArrayList=new ArrayList<>();




/*        Products p1=new Products("1","Hoodie","hoodie","20",
                "Nike","3 days","Erkek","1",
                "Comments", "Giyim","xxl");

        Products p2=new Products("2","Topuklu Ayakkabı","topukluAyakkabı","4000",
                "Louis Vuitton","15 days","Kadın","1",
                "Comments", "Ayakkabı","38");

        Products p3=new Products("3","Ayakkabı","ayakkabı","3000",
                "Prada","10 days","Erkek","5",
                "Comments", "Ayakkabı","38");

        Products p4=new Products("4","Saksı","saksı","35",
                "Tasarimbo","6 days","other","25",
                "Comments", "Ev&Yaşam","-");

        Products p5=new Products("5","Tütsü","tutsu","15",
                "Çetin Accessories","8 days","other","13",
                "Comments", "Ev&Yaşam","-");

        Products p6=new Products("6","Keten Ceket","ketenCeket","1500",
                "Mavi","10 days","Erkek","23",
                "Comments", "Ceket","s");

        Products p7=new Products("7","Parka","parka","2000",
                "Jack Jones","5 days","Erkek","10",
                "Comments", "Ceket","l");

        Products p8=new Products("8","Hasır Çanta","hasirCanta","300",
                "Modanisa","6 days","Kadın","40",
                "Comments", "Çanta","small");

        Products p9=new Products("9","Deri Çanta","deriCanta","400",
                "Koton","9 days","Kadın","20",
                "Comments", "Çanta","small");

        Products p10=new Products("10","Oyun Konsolu","oyunKonsolu","250",
                "nintendo","12 days","Çocuk","23",
                "Comments", "Elektronik","-");

        Products p11=new Products("11","Uzaktan Kumanda","uzaktanKumanda","300",
                "samsung","12 days","other","15",
                "Comments", "Elektronik","-");


        Products p13=new Products("13","Deniz Şortu","denizSortu","40",
                "Adesea","10 days","Erkek","35",
                "Comments", "Spor & Outdoor","m");

        Products p14=new Products("14","Deniz Gözlüğü","denizGozlugu","25",
                "Altis","7 days","Erkek","30",
                "Comments", "Spor & Outdoor","-");

        Products p15=new Products("15","Bileklik","bileklik","35,5",
                "Penta","14 days","Kadın","16",
                "Comments", "Aksesuar","-");

        Products p16=new Products("16","Küpe","kupe","43",
                "Welch","12 days","Kadın","4",
                "Comments", "Aksesuar","-");

        Products p17=new Products("17","Ruj","ruj","38",
                "Maybelline","16 days","Kadın","3",
                "Comments", "Kozmetik","-");

        Products p18=new Products("18","Allık","allik","34",
                "Golden Rose","9 days","Kadın","35",
                "Comments", "Kozmetik","-");

        Products p19=new Products("19","Tank","tank","56",
                "OyuncakBiziz","5 days","Çocuk","65",
                "Comments", "Hediyelik","-");

        Products p20=new Products("20","Ahtapot","ahtapot","26",
                "Oli","7 days","Çocuk","45",
                "Comments", "Hediyelik","-");*/












     /*   productsArrayList.add(products1);
        productsArrayList.add(products2);
        productsArrayList.add(products3);
        productsArrayList.add(products4);
        productsArrayList.add(products5);
        productsArrayList.add(products6);
        productsArrayList.add(products7);
        productsArrayList.add(products8);
        productsArrayList.add(products9);
        productsArrayList.add(products10);
        productsArrayList.add(products11);
        productsArrayList.add(products12);
        productsArrayList.add(products13);
        productsArrayList.add(products14);
*/
        /*myRefProduct.push().setValue(p1);
        myRefProduct.push().setValue(p2);
        myRefProduct.push().setValue(p3);
        myRefProduct.push().setValue(p4);
        myRefProduct.push().setValue(p5);
        myRefProduct.push().setValue(p6);
        myRefProduct.push().setValue(p7);
        myRefProduct.push().setValue(p8);
        myRefProduct.push().setValue(p9);
        myRefProduct.push().setValue(p10);
        myRefProduct.push().setValue(p11);
        myRefProduct.push().setValue(p12);
        myRefProduct.push().setValue(p13);
        myRefProduct.push().setValue(p14);
        myRefProduct.push().setValue(p15);
        myRefProduct.push().setValue(p16);
        myRefProduct.push().setValue(p17);
        myRefProduct.push().setValue(p18);
        myRefProduct.push().setValue(p19);
        myRefProduct.push().setValue(p20);*/

     /*   myRefProduct.push().setValue(p21);
        myRefProduct.push().setValue(p22);
        myRefProduct.push().setValue(p23);
        myRefProduct.push().setValue(p24);
        myRefProduct.push().setValue(p25);
        myRefProduct.push().setValue(p26);
        myRefProduct.push().setValue(p27);
        myRefProduct.push().setValue(p28);
        myRefProduct.push().setValue(p29);
        myRefProduct.push().setValue(p30);
        myRefProduct.push().setValue(p31);
        myRefProduct.push().setValue(p32);
        myRefProduct.push().setValue(p34);
        myRefProduct.push().setValue(p35);
        myRefProduct.push().setValue(p36);
        myRefProduct.push().setValue(p37);
        myRefProduct.push().setValue(p38);
        myRefProduct.push().setValue(p39);*/





        productAdapter=new ProductAdapter(this,productsArrayList);
        recyclerViewProduct.setAdapter(productAdapter);



        callProduct();

        callProductGender();


        
    }


    public void callProduct(){

        Query query=myRefProduct.orderByChild("category_name").equalTo(category.getCategory_Name());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               // productsArrayList.clear();

                for (DataSnapshot d:snapshot.getChildren()) {

                    Products products=d.getValue(Products.class);
                    products.setProduct_id(d.getKey());

                    productsArrayList.add(products);

                }
                productAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }


    public void callProductGender(){

        Query queryGender=myRefProduct.orderByChild("product_genderType").equalTo(category.getCategory_Name());

        queryGender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

              //  productsArrayList.clear();

                for (DataSnapshot d:snapshot.getChildren()) {

                    Products products=d.getValue(Products.class);
                    products.setProduct_id(d.getKey());

                    productsArrayList.add(products);

                }
                productAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }
}