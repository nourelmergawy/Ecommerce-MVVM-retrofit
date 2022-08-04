package com.mrg.ecommercemvvmretrofit.Api;

import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Base {
    String BASE_URL ="https://api.escuelajs.co/api/v1/";
    @POST("users/")
    Call<User> createUser(@Body User user);
    @POST("auth/login")
    Call<User> SignInUser(@Body User user);
    @GET("products/")
    Call<List<Product>> getProduct();
    @GET("auth/profile")
    Call<User> getUserData( @Header("Authorization") String token);


}
