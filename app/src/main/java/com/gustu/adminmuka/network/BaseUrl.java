package com.gustu.adminmuka.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseUrl {
    private Retrofit retrofit = null;
    public API getApi(){
String BASE_URL = "https://mukaweb.000webhostapp.com/";
        if (retrofit==null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
return retrofit.create(API.class);
    }
}
