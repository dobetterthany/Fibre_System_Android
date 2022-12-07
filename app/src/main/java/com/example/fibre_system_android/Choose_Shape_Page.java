package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Choose_Shape_Page extends AppCompatActivity {

    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_shape_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        makeResponsive();


    }

    public void goChooseSizePage(View v) {
        //launch a new activity
        Intent i = new Intent(this, Choose_Size_Page.class);
        startActivity(i);
    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        Button buttonAlcove = (Button) findViewById(R.id.button11);
        Button buttonSquare = (Button) findViewById(R.id.button13);
        Button buttonRound = (Button) findViewById(R.id.button14);
        Button buttonAngle = (Button) findViewById(R.id.button15);
        Button buttonBath = (Button) findViewById(R.id.button16);
        Button buttonWalkIn = (Button) findViewById(R.id.button17);


        ViewGroup.LayoutParams buttonAlcoveParams = (ViewGroup.MarginLayoutParams) buttonAlcove.getLayoutParams();
        buttonAlcoveParams.width = calcWidth(276);
        buttonAlcoveParams.height = calcHeight(421);

        ViewGroup.LayoutParams buttonSquareParams = (ViewGroup.MarginLayoutParams) buttonSquare.getLayoutParams();
        buttonSquareParams.width = calcWidth(276);
        buttonSquareParams.height = calcHeight(421);

        ViewGroup.LayoutParams buttonRoundParams = (ViewGroup.MarginLayoutParams) buttonRound.getLayoutParams();
        buttonRoundParams.width = calcWidth(276);
        buttonRoundParams.height = calcHeight(421);

        ViewGroup.LayoutParams buttonAngleParams = (ViewGroup.MarginLayoutParams) buttonAngle.getLayoutParams();
        buttonAngleParams.width = calcWidth(276);
        buttonAngleParams.height = calcHeight(421);

        ViewGroup.LayoutParams buttonBathParams = (ViewGroup.MarginLayoutParams) buttonBath.getLayoutParams();
        buttonBathParams.width = calcWidth(550);
        buttonBathParams.height = calcHeight(111);

        ViewGroup.LayoutParams buttonWalkInParams = (ViewGroup.MarginLayoutParams) buttonWalkIn.getLayoutParams();
        buttonWalkInParams.width = calcWidth(550);
        buttonWalkInParams.height = calcHeight(111);
    }

    public int calcHeight (float value) {
        return (int) (dpHeight * (value/designHeight));
    }

    public int calcWidth(float value) {
        return (int) (dpWidth * (value/designWidth));
    }
}