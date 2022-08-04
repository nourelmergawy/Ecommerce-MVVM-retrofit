package com.mrg.ecommercemvvmretrofit.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrg.ecommercemvvmretrofit.EViewModel;
import com.mrg.ecommercemvvmretrofit.Fragment.ProductFragment;
import com.mrg.ecommercemvvmretrofit.Models.Category;
import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>{

    private List<Category>mylists;
    private List<Category>categoryList;
    private ProductFragment productFragment;
    private Activity activity;
    private EViewModel viewModel;
    public CategoryRecyclerAdapter(
            List<Category> mylists, ProductFragment productFragment, Activity activity,EViewModel viewModel) {
        this.mylists = mylists;
        this.productFragment = productFragment;
        this.activity = activity;
        this.categoryList = new ArrayList<>();
        this.viewModel = viewModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view =  inflater.inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(mylists.get(position).getName());
        Glide.with(activity)
                .load(mylists.get(position).getImage())
//               .error(R.drawable.mainicon)
                .placeholder(R.drawable.ic_launcher_background)
                .into( holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                productFragment.searchClickListener(mylists.get(position).getId(),viewModel,view);


            }
        });
    }

    @Override
    public int getItemCount() {
        return mylists.size();
    }

    public Filter categoryFilter(String charSequence) {

        charSequence = charSequence.toString().toLowerCase(Locale.getDefault());
        categoryList.clear();
        if (charSequence != null) {
            for (Category wp : mylists) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charSequence)) {
                    categoryList.add(wp);

                }
            }
        }
        return null;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView ;
        private LinearLayoutCompat linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            linearLayout= itemView.findViewById(R.id.item_container);
        }
    }
    public void updateList( List<Category> newList) {
        // on below line we are clearing
        // our notes array list
//        mylists.clear();
        // on below line we are adding a
        // new list to our all notes list.
        mylists.addAll(newList);
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged();
    }
}
