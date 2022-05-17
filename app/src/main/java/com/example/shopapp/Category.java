package com.example.shopapp;

import java.io.Serializable;

public class Category implements Serializable {

    private String category_ID;
    private String category_Name;
    private String category_Gender;
    private String category_image;


    public Category() {
    }

    public Category(String category_ID, String category_Name, String category_image) {
        this.category_ID = category_ID;
        this.category_Name = category_Name;
        this.category_image = category_image;
    }

    public String getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(String category_ID) {
        this.category_ID = category_ID;
    }

    public String getCategory_Name() {
        return category_Name;
    }

    public void setCategory_Name(String category_Name) {
        this.category_Name = category_Name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }
}
