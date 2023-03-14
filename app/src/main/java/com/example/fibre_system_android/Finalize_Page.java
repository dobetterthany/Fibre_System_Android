package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Finalize_Page extends AppCompatActivity{

    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    RelativeLayout plannerArea;
    FinalizeRecyclerViewAdapter recyclerViewAdapter;
    ArrayList<RecyclerViewItems> itemsArrayList;
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

        itemsArrayList  = new ArrayList<RecyclerViewItems>();

        makeResponsive();
        getData();

        Intent i = getIntent();
        itemsArrayList = (ArrayList<RecyclerViewItems>)i.getSerializableExtra("planner_item_array");
    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.finalizeItemList);

        ViewGroup.LayoutParams recyclerViewParams = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
//        recyclerViewParams.width = calcWidth(300);
//        recyclerViewParams.height = calcHeight(440);
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
        itemsArrayList = (ArrayList<RecyclerViewItems>)i.getSerializableExtra("planner_item_array");
        recyclerViewAdapter = new FinalizeRecyclerViewAdapter(getApplicationContext(), itemsArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}