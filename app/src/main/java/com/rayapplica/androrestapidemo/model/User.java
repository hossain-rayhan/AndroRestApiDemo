package com.rayapplica.androrestapidemo.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private long id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("avatar")
    private String photoURL;

    public User(long id, String firstName, String lastName, String photoURL){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoURL = photoURL;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
