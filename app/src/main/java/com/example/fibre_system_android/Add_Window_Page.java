package com.example.fibre_system_android;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibre_system_android.planner_layout.BathroomPlannerLayout;

import java.util.ArrayList;

public class Add_Window_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SelectItemListener {
    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    ArrayList<String> name = new ArrayList<>();


    //Planner area layout variables
    ConstraintLayout AddwindowArea;
    BathroomPlannerLayout bathroomPlannerLayout;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide menu bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add_window_page);

        ImageView background = findViewById(R.id.shapeImage2);

        name.add("Luxury Frameless");
        name.add("LShape");
        name.add("Square");
        name.add("Entry Level Showers");


        //Planner area layout init
        AddwindowArea = findViewById(R.id.AddwindowArea);
        bathroomPlannerLayout = new BathroomPlannerLayout(this, AddwindowArea, background);


        Intent i = getIntent();
        int inputHeight = i.getIntExtra("InputHeight", 0);
        int inputWidth = i.getIntExtra("InputWidth", 0);

        nextButton = findViewById(R.id.AddWindowsPageNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(Add_Window_Page.this, Planner_Area_Page.class);
                intent.putExtra("BackgroundWidth", inputHeight);
                intent.putExtra("BackgroundHeight", inputWidth);
                startActivity(intent);
            }
        });
        setRecyclerView();
        makeResponsive();
        inheritImage(inputHeight, inputWidth);

    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.plannerItemList);
        Button nextButton = (Button) findViewById(R.id.AddWindowsPageNextButton);

        ViewGroup.LayoutParams recyclerViewParams = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
        recyclerViewParams.width = calcWidth(300);
        recyclerViewParams.height = calcHeight(440);

        ViewGroup.LayoutParams nextButtonParams = (ViewGroup.MarginLayoutParams) nextButton.getLayoutParams();
        nextButtonParams.width = calcWidth(130);
        nextButtonParams.height = calcHeight(70);
    }

    public int calcHeight(float value) {
        return (int) (dpHeight * (value / designHeight));
    }

    public int calcWidth(float value) {
        return (int) (dpWidth * (value / designWidth));
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.plannerItemList);

        ArrayList<Recycler_item> itemsArrayList = new ArrayList<>();
        itemsArrayList.add(new Recycler_item("LShape", 14, 1, R.drawable.small_square, true, true, true, false, ShowerRange.LSHAPE));
        itemsArrayList.add(new Recycler_item("LShape", 12, 1, R.drawable.small_square, true, true, true, false, ShowerRange.LSHAPE));
        itemsArrayList.add(new Recycler_item("LShape", 12, 9, R.drawable.small_square, true, true, true, false, ShowerRange.LSHAPE));
        itemsArrayList.add(new Recycler_item("LShape", 12, 8, R.drawable.small_square, true, true, true, false, ShowerRange.LSHAPE));
        itemsArrayList.add(new Recycler_item("Luxury Frameless", 14, 1, R.drawable.small_square, true, true, true, false, ShowerRange.LUXURY_FRAMELESS));
        itemsArrayList.add(new Recycler_item("Luxury Frameless", 12, 1, R.drawable.small_square, true, true, true, false, ShowerRange.LUXURY_FRAMELESS));
        itemsArrayList.add(new Recycler_item("Luxury Frameless", 12, 9, R.drawable.small_square, true, true, true, false, ShowerRange.LUXURY_FRAMELESS));
        itemsArrayList.add(new Recycler_item("Luxury Frameless", 12, 8, R.drawable.small_square, true, true, true, false, ShowerRange.LUXURY_FRAMELESS));
        itemsArrayList.add(new Recycler_item("Eline Round", 10, 10, R.drawable.square, true, false, false, false, ShowerRange.ELS));
        itemsArrayList.add(new Recycler_item("Eline Round", 9, 9, R.drawable.square, true, false, false, false, ShowerRange.ELS));
        itemsArrayList.add(new Recycler_item("Squareline", 9, 9, R.drawable.square, true, false, false, false, ShowerRange.ELS));
        itemsArrayList.add(new Recycler_item("Squareline", 10, 10, R.drawable.square, true, false, false, false, ShowerRange.ELS));
        itemsArrayList.add(new Recycler_item("Square", 1, 1, R.drawable.large_square, true, true, true, false, ShowerRange.SQUARE));
        itemsArrayList.add(new Recycler_item("Square", 9, 9, R.drawable.large_square , true, true, true, false, ShowerRange.SQUARE));
        itemsArrayList.add(new Recycler_item("Square", 9, 9, R.drawable.large_square , true, true, true, false, ShowerRange.SQUARE));
        itemsArrayList.add(new Recycler_item("Square", 9, 9, R.drawable.large_square , true, true, true, false, ShowerRange.SQUARE));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Main_RecyclerViewAdapter(name, itemsArrayList,this));
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();

//        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onItemSelected(Recycler_item item) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

        //Create image view in planner area layout
        bathroomPlannerLayout.AddItem(item);
    }

    public void inheritImage(int InputHeight, int InputWidth) {

        ConstraintLayout.LayoutParams lParams = new ConstraintLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);


        lParams.height = InputHeight;
        lParams.width = InputWidth;


        ImageView icon = (ImageView) findViewById(R.id.shapeImage2);
        icon.setImageResource(R.drawable.square);
        icon.setLayoutParams(lParams);

    }
    /*public void AddImage() {

        ConstraintLayout.LayoutParams lParams = new ConstraintLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);


        lParams.height = 500;
        lParams.width = 500;


        ImageView icon = (ImageView) findViewById(R.id.imageViewA);
        icon.setImageResource(R.drawable.toilet);
        icon.setLayoutParams(lParams);

    }*/

}
