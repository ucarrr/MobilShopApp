package com.example.shopapp;

import java.io.Serializable;

public class Products implements Serializable {
    private String Product_id;
    private String Product_name;
    private String Product_image;
    private String Product_cost;
    private String Product_brand;
    private String Product_shipment;
    private String Product_genderType;
    private String Product_quantity;
    private String Product_Commets;
    private String category_name;
    private String Prodcuts_Size;
    //private Category category;

    public Products() {
    }

    public Products(String product_id, String product_name, String product_image, String product_cost,
                    String product_brand, String product_shipment, String product_genderType,
                    String product_quantity, String product_Commets, String category_name,
                    String prodcuts_Size) {
        Product_id = product_id;
        Product_name = product_name;
        Product_image = product_image;
        Product_cost = product_cost;
        Product_brand = product_brand;
        Product_shipment = product_shipment;
        Product_genderType = product_genderType;
        Product_quantity = product_quantity;
        Product_Commets = product_Commets;
        this.category_name = category_name;
        Prodcuts_Size = prodcuts_Size;
    }

    public String getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(String product_id) {
        Product_id = product_id;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public String getProduct_image() {
        return Product_image;
    }

    public void setProduct_image(String product_image) {
        Product_image = product_image;
    }

    public String getProduct_cost() {
        return Product_cost;
    }

    public void setProduct_cost(String product_cost) {
        Product_cost = product_cost;
    }

    public String getProduct_brand() {
        return Product_brand;
    }

    public void setProduct_brand(String product_brand) {
        Product_brand = product_brand;
    }

    public String getProduct_shipment() {
        return Product_shipment;
    }

    public void setProduct_shipment(String product_shipment) {
        Product_shipment = product_shipment;
    }

    public String getProduct_genderType() {
        return Product_genderType;
    }

    public void setProduct_genderType(String product_genderType) {
        Product_genderType = product_genderType;
    }

    public String getProduct_quantity() {
        return Product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        Product_quantity = product_quantity;
    }

    public String getProduct_Commets() {
        return Product_Commets;
    }

    public void setProduct_Commets(String product_Commets) {
        Product_Commets = product_Commets;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getProdcuts_Size() {
        return Prodcuts_Size;
    }

    public void setProdcuts_Size(String prodcuts_Size) {
        Prodcuts_Size = prodcuts_Size;
    }


    /*  public Products(String product_id, String product_name, String product_image, String product_cost, String category_name) {
        Product_id = product_id;
        Product_name = product_name;
        Product_image = product_image;
        Product_cost = product_cost;
        this.category_name = category_name;
    }*/


}
