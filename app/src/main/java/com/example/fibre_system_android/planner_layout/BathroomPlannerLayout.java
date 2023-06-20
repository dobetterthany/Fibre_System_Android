package com.example.fibre_system_android.planner_layout;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
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

    float itemScale = 3/16f;

    public BathroomPlannerLayout(Context context, ConstraintLayout plannerAreaLayout, ImageView background)
    {
        //Init variables
        this.context = context;
        this.plannerArea = plannerAreaLayout;
        plannerItemArray = new ArrayList<Recycler_item>();

        editButtons = new EditButtons(context, plannerAreaLayout, plannerItemArray);
        BackgroundInit(background);
    }

    public BathroomPlannerLayout(Context context, ConstraintLayout plannerAreaLayout, ImageView background, ArrayList<Recycler_item> doorsWindowsList)
    {
        //Init variables
        this.context = context;
        this.plannerArea = plannerAreaLayout;
        plannerItemArray = doorsWindowsList;

        editButtons = new EditButtons(context, plannerAreaLayout, plannerItemArray);
        BackgroundInit(background);

        for (Recycler_item item : doorsWindowsList)
        {
            ImageView icon = new ImageView(context);
            icon.setImageResource(item.getImage());
            icon.setLayoutParams(setScale(item.getImage()));

            icon.setX(item.GetX());
            icon.setY(item.GetY());
            icon.setRotation(item.rotated);

            plannerAreaLayout.addView(icon);
        }
    }

    private void BackgroundInit(ImageView background){

        //Deselect on clicking background or main planner view.
        this.background = background;
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectItem();
            }
        });

        plannerArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deselectItem();
            }
        });
    }

    public ArrayList<Recycler_item> GetItemList()
    {
        return plannerItemArray;
    }

    private RelativeLayout.LayoutParams setScale(int imageID)
    {
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        lParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        Drawable drawable = context.getResources().getDrawable(imageID);
        int tWidth = drawable.getIntrinsicWidth();
        int tHeight = drawable.getIntrinsicHeight();

        lParams.height = Math.round(tWidth * itemScale);
        lParams.width = Math.round(tHeight * itemScale);

        return lParams;
    }

    //Add item to planner view
    public void AddItem(Recycler_item item)
    {
        deselectItem();

        ImageView icon = new ImageView(context);
        icon.setScaleType(ImageView.ScaleType.FIT_XY);
        icon.setImageResource(item.getImage());
        icon.setLayoutParams(setScale(item.getImage()));

        //Dragging selected item listener
        icon.setOnTouchListener(new View.OnTouchListener() {

            float x, y;

            @SuppressLint("ClickableViewAccessibility")
            @Override

            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        selectItem(icon, item);

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

                        editButtons.update(icon);

                        break;

                    case MotionEvent.ACTION_UP:

                        break;

                }

                return true;

            }

        });

        plannerArea.addView(icon);
        plannerItemArray.add(item);
        editButtons.update(icon);
    }

    //Set selected item
    private void selectItem(ImageView imageView, Recycler_item item)
    {
        deselectItem();

        selectedView = imageView;
        selectedView.setColorFilter(ContextCompat.getColor(context, R.color.planner_selected_object));
        editButtons.viewSelected(imageView, item);
        editButtons.update(imageView);
    }

    //Deselect item
    private void deselectItem()
    {
        //imageView.setColorFilter(Color.argb(255,0,0,1));

        if(selectedView != null) {
            selectedView.clearColorFilter();
            editButtons.viewDeselected();
        }
    }
}
