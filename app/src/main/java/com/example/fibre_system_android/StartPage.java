package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

public class StartPage extends AppCompatActivity {

    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeResponsive();
    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        Button startButton = (Button) findViewById(R.id.button);

        ViewGroup.LayoutParams buttonParams = (ViewGroup.MarginLayoutParams) startButton.getLayoutParams();
        buttonParams.height = calcHeight(644);
        buttonParams.width = calcWidth(1052);
    }

    public int calcHeight (float value) {
        return (int) (dpHeight * (value/designHeight));
    }

    public int calcWidth(float value) {
        return (int) (dpWidth * (value/designWidth));
    }

    public void goNextPage(View v) {
        //launch a new activity
        Intent i = new Intent(this, Planning_Method_Selection_Page.class);
        startActivity(i);
    }
}