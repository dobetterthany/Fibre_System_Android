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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fibre_system_android.planner_layout.BathroomPlannerLayout;

import java.util.ArrayList;

public class Add_Window_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SelectItemListener {

    Spinner spinner;
    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;


    //Planner area layout variables
    ConstraintLayout AddwindowArea;
    BathroomPlannerLayout bathroomPlannerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide menu bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add_window_page);

        final ImageView imageView1 = findViewById(R.id.imageViewA);

        final ImageView imageView2 = findViewById(R.id.shapeImage2);



        imageView1.setOnTouchListener(new View.OnTouchListener() {

            float x, y;



            @SuppressLint("ClickableViewAccessibility")
            @Override

            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        // save the x and y coordinates of the touch

                        x = event.getX();

                        y = event.getY();

                        break;

                    case MotionEvent.ACTION_MOVE:

                        // get the new x and y coordinates of the touch

                        float newX = event.getX();

                        float newY = event.getY();

                        // get the dimensions of the imageviews

                        float itemWidth = imageView1.getWidth();

                        float itemHeight = imageView1.getHeight();

                        float boarderWidth = imageView2.getWidth();

                        float boarderHeight = imageView2.getHeight();

                        // calculate the new position of the imageview

                        float image1X = imageView1.getX() + newX - x;

                        float image1Y = imageView1.getY() + newY - y;



                        // make sure the imageview stays on the border of the other imageview

                        if (image1X < 0) {

                            image1X = 0;

                        }

                        if (image1X > (boarderWidth - itemWidth)) {

                            image1X = boarderWidth - itemWidth;

                        }

                        if (image1Y < 0) {

                            image1Y = 0;

                        }

                        if (image1Y > (boarderHeight - itemHeight)) {

                            image1Y = boarderHeight - itemHeight;

                        }
                        //Snapping logic

                        if(image1X > 0 && image1X < 100)
                        {
                            image1X = 0;
                        }

                        if(image1Y > 0 && image1Y < 100)
                        {
                            image1Y = 0;
                        }

                        if(image1X < boarderWidth && image1X > (boarderWidth - 100 - itemWidth))
                        {
                            image1X = boarderWidth - itemWidth;
                        }

                        if(image1Y < boarderHeight && image1Y > (boarderHeight - 100 - itemHeight))
                        {
                            image1Y = boarderHeight - itemHeight;
                        }

                        // set the new position of the imageview

                        imageView1.setX(image1X);

                        imageView1.setY(image1Y);

                        break;

                    case MotionEvent.ACTION_UP:

                        break;

                }

                return true;

            }

        });




        //Planner area layout init
        AddwindowArea = findViewById(R.id.AddwindowArea);
        bathroomPlannerLayout = new BathroomPlannerLayout(this, AddwindowArea);

        Intent i = getIntent();
        int inputHeight = i.getIntExtra("InputHeight", 0);
        int inputWidth = i.getIntExtra("InputWidth", 0);

        setSpinner();
        initSearchWidget();
        setRecyclerView();
        makeResponsive();
        inheritImage(inputHeight, inputWidth);
        AddImage();

    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        SearchView searchView = (SearchView) findViewById(R.id.productListSearchView);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.plannerItemList);
        Button finishButton = (Button) findViewById(R.id.button_planner_page_finish);

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
        RecyclerView recyclerView = findViewById(R.id.plannerItemList);

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
    public void onItemSelected(RecyclerViewItems item) {
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
    public void AddImage() {

        ConstraintLayout.LayoutParams lParams = new ConstraintLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);


        lParams.height = 500;
        lParams.width = 500;


        ImageView icon = (ImageView) findViewById(R.id.imageViewA);
        icon.setImageResource(R.drawable.toilet);
        icon.setLayoutParams(lParams);

    }


    /*public void snapping() {

        ConstraintLayout.LayoutParams lParams = new ConstraintLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.topToTop = R.id.shapeImage2;
        lParams.bottomToBottom = R.id.shapeImage2;
        lParams.leftToLeft = R.id.shapeImage2;
        lParams.rightToRight = R.id.shapeImage2;


        ImageView icon1 = (ImageView) findViewById(R.id.shapeImage2);
        icon1.setImageResource(R.drawable.toilet);// set Image resource to selected product.

    }
*/
}
