package com.mrg.ecommercemvvmretrofit.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mrg.ecommercemvvmretrofit.Models.User;
import com.mrg.ecommercemvvmretrofit.R;
import com.mrg.ecommercemvvmretrofit.databinding.FragmentDashBoardBinding;

public class DashBoardFragment extends Fragment {
    private FragmentDashBoardBinding binding;
    private ImageView profileImage;
    private EditText username;
    private EditText email;
    private User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDashBoardBinding.inflate(inflater);
        profileImage = binding.profileImage;
        username = binding.editTextTextPersonName;
        email =binding.editTextTextEmailAddress;
            user = DashBoardFragmentArgs.fromBundle(getArguments()).getGetUserData();
            setUserData();
        return binding.getRoot();
    }
    public void setUserData(){
        Glide.with(getActivity())
                .load(user.getAvatar())
//               .error(R.drawable.mainicon)
                .placeholder(R.drawable.ic_launcher_background)
                .into(profileImage);
        username.setText(user.getName());
        email.setText(user.getEmail());
    }
}