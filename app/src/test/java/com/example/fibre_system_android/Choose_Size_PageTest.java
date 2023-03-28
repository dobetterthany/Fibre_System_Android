package com.example.fibre_system_android;

import static org.junit.Assert.assertTrue;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;


public class Choose_Size_PageTest {

    @Rule
    public ActivityScenarioRule<Choose_Size_Page> rule =
            new ActivityScenarioRule<>(Choose_Size_Page.class);

    @Test
    public void onCreate() {
        Choose_Size_Page choose_size_page = new Choose_Size_Page();
        ActivityScenario<Choose_Size_Page> scenario = rule.getScenario();
        int maximumHeight = 2000;
        int maximumWidth = 2000;

        String heightString = choose_size_page.heightInput.getText().toString();
        String widthString = choose_size_page.widthInput.getText().toString();
        int height = Integer.parseInt(heightString);
        int width = Integer.parseInt(widthString);
        height = 200;
        width = 198;

        assertTrue(height<=maximumHeight);
        assertTrue(maximumWidth>=width);
        assertTrue(maximumHeight<height);
        assertTrue(maximumWidth<width);
    }

    @Test
    public void addImage() {

    }
}