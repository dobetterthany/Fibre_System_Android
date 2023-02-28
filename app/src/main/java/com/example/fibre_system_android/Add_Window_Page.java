package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ActionBar;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.content.Intent;

import com.example.fibre_system_android.planner_layout.BathroomPlannerLayout;

import java.util.ArrayList;
import java.util.List;

public class Add_Window_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SelectItemListener {

    Spinner spinner;
    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;



    //Planner area layout variables
    RelativeLayout AddwindowArea;
    BathroomPlannerLayout bathroomPlannerLayout;

    Intent intent = getIntent();
    int inputHeight = intent.getIntExtra("inputHeight",0);
    int inputWidth = intent.getIntExtra("inputWidth",0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_window_page);


        //Planner area layout init
        AddwindowArea = findViewById(R.id.AddwindowArea);
        bathroomPlannerLayout = new BathroomPlannerLayout(this, AddwindowArea);

        //AddImage(R.drawable.square);


        setSpinner();
        initSearchWidget();
        setRecyclerView();
        makeResponsive();
        AddImage(R.drawable.square);



    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        SearchView searchView = (SearchView) findViewById(R.id.productListSearchView);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Button finishButton = (Button) findViewById(R.id.button3);

        ViewGroup.LayoutParams spinnerParams = (ViewGroup.MarginLayoutParams) spinner.getLayoutParams();
        spinnerParams.width = calcWidth(300);
        spinnerParams.height = calcHeight(60);

        ViewGroup.LayoutParams searchViewParams = (ViewGroup.MarginLayoutParams) searchView.getLayoutParams();
        searchViewParams.width = calcWidth(300);
        searchViewParams.height = calcHeight(60);

        ViewGroup.LayoutParams recyclerViewParams = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
        recyclerViewParams.width = calcWidth(300);
        recyclerViewParams.height = calcHeight(440);

        ViewGroup.LayoutParams finishButtonParams = (ViewGroup.MarginLayoutParams) finishButton.getLayoutParams();
        finishButtonParams.width = calcWidth(130);
        finishButtonParams.height = calcHeight(70);
    }

    public int calcHeight(float value) {
        return (int) (dpHeight * (value / designHeight));
    }

    public int calcWidth(float value) {
        return (int) (dpWidth * (value / designWidth));
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ArrayList<RecyclerViewItems> items = new ArrayList<RecyclerViewItems>();
        items.add(new RecyclerViewItems("toilet", 9, 9, R.drawable.toilet));
        items.add(new RecyclerViewItems("toilet", 9, 9, R.drawable.toilet));
        items.add(new RecyclerViewItems("toilet", 9, 9, R.drawable.toilet));
        items.add(new RecyclerViewItems("toilet", 9, 9, R.drawable.toilet));
        items.add(new RecyclerViewItems("toilet", 9, 9, R.drawable.toilet));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(getApplicationContext(), items, this));
    }

    private void setSpinner() {
        spinner = findViewById(R.id.spinner);
        //gets items from products array in string file, and plug it into spinner item list
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.products, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();

//        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void initSearchWidget() {
        SearchView searchView = (SearchView) findViewById(R.id.productListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void onItemSelected(ImageView imageView) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

        //Create image view in planner area layout
        bathroomPlannerLayout.AddImage((int) imageView.getTag());
    }


    public void AddImage(int imageID)
    {


        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        lParams.height = inputHeight;
        lParams.width = inputWidth;

        // Change the parameter of shapeImage(choose size page)

        ImageView icon = findViewById(R.id.shapeImage2);
        icon.setImageResource(imageID);
        icon.setLayoutParams(lParams);


    }


}