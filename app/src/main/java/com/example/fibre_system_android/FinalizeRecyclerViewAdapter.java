package com.example.fibre_system_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FinalizeRecyclerViewAdapter extends RecyclerView.Adapter<FinalizeRecyclerViewHolder>{

    Context context;
    ArrayList<Recycler_item> itemsArrayListFull;

    public FinalizeRecyclerViewAdapter(Context context, ArrayList<Recycler_item> itemsArrayList) {
        this.context = context;
        this.itemsArrayListFull = new ArrayList<>(itemsArrayList);
    }

    @NonNull
    @Override
    public FinalizeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FinalizeRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.finalize_items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FinalizeRecyclerViewHolder holder, int position) {
        holder.nameView.setText(itemsArrayListFull.get(position).getName());
        holder.sizeView.setText(itemsArrayListFull.get(position).getSizeString());
        holder.imageView.setImageResource(itemsArrayListFull.get(position).getImage());
        holder.imageView.setTag(itemsArrayListFull.get(position).getImage());
        List<String> Options = new ArrayList<>();
        for(Map.Entry<String, Boolean> entry : itemsArrayListFull.get(position).colours.entrySet()) {
            String key = entry.getKey();
            Boolean value = entry.getValue();

            if(value)
            {
                Options.add(key);
            }
        }
        if(Options.isEmpty() || Options.size() == 1) //If size is <= 1 disable dropdown
        {
            Options.add("No colour available");
            holder.colourDropdown.setEnabled(false);
        }

        initializeUnitSpinner(holder.colourDropdown, Options);
    }

    @Override
    public int getItemCount() {
        return itemsArrayListFull.size();
    }

    private void initializeUnitSpinner(Spinner spinnerUnit, List<String> unit_list) {
        ArrayAdapter<String> unitArrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, unit_list);
        unitArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(unitArrayAdapter);
    }
}

