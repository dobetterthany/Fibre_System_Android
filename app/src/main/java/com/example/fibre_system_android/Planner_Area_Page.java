package com.example.fibre_system_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibre_system_android.planner_layout.BathroomPlannerLayout;

import java.util.ArrayList;

public class Planner_Area_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SelectItemListener {

    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    //Planner area layout variables
    ConstraintLayout plannerArea;
    BathroomPlannerLayout bathroomPlannerLayout;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Recycler_item> itemsArrayList;
    RecyclerView recyclerView;

    Main_RecyclerViewAdapter adapter;
    ArrayList<String> name = new ArrayList<>();
    Button finishButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide menu bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_planner_area);
        recyclerView = findViewById(R.id.plannerItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Planner area layout init
        plannerArea = findViewById(R.id.plannerArea);
        bathroomPlannerLayout = new BathroomPlannerLayout(this, plannerArea);
        itemsArrayList  = new ArrayList<>();

        finishButton = findViewById(R.id.button_planner_page_finish);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Planner_Area_Page.this, Finalize_Page.class);
                i.putExtra("planner_item_array", bathroomPlannerLayout.GetItemList());
                startActivity(i);
            }
        });

        initSearchWidget();
        makeResponsive();
        getData();

        adapter = new Main_RecyclerViewAdapter(name, itemsArrayList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        SearchView searchView = (SearchView) findViewById(R.id.productListSearchView);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.plannerItemList);
        Button finishButton = (Button) findViewById(R.id.button_planner_page_finish);

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

    //Data for item list
    private void getData() {
        itemsArrayList.add(new Recycler_item("Luxury Frameless", 14, 1, R.drawable.small_square, true, true, true, false));
        itemsArrayList.add(new Recycler_item("Luxury Frameless", 12, 1, R.drawable.small_square, true, true, true, false));
        itemsArrayList.add(new Recycler_item("Luxury Frameless", 12, 9, R.drawable.small_square, true, true, true, false));
        itemsArrayList.add(new Recycler_item("Luxury Frameless", 12, 8, R.drawable.small_square, true, true, true, false));
        itemsArrayList.add(new Recycler_item("Eline Round", 10, 10, R.drawable.square, true, false, false, false));
        itemsArrayList.add(new Recycler_item("Eline Round", 9, 9, R.drawable.square, true, false, false, false));
        itemsArrayList.add(new Recycler_item("Squareline", 9, 9, R.drawable.square, true, false, false, false));
        itemsArrayList.add(new Recycler_item("Squareline", 10, 10, R.drawable.square, true, false, false, false));
        itemsArrayList.add(new Recycler_item("Square", 1, 1, R.drawable.large_square, true, true, true, false));
        itemsArrayList.add(new Recycler_item("Square", 9, 9, R.drawable.large_square , true, true, true, false));
        itemsArrayList.add(new Recycler_item("Square", 9, 9, R.drawable.large_square , true, true, true, false));
        itemsArrayList.add(new Recycler_item("Square", 9, 9, R.drawable.large_square , true, true, true, false));

        name.add("서울");
        name.add("부산");
        name.add("경기");
        name.add("포항");
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

    //Item list on click event
    @Override
    public void onItemSelected(RecyclerViewItems item) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

        bathroomPlannerLayout.AddItem(item);
    }
}