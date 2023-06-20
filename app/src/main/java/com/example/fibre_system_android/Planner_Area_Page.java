package com.example.fibre_system_android;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    ArrayList<Recycler_item> itemsArrayList;
    ArrayList<ShowerRange> categories;
    RecyclerView recyclerView;
    ImageView background;
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
        background = findViewById(R.id.shapeImage3);
        itemsArrayList  = new ArrayList<>();
        categories = new ArrayList<>();

        Intent intent = getIntent();
        int isSkipSize = intent.getIntExtra("SkipChooseSize", 1);

        if(isSkipSize == 1){//Skip choose size = true
            AddDefaultImage();
            bathroomPlannerLayout = new BathroomPlannerLayout(this, plannerArea, background);
        }
        else
        {//Skip choose size = false
            int inputHeight = intent.getIntExtra("BackgroundHeight", 0);
            int inputWidth = intent.getIntExtra("BackgroundWidth", 0);
            ArrayList<Recycler_item> WindowDoorItems = (ArrayList<Recycler_item>) intent.getSerializableExtra("WindowDoorItems");
            bathroomPlannerLayout = new BathroomPlannerLayout(this, plannerArea, background, WindowDoorItems);
            inheritImage(inputWidth,inputHeight );
        }

        finishButton = findViewById(R.id.button_planner_page_finish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Planner_Area_Page.this, Finalize_Page.class);
                i.putExtra("planner_item_array", bathroomPlannerLayout.GetItemList());
                startActivity(i);
            }
        });

//      initSearchWidget();
        makeResponsive();
        getData();

        adapter = new Main_RecyclerViewAdapter(name, itemsArrayList,categories,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.plannerItemList);
        Button finishButton = (Button) findViewById(R.id.button_planner_page_finish);

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
        itemsArrayList.add(new Recycler_item("Luxury 12 x 1", 1200, 1000, R.drawable.ps12x1, true, true, false, ShowerRange.LSHAPE));
        itemsArrayList.add(new Recycler_item("Luxury 12 x 9", 1200, 900, R.drawable.ps12x9, true, true, false, ShowerRange.LSHAPE));
        itemsArrayList.add(new Recycler_item("Luxury 12 x 8", 1200, 800, R.drawable.ps12x8, true, true, false, ShowerRange.LSHAPE));
        itemsArrayList.add(new Recycler_item("Luxury 14 x 1", 1200, 800, R.drawable.ps14x1, true, true, false, ShowerRange.LSHAPE));

        itemsArrayList.add(new Recycler_item("Luxury Frameless 12 x 1", 1200, 1000, R.drawable.ps12x1, true, true, false, ShowerRange.LUXURY_FRAMELESS));
        itemsArrayList.add(new Recycler_item("Luxury Frameless 12 x 9", 1200, 900, R.drawable.ps12x9, true, true, false, ShowerRange.LUXURY_FRAMELESS));
        itemsArrayList.add(new Recycler_item("Luxury Frameless 12 x 8", 1200, 800, R.drawable.ps12x8, true, true, false, ShowerRange.LUXURY_FRAMELESS));
        
        itemsArrayList.add(new Recycler_item("Squareline 900", 900, 900, R.drawable.ps9x9, true, false, true, ShowerRange.ELS));
        itemsArrayList.add(new Recycler_item("Squareline 1000", 1000, 1000, R.drawable.ps1x1, true, false, true, ShowerRange.ELS));

        itemsArrayList.add(new Recycler_item("1000 Square", 1000, 1000, R.drawable.ps1x1, true, true, true, ShowerRange.SQUARE));
        itemsArrayList.add(new Recycler_item("900 Square", 900, 900, R.drawable.ps9x9 , true, true, true, ShowerRange.SQUARE));

        itemsArrayList.add(new Recycler_item("Bath 1525", 1525, 760, R.drawable.ps_bath1525, ShowerRange.OTHER));
        itemsArrayList.add(new Recycler_item("Bath 1675", 1675, 760, R.drawable.ps_bath1675, ShowerRange.OTHER));
        itemsArrayList.add(new Recycler_item("Bath 1800", 1800, 760, R.drawable.ps_bath1800, ShowerRange.OTHER));

        name.add("Luxury Frameless");
        name.add("LShape");
        name.add("Entry Level Showers");
        name.add("Square");
        name.add("Other");

        categories.add(ShowerRange.LUXURY_FRAMELESS);
        categories.add(ShowerRange.LSHAPE);
        categories.add(ShowerRange.ELS);
        categories.add(ShowerRange.SQUARE);
        categories.add(ShowerRange.OTHER);

        itemsArrayList.add(new Recycler_item("Door860", 860, R.drawable.door860, ShowerRange.DOOR));

        itemsArrayList.add(new Recycler_item("Window", 600, R.drawable.window600, ShowerRange.WINDOW));
        itemsArrayList.add(new Recycler_item("Window", 800, R.drawable.window800, ShowerRange.WINDOW));
        itemsArrayList.add(new Recycler_item("Window", 1000, R.drawable.window1000, ShowerRange.WINDOW));

        name.add("Doors");
        name.add("Windows");
        //name.add("Walls");

        categories.add(ShowerRange.DOOR);
        categories.add(ShowerRange.WINDOW);
        //categories.add(ShowerRange.WALL);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();

     Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


    //Item list on click event
    @Override
    public void onItemSelected(Recycler_item item) {
        bathroomPlannerLayout.AddItem(item);
    }

    public void inheritImage(int InputHeight, int InputWidth) {

        ConstraintLayout.LayoutParams lParams = new ConstraintLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.height = InputHeight;
        lParams.width = InputWidth;


        background.setImageResource(R.drawable.bg2x2);
        background.setLayoutParams(lParams);
    }


    public void AddDefaultImage()
    {
        //TODO: Somehow make it scale from choose_size_page's scale.
        int height = 680;
        int width = 700;

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        lParams.height = height;
        lParams.width = width;

        background.setImageResource(R.drawable.bg2x2);
        background.setLayoutParams(lParams);
    }
}