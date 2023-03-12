package com.example.fibre_system_android.planner_layout;

import android.app.ActionBar;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

import com.example.fibre_system_android.R;
import com.example.fibre_system_android.RecyclerViewItems;

import java.util.ArrayList;

public class BathroomPlannerLayout {

    //Layout where all shapes are placed
    RelativeLayout plannerArea;

    //When a view is clicked it will be "selected"
    ImageView selectedView;


    //Stores all items added to array.
    ArrayList<RecyclerViewItems> plannerItemArray;
    float dX = 0, dY = 0;
    Context context;

    EditButtons editButtons;

    public BathroomPlannerLayout(Context context, RelativeLayout plannerAreaLayout)
    {
        //Init variables
        this.context = context;
        this.plannerArea = plannerAreaLayout;
        plannerItemArray = new ArrayList<RecyclerViewItems>();

        editButtons = new EditButtons(context, plannerAreaLayout, plannerItemArray);
    }

    public ArrayList<RecyclerViewItems> getItemArray()
    {
        return plannerItemArray;
    }

    //Add image to planner view
    public void AddItem(RecyclerViewItems item)
    {
        deselectItem(selectedView);

        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        lParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        ImageView icon = new ImageView(context);
        icon.setImageResource(item.getImage());
        icon.setLayoutParams(lParams);

        icon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        deselectItem(selectedView);
                        selectItem((ImageView) view, item);
                        editButtons.update(view);

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

        plannerArea.addView(icon);
        plannerItemArray.add(item);
    }

    private void selectItem(ImageView imageView, RecyclerViewItems item)
    {
        selectedView = imageView;
        selectedView.setColorFilter(ContextCompat.getColor(context, R.color.planner_selected_object));
        editButtons.viewSelected(imageView, item);
        editButtons.update(imageView);
    }

    private void deselectItem(ImageView imageView)
    {
        //imageView.setColorFilter(Color.argb(255,0,0,1));

        if(imageView != null) {
            imageView.clearColorFilter();
            editButtons.viewDeselected();
        }
    }

    public ArrayList<RecyclerViewItems> GetItemList()
    {
        return plannerItemArray;
    }
}
