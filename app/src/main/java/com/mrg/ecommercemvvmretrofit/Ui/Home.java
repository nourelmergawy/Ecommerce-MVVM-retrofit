package com.mrg.ecommercemvvmretrofit.Ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mrg.ecommercemvvmretrofit.EViewModel;
import com.mrg.ecommercemvvmretrofit.Fragment.CartFragmentDirections;
import com.mrg.ecommercemvvmretrofit.Fragment.DashBoardFragmentArgs;
import com.mrg.ecommercemvvmretrofit.Fragment.SignInFragmentDirections;
import com.mrg.ecommercemvvmretrofit.HomeNavGraphDirections;
import com.mrg.ecommercemvvmretrofit.Models.User;
import com.mrg.ecommercemvvmretrofit.R;
import com.mrg.ecommercemvvmretrofit.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity {
    private ActivityHomeBinding binding;
    EViewModel viewModel;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    //    TabLayout tabLayout;
//    ViewPager viewPager;
//    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(EViewModel.class);
        viewModel.init();
        setupButtomBar();
//   To retrieve object in second Activity


    }
    public void setupButtomBar(){

        BottomNavigationView navView =(BottomNavigationView) findViewById(R.id.nav_bottom_menu);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.dashBoardFragment, R.id.productFragment, R.id.cartFragment)
                .build();
        navView.setSelectedItemId(R.id.productFragment);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentView);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        User user= DashBoardFragmentArgs.fromBundle(getIntent().getExtras()).getGetUserData();

        navController.navigate(
                SignInFragmentDirections.actionGlobalDashBoardFragment(user));
    }

//            />
}