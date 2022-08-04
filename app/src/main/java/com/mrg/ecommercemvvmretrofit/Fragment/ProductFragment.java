package com.mrg.ecommercemvvmretrofit.Fragment;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.mrg.ecommercemvvmretrofit.Adapter.CategoryRecyclerAdapter;
import com.mrg.ecommercemvvmretrofit.Adapter.RecyclerAdapter;
import com.mrg.ecommercemvvmretrofit.EViewModel;
import com.mrg.ecommercemvvmretrofit.Models.Category;
import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.R;
import com.mrg.ecommercemvvmretrofit.Ui.Home;
import com.mrg.ecommercemvvmretrofit.databinding.FragmentProductBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductFragment extends Fragment {
    ProductFragment fragment;
    private FragmentProductBinding binding;
    private RecyclerAdapter adapter;
    private EViewModel viewModel;
    private ProgressBar progressBar;
    private SearchView searchView;
    private CategoryRecyclerAdapter categoryRecyclerAdapter;
    private List<Category>categoryList;
    private List<Category>categoryList2;
    private RecyclerView categoryRecyclerView;
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
        progressBar= binding.progressBar2;
        searchView = binding.searchView;
        viewModel = new ViewModelProvider(requireActivity()).get(EViewModel.class);
        viewModel.init();
        viewModel.getProduct(getActivity().getApplicationContext(),progressBar);
        setRecycleView();
        setCategoryRecycleView();
        categoryList = new ArrayList<>();
        categoryList2 = new ArrayList<>();
        viewModel.mutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter = new RecyclerAdapter(products,fragment,getActivity());
                binding.recyclerview.setAdapter(adapter);
//                adapter.updateList(products);
            }
        });
        viewModel.getCategories(getContext().getApplicationContext());
        // attach setOnQueryTextListener
        // to search view defined above
        viewModel.categoryMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryRecyclerAdapter = new CategoryRecyclerAdapter(categories, fragment, getActivity(),viewModel);
                binding.categoryRecyclerview.setAdapter(categoryRecyclerAdapter);
//                categoryRecyclerAdapter.updateList(categories);

            }
        });

        return binding.getRoot();
    }
    public void setRecycleView(){
        RecyclerView recyclerView = binding.recyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));

    }
    public void setCategoryRecycleView(){
        categoryRecyclerView = binding.categoryRecyclerview;
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
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

    public void searchClickListener(int id,EViewModel viewModel,View view){
        Log.d(TAG, "searchClickListener: "+id);
        NavDirections  action=
                ProductFragmentDirections.actionProductFragmentToProductPageByCategoryFragment(id);
        Navigation.findNavController(view).navigate(action);
    }
}