package com.example.fibre_system_android;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
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
    String TAG = "Add_Window_Page";
    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    int inputHeight;
    int inputWidth;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<ShowerRange> categories = new ArrayList<>();
    ArrayList<Recycler_item> itemsArrayList;
    //Planner area layout variables
    ConstraintLayout AddWindowArea;
    BathroomPlannerLayout bathroomPlannerLayout;
    Button nextButton;
    ImageView background;
    Main_RecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide menu bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add_window_page);
        recyclerView = findViewById(R.id.plannerItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent i = getIntent();
        inputHeight = i.getIntExtra("InputHeight", 0);
        inputWidth = i.getIntExtra("InputWidth", 0);

        nextButton = findViewById(R.id.AddWindowsPageNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(Add_Window_Page.this, Planner_Area_Page.class);
                intent.putExtra("BackgroundWidth", inputHeight);
                intent.putExtra("BackgroundHeight", inputWidth);
                intent.putExtra("WindowDoorItems", bathroomPlannerLayout.GetItemList());
                intent.putExtra("SkipChooseSize", 0);
                startActivity(intent);
            }
        });

        AddWindowArea = findViewById(R.id.AddWindowArea);
        background = findViewById(R.id.shapeImage2);
        background.setImageResource(R.drawable.bg2x2);

        itemsArrayList = new ArrayList<>();

        //Planner area layout init

        bathroomPlannerLayout = new BathroomPlannerLayout(this, AddWindowArea, background);

        setRecyclerView();
        makeResponsive();
        inheritImage(inputHeight, inputWidth);

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
//        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onItemSelected(Recycler_item item) {
        bathroomPlannerLayout.AddItem(item);
    }

    public void inheritImage(int InputHeight, int InputWidth) {

        ConstraintLayout.LayoutParams lParams = new ConstraintLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.height = InputHeight;
        lParams.width = InputWidth;

        background.setLayoutParams(lParams);

    }
}
