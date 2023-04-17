package com.example.fibre_system_android;

import org.junit.Assert;
import org.junit.Test;

public class Choose_Size_PageTest {

    public Choose_Size_Page choose_size_page;



//    @Rule
//    public ActivityScenarioRule<Choose_Size_Page> rule =
//            new ActivityScenarioRule<>(Choose_Size_Page.class);
//

    @Test
    public void onCreate() {
//        ActivityScenario<Choose_Size_Page> scenario = rule.getScenario();
        choose_size_page = new Choose_Size_Page();
        int maximumHeight = 2000;
        int maximumWidth = 2000;
        int minHeight = 1000;
        int minWidth = 1000;

//
//        String heightString = choose_size_page.heightInput.getText().toString();
//        String widthString = choose_size_page.widthInput.getText().toString();
//        int height = Integer.parseInt(heightString);
//        int width = Integer.parseInt(widthString);
//        height = 200;
//        width = 198;
//
//
        Assert.assertTrue(choose_size_page.height <= maximumHeight);
        Assert.assertTrue(choose_size_page.width <= maximumWidth);

        Assert.assertTrue(choose_size_page.height >= minHeight);
        Assert.assertTrue(choose_size_page.width >= minWidth);



    }

    @Test
    public void addImage() {

    }
}