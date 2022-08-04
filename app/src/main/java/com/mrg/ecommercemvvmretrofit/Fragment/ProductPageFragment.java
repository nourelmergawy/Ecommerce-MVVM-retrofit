package com.mrg.ecommercemvvmretrofit.Fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.R;
import com.mrg.ecommercemvvmretrofit.databinding.FragmentProductPageBinding;

public class ProductPageFragment extends Fragment {

    private FragmentProductPageBinding binding;
    private ImageView productImage;
    private TextView productTittle;
    private TextView productCategory;
    private TextView productPrice;
    private TextView productDescription;
    private Button addBtn;
    private Product data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductPageBinding.inflate(inflater);
        data = ProductPageFragmentArgs.fromBundle(getArguments()).getGetProduct();
        productImage =binding.productImage;
        productTittle = binding.productTittle;
        productCategory = binding.productCategory;
        productPrice = binding.productPrice;
        productDescription = binding.productDescription;
        addBtn =binding.addCartBtn;
        setupViews();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavDirections action =
                        ProductPageFragmentDirections.actionProductPageFragmentToCartFragment(data);
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
        productCategory.setText(data.getCategory().getName());
        productPrice.setText(String.valueOf(data.getPrice()));
        productDescription.setText(data.getDescription());
    }
}