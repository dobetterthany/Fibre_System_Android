package com.example.fibre_system_android;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    Context context;
    List<RecyclerViewItems> items;
    private SelectItemListener listener;

    RelativeLayout plannerLayout;

    public RecyclerViewAdapter(Context context, List<RecyclerViewItems> items, SelectItemListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.nameView.setText(items.get(position).getName());
        holder.sizeView.setText(items.get(position).getSize());
        holder.imageView.setImageResource(items.get(position).getImage());

        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                listener.onItemSelected(holder.imageView);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
