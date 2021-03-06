package com.rayapplica.androrestapidemo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private  static Retrofit retrofit;
    private static final String BASE_URL = "https://reqres.in/api/";

    //making the constructor private to implement singleton design pattern
    private RetrofitClient(){}

    //single ton pattern to ensure single retrofit instance
    public static Retrofit getRetrofitInstance(){
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
