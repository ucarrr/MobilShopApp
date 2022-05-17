package com.example.shopapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FindViewholderProduct extends RecyclerView.ViewHolder {


    public ImageView imageViewProduct;
    public TextView textViewProductTitle;
    public TextView textViewProductCost;
    public Button buttonAddCart;
    public ImageView imageViewMoreMenu;
    public CardView cardViewProduct;


    public FindViewholderProduct(@NonNull View itemView) {

        super(itemView);
        imageViewProduct=itemView.findViewById(R.id.imageViewProduct);
        textViewProductTitle=itemView.findViewById(R.id.textViewProductTitle);
        textViewProductCost=itemView.findViewById(R.id.textViewProductCost);
        buttonAddCart=itemView.findViewById(R.id.buttonAddCart);
        imageViewMoreMenu=itemView.findViewById(R.id.imageMoreMenu);
        cardViewProduct=itemView.findViewById(R.id.product_CardView);


    }
}
