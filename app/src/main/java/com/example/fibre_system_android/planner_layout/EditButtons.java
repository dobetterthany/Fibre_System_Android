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

    Textview

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

        rotate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float eventX = event.getRawX() + dX;
                        float eventY = event.getRawY() + dY;
                        float dot = (selectedCenterX * eventX + selectedCenterY * eventY);
                        double den = (Math.sqrt(Math.pow(selectedCenterX, 2) + Math.pow(selectedCenterY, 2)) * (Math.sqrt(Math.pow(eventX, 2) + Math.pow(eventY, 2))));
                        double cos =  (dot / den);

                        angle =  (float) Math.atan2(eventX - selectedCenterX, eventY - selectedCenterY);
                        //angle = (float)Math.toDegrees(angle);
                        setAngleFromSelectedView(view, angle);
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

        setAngleFromSelectedView(rotate, 0);

        setAngleFromSelectedView(delete, 2);
    }

    private void setAngleFromSelectedView(View view, float angle)
    {
        newX = (float) (selectedCenterX + radius * Math.sin(angle));
        newY = (float) (selectedCenterY + radius * Math.cos(angle));

        view.animate()
                .x(newX - view.getWidth() / 2)
                .y(newY- view.getHeight() / 2)
                .setDuration(0)
                .start();
    }
}
