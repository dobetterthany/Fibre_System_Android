package com.example.fibre_system_android.planner_layout;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.fibre_system_android.R;

public class EditButtons {
    Context context;
    RelativeLayout plannerAreaLayout;

    ImageView rotate;
    ImageView delete;

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

        rotate.setImageResource(R.drawable.square);
        rotate.setColorFilter(Color.argb(150, 100, 0, 100));
        setScale(rotate, .5f);

        plannerAreaLayout.addView(rotate);
        //plannerAreaLayout.addView(delete);
    }

    private void setScale(View view, float scale) {
        view.setScaleX(scale);
        view.setScaleY(scale);
    }

    public void toggleButtonsVisable()
    {
        toggleView(rotate);
        toggleView(delete);
    }

    public void toggleView(View view) {
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);

        else if (view.getVisibility() == View.VISIBLE)
            view.setVisibility(View.GONE);
    }
}
