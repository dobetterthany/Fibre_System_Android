package com.example.fibre_system_android;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements Filterable{

    Context context;
    ArrayList<RecyclerViewItems> itemsArrayList;
    ArrayList<RecyclerViewItems> itemsArrayListFull;
    private SelectItemListener listener;

    RelativeLayout plannerLayout;

    public RecyclerViewAdapter(Context context, ArrayList<RecyclerViewItems> itemsArrayList, SelectItemListener listener) {
        this.context = context;
        this.itemsArrayListFull = itemsArrayList;
        this.itemsArrayList = new ArrayList<>(itemsArrayListFull);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.nameView.setText(itemsArrayListFull.get(position).getName());
        holder.sizeView.setText(itemsArrayListFull.get(position).getSizeString());
        holder.imageView.setImageResource(itemsArrayListFull.get(position).getImage());
        holder.imageView.setTag(itemsArrayListFull.get(position).getImage());

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
        return itemsArrayListFull.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                ArrayList<RecyclerViewItems> filteredItemsList = new ArrayList<>();


                if (charString == null || charString.length() == 0){
                    filteredItemsList.addAll(itemsArrayListFull);
                } else {


                    for(RecyclerViewItems recyclerViewItems : itemsArrayListFull){
                        if(recyclerViewItems.name.toLowerCase().contains(charString)){
                            filteredItemsList.add(recyclerViewItems);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredItemsList;
                results.count = filteredItemsList.size();
                return results;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemsArrayList.clear();
                itemsArrayList.addAll((ArrayList)results.values);
                notifyDataSetChanged();
            }
        };
    }
}

