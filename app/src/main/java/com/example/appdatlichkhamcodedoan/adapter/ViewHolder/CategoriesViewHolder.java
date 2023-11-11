package com.example.appdatlichkhamcodedoan.adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appdatlichkhamcodedoan.R;

public class CategoriesViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNameCategories;
    public ImageView imgUpdateCategories;
    public CategoriesViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNameCategories = itemView.findViewById(R.id.tvNameCategories);
        imgUpdateCategories = itemView.findViewById(R.id.imgUpdateCategories);
    }
}
