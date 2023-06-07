package com.example.fibre_system_android.planner_layout;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.fibre_system_android.R;
import com.example.fibre_system_android.Recycler_item;

import java.util.ArrayList;

public class BathroomPlannerLayout {

    //Layout where item views are placed
    ConstraintLayout plannerArea;

    //Current selected view
    ImageView selectedView;
    ImageView background;

    //Stores all items added to array
    ArrayList<Recycler_item> plannerItemArray;
    float dX = 0, dY = 0;

    //Context of activity this is created in
    Context context;

    EditButtons editButtons;

    float pScale = 0.5f;




    public BathroomPlannerLayout(Context context, ConstraintLayout plannerAreaLayout, ImageView background)
    {
        //Init variables
        this.context = context;
        this.plannerArea = plannerAreaLayout;
        plannerItemArray = new ArrayList<Recycler_item>();

        editButtons = new EditButtons(context, plannerAreaLayout, plannerItemArray);
        this.background = background;
    }


    public BathroomPlannerLayout(Context context, ConstraintLayout plannerAreaLayout, ImageView background, ArrayList<Recycler_item> doorsWindowsList)
    {
        //Init variables
        this.context = context;
        this.plannerArea = plannerAreaLayout;
        plannerItemArray = doorsWindowsList;

        editButtons = new EditButtons(context, plannerAreaLayout, plannerItemArray);
        this.background = background;

        for (Recycler_item item : doorsWindowsList)
        {
            RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            lParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

            ImageView icon = new ImageView(context);
            icon.setImageResource(item.getImage());
            icon.setLayoutParams(lParams);

            icon.setX(item.GetX());
            icon.setY(item.GetY());

            plannerAreaLayout.addView(icon);
        }
    }

    public ArrayList<Recycler_item> GetItemList()
    {
        return plannerItemArray;
    }

    //Add item to planner view
    public void AddItem(Recycler_item item)
    {
        deselectItem(selectedView);

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        lParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        ImageView icon = new ImageView(context);
        lParams.height = Math.round(item.getHeight() * pScale);
        lParams.width = Math.round(item.getLength() * pScale);
        icon.setImageResource(item.getImage());
        icon.setLayoutParams(lParams);



        //Dragging selected item listener
        icon.setOnTouchListener(new View.OnTouchListener() {

            float x, y;

            @SuppressLint("ClickableViewAccessibility")
            @Override

            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        // save the x and y coordinates of the touch

                        x = event.getX();

                        y = event.getY();

                        break;

                    case MotionEvent.ACTION_MOVE:

                        // get the new x and y coordinates of the touch

                        float newX = event.getX();

                        float newY = event.getY();

                        // get the dimensions of the imageviews

                        float itemWidth = icon.getWidth();

                        float itemHeight = icon.getHeight();

                        float boarderWidth = background.getWidth();

                        float boarderHeight = background.getHeight();

                        // calculate the new position of the imageview

                        float image1X = icon.getX() + newX - x;

                        float image1Y = icon.getY() + newY - y;



                        // make sure the imageview stays on the border of the other imageview

                        if (image1X < 0) {

                            image1X = 0;

                        }

                        if (image1X > (boarderWidth - itemWidth)) {

                            image1X = boarderWidth - itemWidth;

                        }

                        if (image1Y < 0) {

                            image1Y = 0;

                        }

                        if (image1Y > (boarderHeight - itemHeight)) {

                            image1Y = boarderHeight - itemHeight;

                        }
                        //Snapping logic

                        if(image1X > 0 && image1X < 100)
                        {
                            image1X = 0;
                        }

                        if(image1Y > 0 && image1Y < 100)
                        {
                            image1Y = 0;
                        }

                        if(image1X < boarderWidth && image1X > (boarderWidth - 100 - itemWidth))
                        {
                            image1X = boarderWidth - itemWidth;
                        }

                        if(image1Y < boarderHeight && image1Y > (boarderHeight - 100 - itemHeight))
                        {
                            image1Y = boarderHeight - itemHeight;
                        }

                        // set the new position of the imageview

                        icon.setX(image1X);

                        icon.setY(image1Y);

                        item.SetPos(image1X, image1Y);

                        break;

                    case MotionEvent.ACTION_UP:

                        break;

                }

                return true;

            }

        });

        plannerArea.addView(icon);
        plannerItemArray.add(item);
    }

    //Set selected item
    private void selectItem(ImageView imageView, Recycler_item item)
    {
        selectedView = imageView;
        selectedView.setColorFilter(ContextCompat.getColor(context, R.color.planner_selected_object));
        editButtons.viewSelected(imageView, item);
        editButtons.update(imageView);
    }

    //Deselect item
    private void deselectItem(ImageView imageView)
    {
        //imageView.setColorFilter(Color.argb(255,0,0,1));

        if(imageView != null) {
            imageView.clearColorFilter();
            editButtons.viewDeselected();
        }
    }


}
