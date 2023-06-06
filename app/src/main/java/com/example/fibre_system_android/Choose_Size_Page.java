package com.example.fibre_system_android;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class Choose_Size_Page extends AppCompatActivity {

     int height;
     int width;

     public final static int minWidth = 1000;
     public final static int minHeight = 1000;

     public final static int maxWidth = 5000;
     public final static int maxHeight = 5000;

    /* Context context; */

    EditText widthInput;
    EditText heightInput;
    Button confirmButton;
    Button nextButton;



    //unit test function
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

//        ConstraintLayout.LayoutParams lParams = new ConstraintLayout.LayoutParams
//                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
//
//        lParams.height = height;
//        lParams.width = width;

       /* lParams.bottomToBottom = R.id.AddwindowArea;
        lParams.topToTop = R.id.AddwindowArea;
        lParams.leftToLeft = R.id.AddwindowArea;
        lParams.rightToRight = R.id.AddwindowArea;*/

        ImageView icon = (ImageView) findViewById(R.id.shapeImage);
        icon.setScaleX(width/icon.getWidth());
        icon.setScaleY(height/icon.getHeight());
//        icon.setImageResource(imageID);
//        icon.setLayoutParams(lParams);
    }

    public boolean setSize(int height, int width){
        if(height<minHeight||width<minWidth){
            return false;
        }
        if(height>maxHeight||width>maxWidth){
            return false;
        }
        return true;
    }

}