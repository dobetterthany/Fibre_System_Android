package com.example.fibre_system_android;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView nameView,sizeView;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        nameView = itemView.findViewById(R.id.name);
        sizeView = itemView.findViewById(R.id.size) ;
    }
}
