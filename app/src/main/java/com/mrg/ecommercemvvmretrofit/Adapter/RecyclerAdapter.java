package com.mrg.ecommercemvvmretrofit.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrg.ecommercemvvmretrofit.Models.Product;
import com.mrg.ecommercemvvmretrofit.Fragment.ProductFragment;
import com.mrg.ecommercemvvmretrofit.Fragment.ProductPageByCategoryFragment;
import com.mrg.ecommercemvvmretrofit.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Product>mylists;
    private ProductFragment productFragment;
    private ProductPageByCategoryFragment productPageByCategoryFragment;
    private Activity activity;
    public RecyclerAdapter(List<Product> mylists, ProductFragment productFragment,Activity activity) {
        this.mylists = mylists;
        this.productFragment = productFragment;
        this.activity = activity;
    }
    public RecyclerAdapter(List<Product> mylists, ProductPageByCategoryFragment productPageByCategoryFragment, Activity activity) {
        this.mylists = mylists;
        this.productPageByCategoryFragment = productPageByCategoryFragment;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view =  inflater.inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(mylists.get(position).getTitle());
        holder.textView2.setText(mylists.get(position).getDescription());

            Glide.with(activity)
                    .load(mylists.get(position).getImages())
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into( holder.imageView);


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                productFragment.clickListener(mylists.get(position), view,R.id.item_container);

            }
        });
        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productFragment.clickListener(mylists.get(position), view,R.id.add_cart_btn);          }
        });
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                fragment.clickListener(mylists.get(position), view);
//                Log.d(TAG, "onClick: "+mylists.get(position).getImages()[0]);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mylists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button addBtn;
        private ImageView imageView;
        private TextView textView ,textView2;
        private LinearLayoutCompat linearLayout;
//        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
//            cardView = itemView.findViewById(R.id.constraint_item);
            linearLayout= itemView.findViewById(R.id.item_container);
            addBtn = itemView.findViewById(R.id.add_cart_btn);
        }
    }
    public void updateList( List<Product> newList) {
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
