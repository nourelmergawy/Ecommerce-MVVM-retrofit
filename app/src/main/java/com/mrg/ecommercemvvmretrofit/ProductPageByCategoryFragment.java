package com.mrg.ecommercemvvmretrofit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mrg.ecommercemvvmretrofit.Adapter.CategoryRecyclerAdapter;
import com.mrg.ecommercemvvmretrofit.Adapter.RecyclerAdapter;
import com.mrg.ecommercemvvmretrofit.Fragment.ProductFragment;
import com.mrg.ecommercemvvmretrofit.Fragment.ProductPageFragmentArgs;
import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.databinding.FragmentProductPageByCategoryBinding;

import java.util.List;

public class ProductPageByCategoryFragment extends Fragment {
    private FragmentProductPageByCategoryBinding binding;
    private RecyclerAdapter adapter;
    private EViewModel viewModel;
    private ProgressBar progressBar;
    private RecyclerView categoryRecyclerView;
    ProductPageByCategoryFragment fragment;
    private int id ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductPageByCategoryBinding.inflate(inflater);
        progressBar= binding.progressBar2;
        fragment = new ProductPageByCategoryFragment();
        viewModel = new ViewModelProvider(requireActivity()).get(EViewModel.class);
        viewModel.init();
        viewModel.getProduct(getActivity().getApplicationContext(),progressBar);
        setRecycleView();
        if(ProductPageByCategoryFragmentArgs.fromBundle(getArguments()).getId() > -1){
            id = ProductPageByCategoryFragmentArgs.fromBundle(getArguments()).getId();
        }
        viewModel.getProductsByCategory(1);

        viewModel.productByCategory.observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter = new RecyclerAdapter(products, fragment, getActivity());
                binding.recyclerview.setAdapter(adapter);
            }
        });

        return binding.getRoot();
    }
    public void setRecycleView(){
        RecyclerView recyclerView = binding.recyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));

    }
}