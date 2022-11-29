package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Planning_Method_Selection_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_method_selection_page);
    }

    public void goChooseShapePage(View v) {
        //launch a new activity
        Intent i = new Intent(this, Choose_Shape_Page.class);
        startActivity(i);
    }


}