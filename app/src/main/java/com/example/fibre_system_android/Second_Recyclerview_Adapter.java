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
        holder.nameView.setText(itemsArrayListFull.get(position).getName());
        holder.sizeView.setText(itemsArrayListFull.get(position).getSizeString());
        holder.imageView.setImageResource(itemsArrayListFull.get(position).getImage());
        holder.imageView.setTag(itemsArrayListFull.get(position).getImage());

        holder.emptyview.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        listener1.onItemSelected(itemsArrayListFull.get(holder.getBindingAdapterPosition()));
                        break;
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
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
