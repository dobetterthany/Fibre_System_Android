package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StartPage extends AppCompatActivity {

    private int dpHeight;
    private int dpWidth;
    private float dDensity;
    private int designWidth = 1280;
    private int designHeight = 800;

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide menu bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextPage();
            }
        });
        makeResponsive();
    }

    private void makeResponsive() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        dpHeight = (displayMetrics.heightPixels);
        dpWidth = (displayMetrics.widthPixels);
        dDensity = (displayMetrics.scaledDensity);

        Button startButton = (Button) findViewById(R.id.start_button);

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

    public void goNextPage() {
        //launch a new activity
        Intent i = new Intent(this, Planning_Method_Selection_Page.class);
        startActivity(i);
    }
}