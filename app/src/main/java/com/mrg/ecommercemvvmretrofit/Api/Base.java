package com.mrg.ecommercemvvmretrofit.Api;

import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.Models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Base {
    String BASE_URL ="https://api.escuelajs.co/api/v1/";
    @POST("users/")
    Call<User> createUser(@Body User user);
    @POST("auth/login")
    Call<User> SignInUser(@Body User user);
    @GET("products/")
    Call<List<Product>> getProduct();

}
