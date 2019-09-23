package com.example.sandra.proyecto0509;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PantallaSecundariaTest {

    @Rule
    public ActivityTestRule<PantallaSecundaria> activityTestRule= new ActivityTestRule<PantallaSecundaria>(PantallaSecundaria.class);


    /*Este test comprueba que los botones de la pantalla secundaria
    * (empezar, estadisticas y cerrar sesion)
    * son visibles y al hacer click abren la correspondiente activity*/
    @Test
    public void mostrarBotones(){
        onView(withId(R.id.btn_empezar)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_resultados)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_salir)).check(matches(isDisplayed()));
    }

    @Test
    public void clickBotonEmpezar(){
        onView(withId(R.id.btn_empezar)).perform(click());
    }

    @Test
    public void clickBotonResultados(){
        onView(withId(R.id.btn_resultados)).perform(click());
    }

    @Test
    public void clickBotonSalir(){
        onView(withId(R.id.btn_salir)).perform(click());
    }

}