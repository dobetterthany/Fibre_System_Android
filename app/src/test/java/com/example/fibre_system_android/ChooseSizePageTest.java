package com.example.fibre_system_android;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import android.app.ActionBar;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class ChooseSizePageTest {
    @Rule
    public ActivityTestRule<Choose_Size_Page> activityRule =
            new ActivityTestRule<>(Choose_Size_Page.class);

    @Test
    public void testAddImage() {
        Choose_Size_Page activity = activityRule.getActivity();
        activity.runOnUiThread(() -> {
            // Set input values
            EditText widthInput = activity.findViewById(R.id.widthInput);
            widthInput.setText("100");
            EditText heightInput = activity.findViewById(R.id.heightInput);
            heightInput.setText("150");

            // Call AddImage method
            activity.AddImage(R.drawable.square);

            // Check if image view has expected layout params
            ImageView icon = activity.findViewById(R.id.shapeImage);
            ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
            assertEquals(100, layoutParams.width);
            assertEquals(150, layoutParams.height);
        });
    }

    @Test
    public void testAddImageWithInvalidInput() {
        Choose_Size_Page activity = activityRule.getActivity();
        activity.runOnUiThread(() -> {
            // Set invalid input values
            EditText widthInput = activity.findViewById(R.id.widthInput);
            widthInput.setText("invalid");
            EditText heightInput = activity.findViewById(R.id.heightInput);
            heightInput.setText("150");

            // Call AddImage method
            activity.AddImage(R.drawable.square);

            // Check if image view has expected layout params (should not have changed)
            ImageView icon = activity.findViewById(R.id.shapeImage);
            ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
            assertEquals(ActionBar.LayoutParams.WRAP_CONTENT, layoutParams.width);
            assertEquals(ActionBar.LayoutParams.WRAP_CONTENT, layoutParams.height);
        });
    }

    @Test
    public void testConfirmButtonOnClick() {
        Choose_Size_Page activity = activityRule.getActivity();
        activity.runOnUiThread(() -> {
            // Set input values
            EditText widthInput = activity.findViewById(R.id.widthInput);
            widthInput.setText("100");
            EditText heightInput = activity.findViewById(R.id.heightInput);
            heightInput.setText("150");

            // Click confirm button
            Button confirmButton = activity.findViewById(R.id.confirmButton);
            confirmButton.performClick();

            // Check if image view has expected layout params
            ImageView icon = activity.findViewById(R.id.shapeImage);
            ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
            assertEquals(100, layoutParams.width);
            assertEquals(150, layoutParams.height);
        });
    }

    @Test
    public void testNextButtonOnClick() {
        Choose_Size_Page activity = activityRule.getActivity();
        activity.runOnUiThread(() -> {
            // Set input values
            EditText widthInput = activity.findViewById(R.id.widthInput);
            widthInput.setText("100");
            EditText heightInput = activity.findViewById(R.id.heightInput);
            heightInput.setText("150");

            // Click next button
            Button nextButton = activity.findViewById(R.id.goAddwindowButton);
            nextButton.performClick();

            // Check if intent has expected extras
            Intent intent = Shadows.shadowOf(activity).getNextStartedActivity();
            assertEquals(100, intent.getIntExtra("InputWidth", 0));
            assertEquals(150, intent.getIntExtra("InputHeight", 0));
        });
    }
}
