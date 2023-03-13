package com.example.fibre_system_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> implements Filterable{

    Context context;
    ArrayList<RecyclerViewItems> filteredItemsArrayList;
    ArrayList<RecyclerViewItems> itemsArrayListFull;
    private SelectItemListener listener;

    public RecyclerViewAdapter(Context context, ArrayList<RecyclerViewItems> itemsArrayList, SelectItemListener listener) {
        this.context = context;
        this.itemsArrayListFull = itemsArrayList;
        this.filteredItemsArrayList = new ArrayList<>(itemsArrayListFull);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.nameView.setText(filteredItemsArrayList.get(position).getName());
        holder.sizeView.setText(filteredItemsArrayList.get(position).getSizeString());
        holder.imageView.setImageResource(filteredItemsArrayList.get(position).getImage());
        holder.imageView.setTag(filteredItemsArrayList.get(position).getImage());

        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                listener.onItemSelected(filteredItemsArrayList.get(holder.getBindingAdapterPosition()));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredItemsArrayList.size();
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
                        if(recyclerViewItems.name.toLowerCase().replace(" ","").contains(charString)){
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
                filteredItemsArrayList.clear();
                filteredItemsArrayList.addAll((ArrayList)results.values);
                notifyDataSetChanged();
            }
        };


    }
}

