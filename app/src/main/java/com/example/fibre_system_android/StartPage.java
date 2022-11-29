package com.example.fibre_system_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;

public class StartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goNextPage(View v) {
        //launch a new activity
        Intent i = new Intent(this, Planning_Method_Selection_Page.class);
        startActivity(i);
    }
}