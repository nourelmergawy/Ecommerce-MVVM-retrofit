package com.mrg.ecommercemvvmretrofit;

import android.widget.Toast;

import com.mrg.ecommercemvvmretrofit.Api.Handler;
import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.Models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;

public class Repository {
    private static Repository Instance;
//    private ArrayList<User> dataSet =new ArrayList<User>();
    public static Repository getInstance(){
        if(Instance == null){
            Instance = new Repository();
        }
        return Instance;
    }

    public Call<User> createUser(@Body User user){

        Call<User> call = Handler.getInstance().getApi().createUser(user);
        return  call;
    }
    public Call<User> signUser( User user){

        Call<User> call = Handler.getInstance().getApi().SignInUser(user);
        return  call;
    }
    public Call<User> getUserData(String token){
//        Call<User> call = Handler.getInstance().getApi().getUserData(token);
//        return  call;
        Call<User> calltargetResponse = Handler.getInstance().getApi().getUserData("Bearer "+token);
       return  calltargetResponse;
    }
    public Call<List<Product>> getProduct(){

        Call<List<Product>> call = Handler.getInstance().getApi().getProduct();
        return  call;
    }

}
