package com.example.shopapp;

public class User {
    private String userId;
    private String userName;
    private String userSurname;
    private String userPhone;
    private String userEmail;
    private String userPassword;
    private String userAddress;
    private String profileImageUrl;

    public User() {
    }

    public User(String userId, String userName, String userSurname,
                String userPhone, String userEmail, String userPassword, String userAdress, String profileImageUrl) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAddress = userAdress;
        this.profileImageUrl = profileImageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAdress() {
        return userAddress;
    }

    public void setUserAdress(String userAdress) {
        this.userAddress = userAdress;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
