package com.rayapplica.androrestapidemo.model;

import com.google.gson.annotations.SerializedName;

public class PostUser {
    @SerializedName("name")
    private String name;
    @SerializedName("job")
    private String job;

    public PostUser(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
