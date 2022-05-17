package com.example.shopapp;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartItem {




    private Products productName;
    private int quantity;


    public CartItem() {
    }

    public CartItem(Products productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }


    public Products getProductName() {
        return productName;
    }

    public void setProductName(Products productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
