package com.rayapplica.androrestapidemo.network;

import com.rayapplica.androrestapidemo.model.Data;
import com.rayapplica.androrestapidemo.model.PostUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserDataService {
    @GET("users")
    Call<Data> getData();

    @POST("users")
    Call<PostUser> savePost(@Body PostUser postUser);
}
