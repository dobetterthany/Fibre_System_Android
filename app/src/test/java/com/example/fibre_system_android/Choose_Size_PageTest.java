package com.example.fibre_system_android;

import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class Choose_Size_PageTest {

    public Choose_Size_Page choose_size_page;

@Before
public void setUp() throws Exception {
    choose_size_page = Robolectric.buildActivity(Choose_Size_Page.class)
            .create()
            .resume()
            .get();
}

@Test
public void shouldNotBeNull() throws Exception {
    assertNotNull(choose_size_page);
}


//    @Rule
//    public ActivityScenarioRule<Choose_Size_Page> rule =
//            new ActivityScenarioRule<>(Choose_Size_Page.class);
//
//
//    @Test
//    public void onCreate() {
//        rule.getScenario().onActivity(new ActivityScenario.ActivityAction<Choose_Size_Page>() {
//            @Override
//            public void perform(Choose_Size_Page activity) {
//                EditText heightText = activity.findViewById(R.id.heightInput);
//                onView(withId(R.id.heightInput)).perform(typeText("20000"), closeSoftKeyboard());
//                int height = Integer.valueOf(heightText.getText().toString());
//                EditText widthText = activity.findViewById(R.id.widthInput);
//                Button button = activity.findViewById(R.id.testButton);
//                int max = 20000;
//
//

@Test
    public void setSizeTest(){
        //Returns True
        Assert.assertTrue(choose_size_page.setSize(1500,1500));
        Assert.assertTrue(choose_size_page.setSize(Choose_Size_Page.minHeight,Choose_Size_Page.minWidth));
        Assert.assertTrue(choose_size_page.setSize(Choose_Size_Page.maxHeight,Choose_Size_Page.maxWidth));
        Assert.assertTrue(choose_size_page.setSize(Choose_Size_Page.minHeight+1000,Choose_Size_Page.minWidth+1000));
        Assert.assertTrue(Choose_Size_Page.minHeight<Choose_Size_Page.maxHeight);
        Assert.assertTrue(Choose_Size_Page.minWidth<Choose_Size_Page.maxWidth);


        //Returns false
        Assert.assertFalse(choose_size_page.setSize(Choose_Size_Page.minHeight-1,Choose_Size_Page.minWidth-1));
        Assert.assertFalse(choose_size_page.setSize(Choose_Size_Page.maxHeight+1,Choose_Size_Page.maxWidth+1));
    }

}