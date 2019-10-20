package com.example.sandra.proyecto0509;



import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/*@RunWith(AndroidJUnit4.class)
public class PantallaSecundariaTest {

    @Rule
    public ActivityTestRule<PantallaSecundaria> activityTestRule= new ActivityTestRule<PantallaSecundaria>(PantallaSecundaria.class);*/


    /*Este test comprueba que los botones de la pantalla secundaria
    * (empezar, estadisticas y cerrar sesion)
    * son visible en su correspondiente activity*/
  /*  @Test
    public void mostrarBotones(){
        onView(withId(R.id.btn_empezar)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_resultados)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_salir)).check(matches(isDisplayed()));
    }*/

    /*Test que comprueba que el boton empezar es clicable y abre
    * su activity correspondiente*/
  /*  @Test
    public void clickBotonEmpezar(){
        onView(withId(R.id.btn_empezar)).perform(click());
    }

    *//*Test que comprueba que el boton resultados es clicable y abre
     * su activity correspondiente*//*
    @Test
    public void clickBotonResultados(){
        onView(withId(R.id.btn_resultados)).perform(click());
    }*/



//}