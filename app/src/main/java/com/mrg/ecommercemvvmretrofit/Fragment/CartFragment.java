package com.mrg.ecommercemvvmretrofit.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.R;
import com.mrg.ecommercemvvmretrofit.databinding.FragmentCartBinding;
import com.mrg.ecommercemvvmretrofit.databinding.FragmentProductBinding;

public class CartFragment extends Fragment {
    private Product data;
    private ImageView productImage;
    private TextView productTittle;
    private TextView productPrice;
    private TextView subtotal;
    private TextView total;

    private Button confirmBtn;

    private FragmentCartBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater);
        data = ProductPageFragmentArgs.fromBundle(getArguments()).getGetProduct();
        productImage =binding.imageView2;
        productTittle = binding.tittle;
        productPrice = binding.price;
        confirmBtn =binding.button;
        subtotal = binding.subtotal;
        total =binding.total;
        setupViews();
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action =
                        CartFragmentDirections.actionCartFragmentToFinalFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });
        return binding.getRoot();
    }
    public void setupViews(){
        Glide.with(getActivity())
                .load(data.getImages()[0])
//               .error(R.drawable.mainicon)
                .placeholder(R.drawable.ic_launcher_background)
                .into( productImage);

        productTittle.setText(data.getTitle());
        productPrice.setText(String.valueOf(data.getPrice()));
        subtotal.setText(String.valueOf(data.getPrice()));
        total.setText(String.valueOf(data.getPrice()));
    }
}