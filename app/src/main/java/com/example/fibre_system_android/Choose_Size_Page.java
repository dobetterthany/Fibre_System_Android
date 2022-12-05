package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Choose_Size_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_size_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void goPlannerPage(View v) {
        //launch a new activity
        Intent i = new Intent(this, Planner_Area.class);
        startActivity(i);
    }
}