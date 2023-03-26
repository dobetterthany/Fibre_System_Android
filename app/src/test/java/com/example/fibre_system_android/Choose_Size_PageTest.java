package com.example.fibre_system_android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.junit.Test;

public class Choose_Size_PageTest {

    private Choose_Size_Page choose_size_page;




    @Test
    public void onCreate() {

    }

    @Test
    public void testAddImage() {
        choose_size_page = new Choose_Size_Page();
        int imageID = R.drawable.square;
        int expectedHeight = 100;
        int expectedWidth = 200;

        choose_size_page.heightInput.setText(String.valueOf(expectedHeight));
        choose_size_page.widthInput.setText(String.valueOf(expectedWidth));

        choose_size_page.AddImage(imageID);

        ImageView icon = choose_size_page.findViewById((R.id.shapeImage));
        assertNotNull(icon);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) icon.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(expectedHeight, layoutParams.height);
        assertEquals(expectedWidth, layoutParams.width);


    }
}