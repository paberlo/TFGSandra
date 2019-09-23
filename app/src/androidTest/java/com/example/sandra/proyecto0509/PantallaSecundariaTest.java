package com.example.sandra.proyecto0509;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PantallaSecundariaTest {

    @Rule
    public ActivityTestRule<PantallaSecundaria> activityTestRule= new ActivityTestRule<PantallaSecundaria>(PantallaSecundaria.class);

    @Test
    public void mostrarBotones(){
        onView(withId(R.id.btn_empezar)).check(matches(isDisplayed()));
    }
}