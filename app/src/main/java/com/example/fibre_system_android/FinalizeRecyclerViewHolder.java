package com.example.fibre_system_android;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibre_system_android.R;

public class FinalizeRecyclerViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView nameView;
    TextView sizeView;
    Spinner colourDropdown;

    public FinalizeRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview_finalize);
        nameView = itemView.findViewById(R.id.name_finalize);
        sizeView = itemView.findViewById(R.id.size_finalize);
        colourDropdown = itemView.findViewById(R.id.spinner_colour);
    }
}
