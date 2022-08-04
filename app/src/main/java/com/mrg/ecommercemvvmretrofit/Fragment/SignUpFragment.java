package com.mrg.ecommercemvvmretrofit.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mrg.ecommercemvvmretrofit.EViewModel;
import com.mrg.ecommercemvvmretrofit.Models.User;
import com.mrg.ecommercemvvmretrofit.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;
    private EditText etEmail ;
    private EditText etpassword ;
    private EditText etusername ;
    private Button signUpBtn ;
    private EViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater);
        //binding views
        etEmail = binding.emailItem;
        etpassword = binding.passwordItem;
        signUpBtn = binding.signupBtn;
        etusername = binding.userItem;
        String imgURI =  "https://www.kindpng.com/picc/m/52-526237_avatar-profile-hd-png-download.png";
        viewModel = new ViewModelProvider(requireActivity()).get(EViewModel.class);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etpassword.getText().toString();
                String username = etusername.getText().toString();
                viewModel.createUser(new User(email,username,password,imgURI),getActivity(),getActivity().getApplicationContext(),view);
            }
        }) ;
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}