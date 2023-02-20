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

    private int Height;
    private int Width;

    /* Context context; */

    EditText widthInput;
    EditText heightInput;
    Button confirmButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_size_page);

        widthInput = (EditText) findViewById(R.id.widthInput);
        heightInput = (EditText) findViewById(R.id.heightInput);

        confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddImage(R.drawable.toilet);
                goAddwindowPage();
            }
        });
    }

    public void AddImage(int imageID)
    {
        Height = Integer.valueOf(heightInput.getText().toString());
        Width = Integer.valueOf(widthInput.getText().toString());

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        lParams.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        lParams.addRule(RelativeLayout.ALIGN_BOTTOM);
        lParams.bottomMargin = 100;
        lParams.height = Height;
        lParams.width = Width;




        ImageView icon = (ImageView) findViewById(R.id.AddwindowimageView);
        icon.setImageResource(imageID);
        icon.setLayoutParams(lParams);
    }

    public void goAddwindowPage() {
        //launch a new activity， goes to
        Intent i = new Intent(this, Planner_Area_Page.class);
        startActivity(i);
    }


}