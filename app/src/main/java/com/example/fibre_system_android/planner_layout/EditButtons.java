package com.example.fibre_system_android.planner_layout;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.example.fibre_system_android.R;
import com.example.fibre_system_android.RecyclerViewItems;

import java.util.ArrayList;

/*
Shows a radial menu of buttons a view is passed in.
These buttons change the views orientation removes the view from its layout.
 */
public class EditButtons {
    String TAG = "editButtons";
    Context context;
    RelativeLayout plannerAreaLayout;

    ArrayList<ImageButton> buttonArray;
    ArrayList<RecyclerViewItems> plannerItemArray;
    ImageButton rotateLeft, rotateRight, delete;

    View selectedView;
    RecyclerViewItems selectedItem;
    float selectedViewX, selectedViewY, selectedViewW, selectedViewH;

    float newX, newY;
    float radius = 400; //Radius of buttons from target view
    float selectedCenterX, selectedCenterY;

    //Rotation
    float angle;

    EditButtons(Context context, RelativeLayout plannerAreaLayout,  ArrayList<RecyclerViewItems> plannerItemArray) {
        this.plannerAreaLayout = plannerAreaLayout;
        this.context = context;
        this.plannerItemArray = plannerItemArray;
        buttonArray = new ArrayList<>();
        initControls();

    }

    private void initControls() {

        rotateLeft = new ImageButton(context);
        initButton(rotateLeft, R.drawable.small_square, -90, R.color.edit_rotate);
        rotateLeft.setOnClickListener(view -> selectedView.setRotation(selectedView.getRotation() - 90));

        rotateRight = new ImageButton(context);
        initButton(rotateRight, R.drawable.small_square, 90, R.color.edit_rotate);
        rotateRight.setOnClickListener(view -> selectedView.setRotation(selectedView.getRotation() + 90));

        delete = new ImageButton(context);
        initButton(delete, R.drawable.small_square, 0, R.color.edit_delete);
        delete.setOnClickListener(view -> {
            plannerAreaLayout.removeView(selectedView);
            plannerItemArray.remove(selectedItem);
            viewDeselected();
        });

        viewDeselected();
    }

    //On tap code to go here, eg, info for selected item (name)
    public void viewSelected(View view, RecyclerViewItems item){
        selectedView = view;
        selectedItem = item;
        updatePos();

        for (ImageButton imageButton: buttonArray){
            imageButton.bringToFront();
            imageButton.setVisibility(View.VISIBLE);
        }

    }

    public void viewDeselected()
    {
        for (ImageButton view: buttonArray
             ) {
            view.setVisibility(View.GONE);
        }
    }

    //Updates the position of the buttons
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

    //Set the angle of a button in relation to the selected view in radians
    private void setAngleFromSelectedViewRad(View view, float angleRad)
    {
        float angle;
        if(angleRad == 0)
        {
            angle = 0;
        }
        else{
            angle = (float)Math.PI / angleRad;
        }
        newX = (float) (selectedCenterX + radius * Math.sin(angle));
        newY = (float) (selectedCenterY + radius * Math.cos(angle));

        view.animate()
                .x(newX - view.getWidth() / 2)
                .y(newY- view.getHeight() / 2)
                .setDuration(0)
                .start();
    }

    //Set the angle of a button in relation to the selected view in degrees
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

    //Initialises an image button
    private void initButton(ImageButton imageButton, int image, int angle, int tintColour)
    {
        imageButton.setImageResource(image);
        imageButton.setRotation(angle);
        imageButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageButton.setColorFilter(ContextCompat.getColor(context, tintColour));

        plannerAreaLayout.addView(imageButton);
        buttonArray.add(imageButton);
    }

}
