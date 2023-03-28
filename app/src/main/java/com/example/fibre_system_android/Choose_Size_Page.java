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

     int Height;
     int Width;

    /* Context context; */

    EditText widthInput;
    EditText heightInput;
    Button confirmButton;
    Button nextButton;

    public void TestInit(int height, int width){
        this.Height = height;
        this.Width = width;
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
                intent.putExtra("InputWidth", Width);
                intent.putExtra("InputHeight", Height);
                startActivity(intent);
            }
        });

    }


    public void AddImage(int imageID)
    {
        Height = Integer.valueOf(heightInput.getText().toString());
        Width = Integer.valueOf(widthInput.getText().toString());

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        lParams.height = Height;
        lParams.width = Width;


        ImageView icon = (ImageView) findViewById(R.id.shapeImage);
        icon.setImageResource(imageID);
        icon.setLayoutParams(lParams);


    }

}