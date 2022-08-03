package com.mrg.ecommercemvvmretrofit;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonArray;
import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.Models.User;
import com.mrg.ecommercemvvmretrofit.Ui.Home;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EViewModel extends ViewModel {
    public MutableLiveData<List<Product>> mutableLiveData = new MutableLiveData<>();
    private Repository repository;
    public void init(){
        if(mutableLiveData != null){
            return;
        }
        repository = Repository.getInstance();

    }
    public  void createUser(User user, Activity activity, Context context){
        Repository.getInstance().createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(context, "success", Toast.LENGTH_LONG).show();

                // we are getting response from our body
                // and passing it to our modal class.
                User responseFromAPI = new User();
                responseFromAPI =response.body();
                Intent intent = new Intent( context, Home.class);
                //To pass:
                intent.putExtra("userObj", responseFromAPI);
                Toast.makeText(context, "i'm done "+responseFromAPI.toString(), Toast.LENGTH_LONG).show();
                activity.startActivity(intent);


            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "fail", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: "+t);

            }
        });
    }
    public void getProduct(Context context){
        Repository.getInstance().getProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                mutableLiveData.setValue(response.body());
                List<Product> responseFromAPI = new ArrayList<Product>();
                responseFromAPI =response.body();
                Log.d(TAG, "onResponse: "+responseFromAPI.get(0).getCategory());
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t);
            }
        });
    }
    public void updateUi(){

    }
}
