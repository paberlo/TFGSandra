package com.example.sandra.proyecto0509;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class CrearUsuariosTest {

    @Rule
    public ActivityTestRule<CrearUsuarios> activityRule = new ActivityTestRule<>(CrearUsuarios.class);

    /*Test que comprueba que los usuarios inician sesion y estan registrados*/
    @Test
    public void clickLoginButton_opensLoginUi() throws Exception{
        //comprobar que el boton btn_yaregistrado es visible al usuario
        onView(withId(R.id.btn_yaregistrado)).perform(click())
                                             .check(matches(isDisplayed()));
        //comprobar que el boton btn_register es visible al usuario
        onView(withId(R.id.btn_register)).perform(click())
                                         .check(matches(isDisplayed()));
        onView(withId(R.id.textView29)).check(matches(isDisplayed()));
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
        onView(withId(R.id.ed_email)).perform(typeText("conchita2@hotmail.com"))
                                     .check(matches(isDisplayed()));
        onView(withId(R.id.et_password)).perform(typeText("conchita"))
                                        .check(matches(isDisplayed()));
        onView(withId(R.id.ed_email)).perform(typeText("jefeestudios33@hotmail.com"))
                                     .check(matches(isDisplayed()));
        onView(withId(R.id.et_password)).perform(typeText("1234567"))
                                        .check(matches(isDisplayed()));
    }

}