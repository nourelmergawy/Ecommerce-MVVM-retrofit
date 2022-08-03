package com.mrg.ecommercemvvmretrofit.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mrg.ecommercemvvmretrofit.EViewModel;
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
  // To retrieve object in second Activity
//        User user = (User) getIntent().getSerializableExtra("userObj");
//        Log.d(TAG, "onCreate: "+user.getId());

    }
    public void setupButtomBar(){
        BottomNavigationView navView =(BottomNavigationView) findViewById(R.id.nav_bottom_menu);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.dashBoardFragment, R.id.productFragment, R.id.cartFragment)
                .build();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentView);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }
}