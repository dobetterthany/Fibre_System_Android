package com.example.fibre_system_android.planner_layout;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.fibre_system_android.R;
import com.example.fibre_system_android.Vector2;

public class EditButtons {
    Context context;
    RelativeLayout plannerAreaLayout;

    ImageView rotate;
    ImageView delete;

    View selectedView;
    float selectedViewX, selectedViewY, selectedViewW, selectedViewH;
    Vector2 selectedViewCenter;
    float dX = 0, dY = 0;
    float newX, newY;
    float radius = 150; //Radius of buttons from target view

    //Rotation
    float angle;

    EditButtons(Context context, RelativeLayout plannerAreaLayout) {
        this.plannerAreaLayout = plannerAreaLayout;
        this.context = context;
        initControls();
    }

    private void initControls() {
        delete = new ImageView(context);
        rotate = new ImageView(context);

        delete.setVisibility(View.GONE);
        rotate.setVisibility(View.GONE);

        rotate.setImageResource(R.drawable.small_square);
        rotate.setColorFilter(Color.argb(150, 100, 0, 100));

        delete.setImageResource(R.drawable.small_square);
        delete.setColorFilter(Color.argb(150, 0, 100, 0));

        plannerAreaLayout.addView(rotate);
        plannerAreaLayout.addView(delete);
        //plannerAreaLayout.addView(delete);

        selectedViewCenter = new Vector2(0,0);

        rotate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        angle =  (float) Math.atan2(event.getRawY(), event.getRawX());
                        newX = (float) (radius * Math.sin(angle));
                        newY = (float) (radius * Math.cos(angle));
                        view.animate()
                                .x(newX + selectedViewCenter.getX())
                                .y(newY + selectedViewCenter.getY())
                                .setDuration(0)
                                .start();
                        break;

                    case MotionEvent.ACTION_UP:
                            updatePos();

                        break;

                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private void setScale(View view, float scale) {
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

    public void toggleButtonsVisible(){
        toggleView(rotate);
        toggleView(delete);
    }

    public void toggleView(View view) {
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);

        else if (view.getVisibility() == View.VISIBLE)
            view.setVisibility(View.GONE);
    }

    //On tap code to go here, eg, info for selected item (name)
    public void viewSelected(View view){
        updatePos();
        rotate.bringToFront();
        rotate.setVisibility(View.VISIBLE);

        delete.bringToFront();
        delete.setVisibility(View.VISIBLE);
    }

    public void viewDeselected()
    {
        rotate.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);
    }

    private void updatePos() {
        rotate.animate()
                .x(selectedViewX + selectedViewW + radius)
                .y(selectedViewY - rotate.getHeight()/2 - radius)
                .setDuration(0)
                .start();

        delete.animate()
                .x(selectedViewX - radius - delete.getWidth())
                .y(selectedViewY - delete.getHeight()/2 - radius)
                .setDuration(0)
                .start();
    }

    public void update(View view)
    {
        selectedViewX = view.getX();
        selectedViewY = view.getY();
        selectedViewW = view.getWidth();
        selectedViewH = view.getHeight();

        selectedViewCenter.setX(view.getX() + selectedViewW / 2);
        selectedViewCenter.setY(view.getY() + selectedViewH / 2);

        updatePos();
    }
}
