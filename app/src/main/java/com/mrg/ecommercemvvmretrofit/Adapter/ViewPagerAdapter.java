package com.mrg.ecommercemvvmretrofit.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mrg.ecommercemvvmretrofit.Fragment.DashBoardFragment;
import com.mrg.ecommercemvvmretrofit.Fragment.CartFragment;

public class ViewPagerAdapter  extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new DashBoardFragment();
        }
        else if (position == 1)
        {
            fragment = new DashBoardFragment();
        }
        else if (position == 2)
        {
            fragment = new CartFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "DashBoard";
        }
        else if (position == 1)
        {
            title = "Product";
        }
        else if (position == 2)
        {
            title = "Cart";
        }
        return title;
    }
}