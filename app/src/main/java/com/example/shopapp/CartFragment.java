package com.example.shopapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartFragment extends Fragment {


    private TextView totalPricetextView;
    private float totalPrice=0;
    private RecyclerView recyclerViewcart;
    private ArrayList<Products> productsArrayList;
    private CartAdapter cartAdapter;
    private DatabaseReference myRefProduct;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private Button buttonBuy;

    public CartFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRefProduct = database.getReference("cartList").child(mAuth.getUid()).child("Products");

        productsArrayList=new ArrayList<>();

        cartList();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view= inflater.inflate(R.layout.fragment_cart, container, false);


         totalPricetextView =(TextView)view.findViewById(R.id.cartfragmentTotalPrice);
         recyclerViewcart=(RecyclerView) view.findViewById(R.id.cartfragementRV);
         buttonBuy=(Button)view.findViewById(R.id.cartfrgmentbutton);

         buttonBuy.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 database = FirebaseDatabase.getInstance();
                 mAuth=FirebaseAuth.getInstance();
                 myRefProduct = database.getReference("cartList").child(mAuth.getUid()).child("Products");
                 myRefProduct.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {


                         for (DataSnapshot d:snapshot.getChildren()) {

                            // d.getRef().removeValue();

                         }


                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });
                 Toast.makeText(getActivity(),"All Items Purchased Total Price: "+totalPrice,Toast.LENGTH_SHORT).show();
             }
         });


        cartAdapter =new CartAdapter(getContext(),productsArrayList);
        recyclerViewcart.setAdapter(cartAdapter);

        recyclerViewcart.setHasFixedSize(true);
        recyclerViewcart.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));





         return view;


    }


    public void cartList(){
        myRefProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                totalPrice=0;
                productsArrayList.clear();

                for (DataSnapshot d:snapshot.getChildren()) {

                    Products product=d.getValue(Products.class);
                    product.setProduct_id(d.getKey());

                    totalPrice= totalPrice+Float.parseFloat(product.getProduct_cost());
                    productsArrayList.add(product);

                }
                System.out.println(totalPrice+ " ------------- ");
                totalPricetextView.setText("Total Price : " +totalPrice);
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}