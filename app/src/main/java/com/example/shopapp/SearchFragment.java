package com.example.shopapp;

import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.internal.Asserts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SearchFragment extends Fragment {


    private RecyclerView recyclerView;
    public ArrayList<Products> productsArrayList;
    public ProductAdapter productAdapter;
    private DatabaseReference myRefProduct;
    private FirebaseDatabase database;
    private SearchView searchView;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private FirebaseRecyclerOptions<Products> firebaseRecyclerOptions;
    private FirebaseRecyclerAdapter<Products,FindViewholderProduct> firebaseRecyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        myRefProduct = database.getReference("newProducts");







    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView=(RecyclerView) view.findViewById(R.id.searchfragmentRV);
        searchView=(SearchView)view.findViewById(R.id.searchView);

        productsArrayList=new ArrayList<>();

        productAdapter=new ProductAdapter(getActivity(),productsArrayList);

        recyclerView.setAdapter(productAdapter);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));




        search();



        productList();

        return view;
    }



public void search(){
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            firebaseSearch(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    });
}

    private void firebaseSearch(String searhText){


        Query query=myRefProduct.orderByChild("product_name").startAt(searhText).endAt(searhText+"\uf8ff");


        firebaseRecyclerOptions=new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(query,Products.class).build();

        firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Products, FindViewholderProduct>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull  FindViewholderProduct holder,
                                            int position, @NonNull  Products model) {



            /*    holder.imageViewProduct.setImageResource(
                        getResources().getIdentifier(model.getProduct_image(),
                                "drawable",getActivity().getPackageName()));*/

                String url=model.getProduct_image();
                Picasso.get().load(url).into(holder.imageViewProduct);
                holder.textViewProductTitle.setText(model.getProduct_name());
                holder.textViewProductCost.setText(model.getProduct_cost()+" Tl ");



            }

            @NonNull

            @Override
            public FindViewholderProduct onCreateViewHolder(@NonNull   ViewGroup parent, int viewType) {

                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product_design,parent,false);

                return new FindViewholderProduct(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);



    }
    public void productList(){
        myRefProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                productsArrayList.clear();
                for (DataSnapshot d:snapshot.getChildren()){

                    Products product= d.getValue(Products.class);

                    product.setProduct_id(d.getKey());
                    productsArrayList.add(product);


                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

