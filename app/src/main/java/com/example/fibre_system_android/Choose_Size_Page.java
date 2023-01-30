package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Choose_Size_Page extends AppCompatActivity {

    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_size_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        makeResponsive();
    }
    public void goPlannerPage(View v) {
        //launch a new activity
        Intent i = new Intent(this, Planner_Area_Page.class);
        startActivity(i);
    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        Button button1 = (Button) findViewById(R.id.button5);
        Button button2 = (Button) findViewById(R.id.button6);
        Button button3 = (Button) findViewById(R.id.button7);
        Button button4 = (Button) findViewById(R.id.button8);

        ViewGroup.LayoutParams button1Params = (ViewGroup.MarginLayoutParams) button1.getLayoutParams();
        button1Params.width = calcWidth(300);
        button1Params.height = calcHeight(150);

        ViewGroup.LayoutParams button2Params = (ViewGroup.MarginLayoutParams) button2.getLayoutParams();
        button2Params.width = calcWidth(300);
        button2Params.height = calcHeight(150);

        ViewGroup.LayoutParams button3Params = (ViewGroup.MarginLayoutParams) button3.getLayoutParams();
        button3Params.width = calcWidth(300);
        button3Params.height = calcHeight(150);

        ViewGroup.LayoutParams button4Params = (ViewGroup.MarginLayoutParams) button4.getLayoutParams();
        button4Params.width = calcWidth(300);
        button4Params.height = calcHeight(150);
    }

    public int calcHeight (float value) {
        return (int) (dpHeight * (value/designHeight));
    }

    public int calcWidth(float value) {
        return (int) (dpWidth * (value/designWidth));
    }
}