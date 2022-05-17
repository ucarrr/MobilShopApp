package com.example.shopapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    public ArrayList<Products> productsArrayList;
    public ProductAdapter productAdapter;
    private DatabaseReference myRefProduct;
    private FirebaseDatabase database;







    private CategoryAdapter categoryAdapter;
    private ArrayList<Category> arrayListCategory;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recylerViewHomeFragment);








        allCategory();

        categoryAdapter=new CategoryAdapter(getActivity(),arrayListCategory);
        recyclerView.setAdapter(categoryAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));








        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);







        database = FirebaseDatabase.getInstance();
        myRefProduct = database.getReference("category");


        arrayListCategory=new ArrayList<>();



/*        Category category1=new Category("11","Erkek","man");
        Category category2=new Category("12","Kadın","kadin");
        Category category3=new Category("13","Çocuk","cocuk");

        myRefProduct.push().setValue(category1);
        myRefProduct.push().setValue(category2);
        myRefProduct.push().setValue(category3);*/
            /*

              Category category1=new Category("11","Giyim","jean");
        Category category2=new Category("2","Ayakkabı","ayakkabi");
        Category category3=new Category("3","Ev&Yaşam","oda");
               Category category4=new Category("4","Ceket","man");
        Category category5=new Category("5","Çanta","cantakategori");
        Category category6=new Category("6","Elektronik","bilgisayar");
        Category category7=new Category("7","Spor & Outdoor","spor");
        Category category8=new Category("8","Aksesuar","aksesuarmoda");
        Category category9=new Category("9","Kozmetik","kozmetik");
        Category category10=new Category("10","Hediyelik","hediyelik");

        */



      /*  arrayListCategory.add(category1);
        arrayListCategory.add(category2);
        arrayListCategory.add(category3);
        arrayListCategory.add(category4);
        arrayListCategory.add(category5);
        arrayListCategory.add(category6);
        arrayListCategory.add(category7);
        arrayListCategory.add(category8);
        arrayListCategory.add(category9);
        arrayListCategory.add(category10);



        myRefProduct.push().setValue(category1);
        myRefProduct.push().setValue(category2);
        myRefProduct.push().setValue(category3);
        myRefProduct.push().setValue(category4);
        myRefProduct.push().setValue(category5);
        myRefProduct.push().setValue(category6);
        myRefProduct.push().setValue(category7);
        myRefProduct.push().setValue(category8);
        myRefProduct.push().setValue(category9);
        myRefProduct.push().setValue(category10);
*/















    }


    public void allCategory(){
        myRefProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayListCategory.clear();

                for (DataSnapshot d:snapshot.getChildren()) {

                    Category category=d.getValue(Category.class);
                    category.setCategory_ID(d.getKey());

                    arrayListCategory.add(category);
                    
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}