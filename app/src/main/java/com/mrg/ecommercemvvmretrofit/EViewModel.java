package com.mrg.ecommercemvvmretrofit;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.gson.JsonArray;
import com.mrg.ecommercemvvmretrofit.Fragment.CartFragmentDirections;
import com.mrg.ecommercemvvmretrofit.Fragment.SignInFragmentDirections;
import com.mrg.ecommercemvvmretrofit.Fragment.SignUpFragmentDirections;
import com.mrg.ecommercemvvmretrofit.Models.Category;
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
    public MutableLiveData<List<Category>> categoryMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Product>> productByCategory = new MutableLiveData<>();

    private Repository repository;
    public void init(){
        if(mutableLiveData != null){
            return;
        }
        repository = Repository.getInstance();

    }
    public  void createUser(User user, Activity activity, Context context, View view){
        Repository.getInstance().createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(context, "success", Toast.LENGTH_LONG).show();

                // we are getting response from our body
                // and passing it to our modal class.
                User responseFromAPI = new User();
                responseFromAPI =response.body();
                Toast.makeText(context, "i'm done "+responseFromAPI.toString(), Toast.LENGTH_LONG).show();
                NavDirections action =
                        SignUpFragmentDirections.actionSignUpFragmentToHome2(responseFromAPI);
                Navigation.findNavController(view).navigate(action);


            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(context, "fail", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: "+t);

            }
        });
    }

    public  void getUserData(String token,View view){

        Repository.getInstance().getUserData(token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                User UserResponse = response.body();
                Log.d(TAG, "onResponse: "+UserResponse.getEmail());
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: done222");
                    Log.d(TAG, "onResponse: "+response.headers());
                    Log.d(TAG, "onResponse: "+response.message());
                    Log.d(TAG, "onClick-signFragment:");

                    NavDirections action=
                            SignInFragmentDirections.actionSignInFragmentToHome2(response.body());
                    Navigation.findNavController(view).navigate(action);
                }else {
                    Log.d(TAG, "onResponse: fail");
                }
//                Toast.makeText(co, " "+response.body(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Toast.makeText(this, "Failed ", Toast.LENGTH_SHORT).show();
            }
        });
//
        return ;
    }
    public  void signInUser(User user, Activity activity, Context context,View view){
        Repository.getInstance().signUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, "onResponse: done");
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.body().getAccess_token());
                    Log.d(TAG, "onResponse: "+response.body().toString());
                    Log.d(TAG, "onResponse: "+response.raw());
                    Toast.makeText(context,response.body().getAccess_token(),Toast.LENGTH_LONG).show();
                    getUserData(response.body().getAccess_token(),view);
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getProduct(Context context, ProgressBar bar){
        Repository.getInstance().getProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    bar.setVisibility(View.GONE);
                }
                mutableLiveData.setValue(response.body());
                List<Product> responseFromAPI = new ArrayList<Product>();
                responseFromAPI =response.body();
                Log.d(TAG, "onResponse: "+responseFromAPI.get(0).getCategory());

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "results: "+t);
            }
        });
    }
    public void getCategories(Context context){
        Repository.getInstance().getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.message());
                    Log.d(TAG, "category: "+response.body());
                    Log.d(TAG, "category  "+response.body().get(1).getName());
                    categoryMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }
    public void getProductsByCategory(int id){
        Repository.getInstance().getProductsByCategory(id).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "getProductsByCategory: " +response.body());
                    productByCategory.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
            }
        });
    }
}
