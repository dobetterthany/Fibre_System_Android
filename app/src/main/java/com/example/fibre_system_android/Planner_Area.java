package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.DisplayMetrics;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Planner_Area extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SelectItemListener {

    Spinner spinner;
    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    ImageView selectedView;
    ArrayList<ImageView> plannerItemArray;
    float dX = 0, dY = 0;
    RelativeLayout plannerArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner_area);

        plannerArea = findViewById(R.id.plannerArea);
        plannerItemArray = new ArrayList<ImageView>();
        setSpinner();
        initSearchWidget();
        setRecyclerView();
        makeResponsive();
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

        List<RecyclerViewItems> items = new ArrayList<RecyclerViewItems>();
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


        AddImage((int) imageView.getTag());
    }

    //Add image to planner view
    private void AddImage(int tag)
    {
        deselectAll();

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        lParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        ImageView icon = new ImageView(this);
        icon.setImageResource(tag);
        icon.setLayoutParams(lParams);


        icon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        deselectAll();
                        selectItem((ImageView) view);

                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        view.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;

                    default:
                        return false;
                }
                return true;
            }
       } );
       // plannerItemArray.add(icon);
        plannerArea.addView(icon);
    }

    //Deselects view from planner area when selecting an item from the recycler view
    private void deselectAll() {
        final int childCount = plannerArea.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = plannerArea.getChildAt(i);

            if(v instanceof ImageView)
            {
                deselectItem((ImageView) v);
            }
        }
    }

    private void selectItem(ImageView imageView)
    {
        selectedView = imageView;
        selectedView.setColorFilter(Color.argb(255,255,0,0));
        //TODO: Code to move item edit buttons(rotation, delete, etc)
    }

    private void deselectItem(ImageView imageView)
    {
        imageView.setColorFilter(Color.argb(255,0,0,1));
    }
}