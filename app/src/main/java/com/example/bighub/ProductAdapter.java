package com.example.bighub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProductAdapter extends FirebaseRecyclerAdapter<Product, ProductAdapter.PastViewHolder> {

    private Context context;

    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options, Context context) {
        super(options);
        this.context = context;
    }


    @Override
    protected void onBindViewHolder(@NonNull PastViewHolder holder, final int i, @NonNull final Product product) {

      //  holder.id.setText(product.getId());
        holder.name.setText(product.getName());
        holder.title.setText(product.getTitle());
        holder.description.setText(product.getDescription());
        holder.price.setText("₹"+product.getPrice());
        Glide.with(context).load(product.getImageUrl()).into(holder.pdimg);






    }

    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content, parent, false);
        return new PastViewHolder(view);
    }

    class PastViewHolder extends RecyclerView.ViewHolder{

        TextView title,description,name,price,id;
        ImageView pdimg;


        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
           // id=itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            pdimg=itemView.findViewById(R.id.pdimg);

        }
    }
}

