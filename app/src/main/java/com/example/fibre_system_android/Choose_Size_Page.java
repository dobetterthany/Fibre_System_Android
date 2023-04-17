package com.example.fibre_system_android;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;



public class Choose_Size_Page extends AppCompatActivity {

     int height;
     int width;

     int minWidth;
     int minHeight;
     int maxWidth;
     int maxHeight;

    /* Context context; */

    EditText widthInput;
    EditText heightInput;
    Button confirmButton;
    Button nextButton;

    Button testButton;

    public void TestInit(int height, int width){
        this.height = height;
        this.width = width;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide menu bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_choose_size_page);

        widthInput = findViewById(R.id.widthInput);
        heightInput = findViewById(R.id.heightInput);
        testButton = findViewById(R.id.testButton);
        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddImage(R.drawable.square);
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Height = Integer.valueOf(heightInput.getText().toString());
                Width = Integer.valueOf(widthInput.getText().toString());
            }
        });


        nextButton = findViewById(R.id.goAddwindowButton);
        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent (Choose_Size_Page.this, Add_Window_Page.class);
                intent.putExtra("InputWidth", width);
                intent.putExtra("InputHeight", height);
                startActivity(intent);
            }
        });

    }


    public void AddImage(int imageID)
    {
        height = Integer.valueOf(heightInput.getText().toString());
        width = Integer.valueOf(widthInput.getText().toString());

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        lParams.height = height;
        lParams.width = width;


        ImageView icon = (ImageView) findViewById(R.id.shapeImage);
        icon.setImageResource(imageID);
        icon.setLayoutParams(lParams);


    }

    public boolean setSize(int height, int width){
        
        return true;
    }

}