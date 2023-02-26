package com.example.fibre_system_android.planner_layout;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fibre_system_android.R;
import com.example.fibre_system_android.Vector2;

import java.util.ArrayList;

public class EditButtons {
    String TAG = "editButtons";
    Context context;
    RelativeLayout plannerAreaLayout;

    ArrayList<ImageButton> buttonArray;

    ImageButton rotateLeft, rotateRight, delete;
    TextView degCounter;

    View selectedView;
    float selectedViewX, selectedViewY, selectedViewW, selectedViewH;
    float dX = 0, dY = 0;
    float newX, newY;
    float radius = 400; //Radius of buttons from target view
    float selectedCenterX, selectedCenterY;

    //Rotation
    float angle;

    EditButtons(Context context, RelativeLayout plannerAreaLayout) {
        this.plannerAreaLayout = plannerAreaLayout;
        this.context = context;
        buttonArray = new ArrayList<>();
        initControls();

    }

    private void initControls() {

        rotateLeft = new ImageButton(context);
        initButton(rotateLeft, R.drawable.small_square, -90);
        rotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedView.setRotation(selectedView.getRotation() - 90);
            }
        });

        rotateRight = new ImageButton(context);
        initButton(rotateRight, R.drawable.small_square, 90);
        rotateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedView.setRotation(selectedView.getRotation() + 90);
            }
        });

        delete = new ImageButton(context);
        initButton(delete, R.drawable.small_square, 0);
        delete.setColorFilter(R.color.purple_200);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //TODO Delete selected image view method
            }
        });

        viewDeselected();
    }

    //On tap code to go here, eg, info for selected item (name)
    public void viewSelected(View view){
        updatePos();

        for (ImageButton imageButton: buttonArray){
            imageButton.bringToFront();
            imageButton.setVisibility(View.VISIBLE);
        }

        selectedView = view;
    }

    public void viewDeselected()
    {
        for (ImageButton view: buttonArray
             ) {
            view.setVisibility(View.GONE);
        }
    }

    public void update(View view)
    {
        selectedViewX = view.getX();
        selectedViewY = view.getY();
        selectedViewW = view.getWidth();
        selectedViewH = view.getHeight();

        selectedCenterX = selectedViewX + (selectedViewW / 2);
        selectedCenterY = selectedViewY + (selectedViewH / 2);
        updatePos();
    }

    private void updatePos() {

        setAngleFromSelectedViewDeg(rotateLeft, -90);
        setAngleFromSelectedViewDeg(rotateRight, 90);
        setAngleFromSelectedViewDeg(delete, 180);
    }

    private void setAngleFromSelectedViewRad(View view, float angleRad)
    {
        if(angleRad == 0)
        {
            float angle = 0;
        }
        else{
            float angle = (float)Math.PI / angleRad;
        }
        newX = (float) (selectedCenterX + radius * Math.sin(angle));
        newY = (float) (selectedCenterY + radius * Math.cos(angle));

        view.animate()
                .x(newX - view.getWidth() / 2)
                .y(newY- view.getHeight() / 2)
                .setDuration(0)
                .start();
    }

    private void setAngleFromSelectedViewDeg(View view, float deg)
    {
        float angle = (float)Math.toRadians(deg);
        newX = (float) (selectedCenterX + radius * Math.sin(angle));
        newY = (float) (selectedCenterY + radius * Math.cos(angle));

        view.animate()
                .x(newX - view.getWidth() / 2)
                .y(newY- view.getHeight() / 2)
                .setDuration(0)
                .start();
    }

    private void initButton(ImageButton imageButton, int image, int angle)
    {
        imageButton.setImageResource(image);
        imageButton.setRotation(angle);
        imageButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        plannerAreaLayout.addView(imageButton);
        buttonArray.add(imageButton);
    }
}
