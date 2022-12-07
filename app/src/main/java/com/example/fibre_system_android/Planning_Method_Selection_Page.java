package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Planning_Method_Selection_Page extends AppCompatActivity {

    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_method_selection_page);
        makeResponsive();
    }

    public void goChooseShapePage(View v) {
        //launch a new activity
        Intent i = new Intent(this, Choose_Shape_Page.class);
        startActivity(i);


    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        Button planningButton = (Button) findViewById(R.id.button2);
        Button chooseSizeButton = (Button) findViewById(R.id.button4);


        ViewGroup.LayoutParams planningButtonParams = (ViewGroup.MarginLayoutParams) planningButton.getLayoutParams();
        planningButtonParams.height = calcHeight(690);
        planningButtonParams.width = calcWidth(530);

        ViewGroup.LayoutParams chooseSizeParams = (ViewGroup.MarginLayoutParams) chooseSizeButton.getLayoutParams();
        chooseSizeParams.height = calcHeight(690);
        chooseSizeParams.width = calcWidth(530);
    }

    public int calcHeight (float value) {
        return (int) (dpHeight * (value/designHeight));
    }

    public int calcWidth(float value) {
        return (int) (dpWidth * (value/designWidth));
    }
}