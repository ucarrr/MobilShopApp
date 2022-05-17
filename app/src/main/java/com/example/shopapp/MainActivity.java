package com.example.shopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment;
    SearchFragment searchFragment;
    CartFragment cartFragment;
    AdressesFragment adressesFragment;
    AccountFragment accountFragment;
    MapsFragment mapsFragment;

    HashMap<String,Object> hashMap;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        //hashMap = (HashMap<String, Object>)intent.getSerializableExtra("map");

     /*   for (String i : hashMap.keySet()) {
            System.out.println("key: " + i + " value: " + hashMap.get(i));
             if (i.equals("userName")){
                 System.out.println("----------");
                 System.out.println(i +" -- " +hashMap.get(i));
                 System.out.println("----------");
             }
        }*/



        bottomNavigationView =(BottomNavigationView)findViewById(R.id.main_activity_BottonNavigationView);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        cartFragment = new CartFragment();
        adressesFragment = new AdressesFragment();
        accountFragment = new AccountFragment();
        mapsFragment=new MapsFragment();


        setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home:
                                setFragment(homeFragment);
                                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.search:

                                setFragment(searchFragment);
                                Toast.makeText(getApplicationContext(),"Search",Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.cart:

                                setFragment(cartFragment);
                                Toast.makeText(getApplicationContext(),"MY cart",Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.adress:

                                setFragment(mapsFragment);
                                Toast.makeText(getApplicationContext(),"My Adress",Toast.LENGTH_SHORT).show();
                                return true;

                            case R.id.account:

                                setFragment(accountFragment);
                                Toast.makeText(getApplicationContext(),"My account",Toast.LENGTH_SHORT).show();
                                return true;

                            default:
                                return false;
                        }

                    }
                });






    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_framelayout,fragment);
        fragmentTransaction.commit();
    }




}