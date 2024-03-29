package com.example.fibre_system_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Finalize_Page extends AppCompatActivity{

    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    RelativeLayout plannerArea;
    FinalizeRecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Recycler_item> itemsArrayList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide menu bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_finalize);
        recyclerView = findViewById(R.id.finalizeItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemsArrayList  = new ArrayList<Recycler_item>();

        makeResponsive();
        getData();
    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.finalizeItemList);

        ViewGroup.LayoutParams recyclerViewParams = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
        recyclerViewParams.width = calcWidth(recyclerView.getWidth());
        recyclerViewParams.height = calcHeight(recyclerView.getHeight());
    }

    public int calcHeight(float value) {
        return (int) (dpHeight * (value / designHeight));
    }

    public int calcWidth(float value) {
        return (int) (dpWidth * (value / designWidth));
    }

    private void getData() {
        Intent i = getIntent();

        for(Recycler_item item : (ArrayList<Recycler_item>)i.getSerializableExtra("planner_item_array"))
        {
            if(!item.isGeneric)
            {
                itemsArrayList.add(item);
            }
        }

        if (!itemsArrayList.isEmpty())
        {
            TextView text = findViewById(R.id.finalEmptyText);
            text.setEnabled(false);
        }
        recyclerViewAdapter = new FinalizeRecyclerViewAdapter(getApplicationContext(), itemsArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}