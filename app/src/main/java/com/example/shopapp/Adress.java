package com.example.shopapp;

public class Adress {

    private String userID;
    private String Adress_Name;
    private String City;
    private String Country;
    private String District;
    private String Neighborhood;
    private String Decription;

    public Adress() {
    }

    public Adress(String userID, String adress_Name, String city, String country,
                  String district, String neighborhood, String decription) {
        this.userID = userID;
        Adress_Name = adress_Name;
        City = city;
        Country = country;
        District = district;
        Neighborhood = neighborhood;
        Decription = decription;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAdress_Name() {
        return Adress_Name;
    }

    public void setAdress_Name(String adress_Name) {
        Adress_Name = adress_Name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getNeighborhood() {
        return Neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        Neighborhood = neighborhood;
    }

    public String getDecription() {
        return Decription;
    }

    public void setDecription(String decription) {
        Decription = decription;
    }
}
