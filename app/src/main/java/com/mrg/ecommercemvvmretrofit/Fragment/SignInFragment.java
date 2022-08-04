package com.mrg.ecommercemvvmretrofit.Fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mrg.ecommercemvvmretrofit.EViewModel;
import com.mrg.ecommercemvvmretrofit.Models.User;
import com.mrg.ecommercemvvmretrofit.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {
    private FragmentSignInBinding binding;
    private EditText etEmail ;
    private EditText etpassword ;
    private Button signInBtn ;
    private Button signUpBtn ;
    private EViewModel viewModel;
    private User userData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =FragmentSignInBinding.inflate(inflater);
        //binding views
        etEmail = binding.emailItem;
        etpassword = binding.passwordItem;
        signInBtn = binding.signinBtn;
        signUpBtn =binding.signupBtn;
        viewModel = new ViewModelProvider(requireActivity()).get(EViewModel.class);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etpassword.getText().toString();
                viewModel.signInUser(new User(email,password),getActivity(),getActivity().getApplicationContext(),view);

            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action=
                        SignInFragmentDirections.actionSignInFragmentToSignUpFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
        return binding.getRoot();
    }
}