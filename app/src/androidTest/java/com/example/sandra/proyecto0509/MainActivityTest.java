package com.example.sandra.proyecto0509;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Test
    public void clickLoginButton_opensLoginUi() throws Exception{
        onView(withId(R.id.btn_start)).perform(click())               // click() is a ViewAction
                .check(matches(isDisplayed()));//check(matches(isDisplayed())); //la prueba
        onView(withId(R.id.btn_brillo)).perform(click())
                .check(matches(isDisplayed()));
    }

}