package com.example.fibre_system_android;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;



public class Choose_Size_Page extends AppCompatActivity {



    /* Context context; */

    EditText widthInput;
    EditText heightInput;
    Button confirmButton;
    Button nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_size_page);



        widthInput = findViewById(R.id.widthInput);
        heightInput = findViewById(R.id.heightInput);

        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddImage(R.drawable.square);
            }
        });

        nextButton = findViewById(R.id.goAddwindowButton);
        nextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent (Choose_Size_Page.this, Add_Window_Page.class);
                intent.putExtra("inputHeight",inputHeight);
                intent.putExtra("inputWidth",inputWidth);
                startActivity(intent);
            }
        });

    }

    int inputHeight = Integer.valueOf(heightInput.getText().toString());
    int inputWidth = Integer.valueOf(widthInput.getText().toString());


    public void AddImage(int imageID)
    {

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        lParams.height = inputHeight;
        lParams.width = inputWidth;

        // Change the parameter of shapeImage(choose size page)

        ImageView icon = findViewById(R.id.shapeImage);
        icon.setImageResource(imageID);
        icon.setLayoutParams(lParams);


    }



}