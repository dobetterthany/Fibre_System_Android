package com.example.fibre_system_android.planner_layout;

import android.app.ActionBar;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.example.fibre_system_android.R;

import java.util.ArrayList;

public class BathroomPlannerLayout {

    //Layout where all shapes are placed
    RelativeLayout plannerArea;

    //When a view is clicked it will be "selected"
    ImageView selectedView;


    //Stores all items added to array.
    ArrayList<ImageView> plannerItemArray;
    float dX = 0, dY = 0;
    Context context;

    EditButtons editButtons;

    public BathroomPlannerLayout(Context context, RelativeLayout plannerAreaLayout)
    {
        //Init variables
        this.context = context;
        this.plannerArea = plannerAreaLayout;
        plannerItemArray = new ArrayList<ImageView>();

        editButtons = new EditButtons(context, plannerAreaLayout);
    }

    //Add image to planner view
    public void AddImage(int tag)
    {
        deselectItem(selectedView);

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        lParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        ImageView icon = new ImageView(context);
        icon.setImageResource(tag);
        icon.setLayoutParams(lParams);

        icon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        deselectItem(selectedView);
                        selectItem((ImageView) view);

                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        view.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        editButtons.update(view);
                        break;

                    default:
                        return false;
                }
                return true;
            }
        } );
        // plannerItemArray.add(icon);
        plannerArea.addView(icon);

    }

    //Deselects view from planner area when selecting an item from the recycler view
    private void deselectAll() {
        final int childCount = plannerArea.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = plannerArea.getChildAt(i);

            if(v instanceof ImageView)
            {
                deselectItem((ImageView) v);
            }
        }
    }

    private void selectItem(ImageView imageView)
    {
        selectedView = imageView;
        selectedView.setColorFilter(ContextCompat.getColor(context, R.color.planner_selected_object));
        editButtons.viewSelected(imageView);
        editButtons.update(imageView);
        //TODO: Code to move item edit buttons(rotation, delete, etc)
    }

    private void deselectItem(ImageView imageView)
    {
        //imageView.setColorFilter(Color.argb(255,0,0,1));

        if(imageView != null) {
            imageView.clearColorFilter();
            editButtons.viewDeselected();
        }
    }
}
