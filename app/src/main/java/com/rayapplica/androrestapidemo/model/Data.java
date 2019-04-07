package com.rayapplica.androrestapidemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int per_page;
    @SerializedName("total")
    private int total;
    @SerializedName("total_pages")
    private int total_pages;
    @SerializedName("data")
    private List<User> data;

    public Data(int page, int per_page, int total, int total_pages, List<User> data) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
    }
    public List<User> getUserList(){
        return data;
    }
}
