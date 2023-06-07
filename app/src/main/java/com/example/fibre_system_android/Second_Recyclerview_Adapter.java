package com.example.fibre_system_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Vector;

public class Second_Recyclerview_Adapter extends RecyclerView.Adapter<Second_Recyclerview_Adapter.ViewHolder>{

    Context context;
    ArrayList<Recycler_item> itemsArrayListFull;
    private SelectItemListener listener1;


    private boolean isScrolling = false;

    Vector<Point> points = new Vector<Point>();

    Bitmap[] monsterTypes = new Bitmap[3];

    Vector<Integer> distanceMovedX = new Vector<Integer>();
    Vector<Integer> distanceMovedY = new Vector<Integer>();

    int mNewX = -1;
    int mNewY = -1;

    public Second_Recyclerview_Adapter(Context context, ArrayList<Recycler_item> itemsArrayList, SelectItemListener listener1) {
        this.context = context;
        this.itemsArrayListFull = itemsArrayList;
        this.listener1 = listener1;
    }

    @NonNull
    @Override
    public Second_Recyclerview_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.second_recyclerview_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Second_Recyclerview_Adapter.ViewHolder holder, int position) {
        Recycler_item item = itemsArrayListFull.get(position);
        holder.nameView.setText(item.getName());
        holder.sizeView.setText(item.getSizeString());
        holder.imageView.setImageResource(item.getImage());

        holder.emptyview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int adapterPosition = holder.getBindingAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onItemTouch(adapterPosition);
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
        View emptyview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            nameView = itemView.findViewById(R.id.name);
            sizeView = itemView.findViewById(R.id.size) ;
            emptyview = itemView.findViewById(R.id.emptyview);
        }
    }
}
