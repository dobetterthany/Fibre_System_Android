package com.example.fibre_system_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Second_Recyclerview_Adapter extends RecyclerView.Adapter<Second_Recyclerview_Adapter.ViewHolder>{

    Context context;
    ArrayList<Recycler_item> itemsArrayListFull;
    private SelectItemListener1 listener1;

    private RecyclerView mainRecyclerView;

    public Second_Recyclerview_Adapter(Context context, ArrayList<Recycler_item> itemsArrayList, SelectItemListener1 listener1,RecyclerView mainRecyclerView) {
        this.context = context;
        this.itemsArrayListFull = itemsArrayList;
        this.listener1 = listener1;
        this.mainRecyclerView = mainRecyclerView;
    }



    @NonNull
    @Override
    public Second_Recyclerview_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_items_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Second_Recyclerview_Adapter.ViewHolder holder, int position) {
        holder.nameView.setText(itemsArrayListFull.get(position).getName());
        holder.sizeView.setText(itemsArrayListFull.get(position).getSizeString());
        holder.imageView.setImageResource(itemsArrayListFull.get(position).getImage());
        holder.imageView.setTag(itemsArrayListFull.get(position).getImage());

        holder.imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mainRecyclerView.requestDisallowInterceptTouchEvent(true);
                    view.requestFocus();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    mainRecyclerView.requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
    }



    @Override
    public int getItemCount() {
        return itemsArrayListFull.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nameView,sizeView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            nameView = itemView.findViewById(R.id.name);
            sizeView = itemView.findViewById(R.id.size) ;
        }
    }
}
