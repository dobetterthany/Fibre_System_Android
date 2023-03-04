package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import com.example.fibre_system_android.planner_layout.BathroomPlannerLayout;

import java.util.ArrayList;
import java.util.List;

public class Planner_Area_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SelectItemListener {

    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    //Planner area layout variables
    RelativeLayout plannerArea;
    BathroomPlannerLayout bathroomPlannerLayout;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<RecyclerViewItems> itemsArrayList;
    RecyclerView recyclerView;
    Button button = (Button) findViewById(R.id.buttonSubmit);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner_area);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Planner area layout init
        plannerArea = findViewById(R.id.plannerArea);
        bathroomPlannerLayout = new BathroomPlannerLayout(this, plannerArea);
        itemsArrayList  = new ArrayList<RecyclerViewItems>();

        initSearchWidget();
        makeResponsive();
        getData();

    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        SearchView searchView = (SearchView) findViewById(R.id.productListSearchView);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Button finishButton = (Button) findViewById(R.id.button3);

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

//    public void filterData() {
//
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(String userText){
//                recyclerViewAdapter.getFilter().filter(userText);
//            }
//        });
//    }

    private void getData() {
        itemsArrayList.add(new RecyclerViewItems("large shower", 9, 9, R.drawable.small_square, true, true, true, false));
        itemsArrayList.add(new RecyclerViewItems("shower", 9, 9, R.drawable.square, false, true, false, false));
        itemsArrayList.add(new RecyclerViewItems("small shower", 9, 9, R.drawable.large_square, false, false, true, false));

        itemsArrayList.add(new RecyclerViewItems("toilet", 9, 9, R.drawable.toilet));

        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), itemsArrayList, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }
    //set searchview on the menubar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search );
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search Here!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String userText) {
                recyclerViewAdapter.getFilter().filter(userText);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String userText) {
//                recyclerViewAdapter.getFilter().filter(userText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
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
            public boolean onQueryTextChange(String userText) {
                recyclerViewAdapter.getFilter().filter(userText);
                return true;
            }
        });
    }

    @Override
    public void onItemSelected(RecyclerViewItems item) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

        //Create image view in planner area layout
        bathroomPlannerLayout.AddItem(item);
    }
}