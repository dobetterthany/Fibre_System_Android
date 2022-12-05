package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Choose_Shape_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_shape_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void goChooseSizePage(View v) {
        //launch a new activity
        Intent i = new Intent(this, Choose_Size_Page.class);
        startActivity(i);
    }
}