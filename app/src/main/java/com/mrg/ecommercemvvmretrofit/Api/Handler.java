package com.mrg.ecommercemvvmretrofit.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Handler {
    private Base base;
    private static Handler instance = null;
    public Handler(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Base.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        base = retrofit.create(Base.class);
    }
    public  static synchronized Handler getInstance(){
        if (instance == null){
            instance =new Handler();
        }
        return instance;
    }
    public Base getApi() {
        return base;
    }
}
