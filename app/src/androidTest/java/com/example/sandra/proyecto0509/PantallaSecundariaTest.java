package com.example.sandra.proyecto0509;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class) //lo tienes
public class PantallaSecundariaTest {
    @Test
   public void clickLoginButton_opensLoginUi() throws Exception{
        onView(withId(R.id.btn_empezar)).perform(click())               // click() is a ViewAction
                .check(matches(isDisplayed()));                         //check(matches(isDisplayed())); //la prueba
    }
}