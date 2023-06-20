package com.example.fibre_system_android;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


public class Choose_Size_Page extends AppCompatActivity {

    String TAG = "Choose_Size_Page";
     int height;
     int width;

     final static float BGScale = 0.25f;

// 760mm is the smallest shower size available (as of 07/06/2023 10:54AM)
     public final static int minWidth = 760;
     public final static int minHeight = 760;

     public final static int maxWidth = 2900;
     public final static int maxHeight = 2900;

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

        nextButton = findViewById(R.id.goAddwindowButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(TextUtils.isEmpty(heightInput.getText().toString().trim()) ||
                        TextUtils.isEmpty(widthInput.getText().toString().trim()))
                {
                    Toast.makeText(getApplicationContext(), "Height and width fields cannot empty", Toast.LENGTH_SHORT).show();
                    return; //If text fields are empty, return
                }

                height = Integer.valueOf(heightInput.getText().toString());
                width = Integer.valueOf(widthInput.getText().toString());
                if(CheckInput()) { //If input doesn't meet requirements, skip
                    AddImage(R.drawable.bg2x2);

                    Intent intent = new Intent (Choose_Size_Page.this, Add_Window_Page.class);
                    intent.putExtra("InputWidth", Math.round(width * BGScale));
                    intent.putExtra("InputHeight", Math.round(height * BGScale));
                    startActivity(intent);
                }
            }
        });
    }

    public void AddImage(int imageID)
    {
        ConstraintLayout.LayoutParams lParams = new ConstraintLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.height = Math.round(height * BGScale);
        lParams.width = Math.round(width * BGScale);

        ImageView icon = (ImageView) findViewById(R.id.shapeImage);
        Log.d(TAG, "AddImage Height: " + height + "Width: " + width);
        icon.setImageResource(imageID);
        icon.setLayoutParams(lParams);
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

    private boolean CheckInput() {

        if (height < minHeight) {
            Toast.makeText(getApplicationContext(), "Height should be between " + minHeight + " and " + maxHeight, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (height > maxHeight) {
            Toast.makeText(getApplicationContext(), "Height should be between " + minHeight + " and " + maxHeight, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (width < minWidth) {
            Toast.makeText(getApplicationContext(), "Width should be between " + minWidth + " and " + maxWidth, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (width > maxWidth) {
            Toast.makeText(getApplicationContext(), "Width should be between " + minWidth + " and " + maxWidth, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}