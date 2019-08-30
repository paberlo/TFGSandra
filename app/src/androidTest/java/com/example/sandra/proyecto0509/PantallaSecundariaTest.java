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

@RunWith(AndroidJUnit4.class)
public class PantallaSecundariaTest {

    //Comprobar que el btn_empezar esta visible para el usuario
    @Test
   public void clickLoginButton_opensLoginUiEmpezar() throws Exception{
        onView(withId(R.id.btn_empezar)).perform(click())               // click() is a ViewAction
                .check(matches(isDisplayed()));
        onView(withId(R.id.btn_resultados)).perform(click())
                .check(matches(isDisplayed()));//check(matches(isDisplayed())); //la prueba
    }

}