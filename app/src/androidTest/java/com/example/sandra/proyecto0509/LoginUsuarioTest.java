package com.example.sandra.proyecto0509;

import android.app.Activity;
import android.app.Instrumentation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.times;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LoginUsuarioTest {

    @Rule
    public IntentsTestRule<CrearUsuarios> activityRule = new IntentsTestRule<>(CrearUsuarios.class);

    private String username="conchita2@hotmail.com";
    private String password="conchita";

    @Test
    public void clickLoginButton(){
        onView(withId(R.id.ed_email)).perform(ViewActions.typeText(username), closeSoftKeyboard());
        onView(withId(R.id.et_password)).perform(ViewActions.typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btn_yaregistrado)).perform(click());
        intended(hasComponent(PantallaSecundaria.class.getName()), times(2));
    }
}