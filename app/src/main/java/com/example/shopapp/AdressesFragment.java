package com.example.shopapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdressesFragment extends Fragment {

    private CircleImageView circleImageViewNav;
    private MapsFragment mapsFragment;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapsFragment=new MapsFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_adresses, container, false);


        circleImageViewNav=(CircleImageView)view.findViewById(R.id.navigasyonCircleImageview);

        callMap();
        return view;
    }

    public void callMap(){
        circleImageViewNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }


}