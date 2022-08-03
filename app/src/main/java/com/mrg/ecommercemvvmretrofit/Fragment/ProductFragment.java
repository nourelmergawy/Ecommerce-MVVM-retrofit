package com.mrg.ecommercemvvmretrofit.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mrg.ecommercemvvmretrofit.Adapter.RecyclerAdapter;
import com.mrg.ecommercemvvmretrofit.EViewModel;
import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.R;
import com.mrg.ecommercemvvmretrofit.databinding.FragmentProductBinding;

import java.util.List;

public class ProductFragment extends Fragment {
    ProductFragment fragment;
    private FragmentProductBinding binding;
    private RecyclerAdapter adapter;
    private EViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        fragment =new ProductFragment();
        binding= FragmentProductBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(requireActivity()).get(EViewModel.class);
        viewModel.init();
        viewModel.getProduct(getActivity().getApplicationContext());
        setRecycleView();
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter = new RecyclerAdapter(products,fragment,getActivity());
                binding.recyclerview.setAdapter(adapter);
                adapter.updateList(products);
            }
        });
        return binding.getRoot();
    }
    public void setRecycleView(){
        RecyclerView recyclerView = binding.recyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));

    }
    public void clickListener(Product product,View view,int id){

       if (id == R.id.item_container){
           NavDirections  action=
                   ProductFragmentDirections.actionProductFragmentToProductPageFragment(product);
           Navigation.findNavController(view).navigate(action);

       }else if(id == R.id.add_cart_btn){
           NavDirections  action=
                   ProductFragmentDirections.actionProductFragmentToCartFragment(product);
           Navigation.findNavController(view).navigate(action);
       }
    }
}