package com.example.testapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Choose_Size_Page extends AppCompatActivity {

    private int Height;
    private int Width;

    private int designWidth = 1280;
    private int designHeight = 800;

    EditText widthInput;
    EditText heightInput;
    Button confirmButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_size);

        widthInput = (EditText) findViewById(R.id.widthInput);
        heightInput = (EditText) findViewById(R.id.heightInput);

        confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Height = Integer.valueOf(heightInput.getText().toString());
                Width = Integer.valueOf(widthInput.getText().toString());
            }


        });
    }

    public void AddImage(int imageID)
    {

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        lParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        ImageView icon = new ImageView(context);
        icon.setImageResource(imageID);
        icon.setLayoutParams(lParams);
    }

    public void goPlannerPage(View v) {
        //launch a new activity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}