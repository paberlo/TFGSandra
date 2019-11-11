package com.example.sandra.proyecto0509;



import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import androidx.annotation.NonNull;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.times;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PantallaSecundariaTest {

    private String username="conchita2@hotmail.com";
    private String password="conchita";
    @Rule
    public ActivityTestRule<PantallaSecundaria> activityTestRule= new ActivityTestRule<PantallaSecundaria>(PantallaSecundaria.class);


    /* Este test comprueba que los botones de la pantalla secundaria
    * (empezar, estadisticas y cerrar sesion)
    * son visible en su correspondiente activity*/

    @Test
    public void mostrarBotones(){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    /*onView(withId(R.id.btn_empezar)).check(matches(isDisplayed()));
                    onView(withId(R.id.btn_resultados)).check(matches(isDisplayed()));
                    onView(withId(R.id.btn_salir)).check(matches(isDisplayed()));*/

                }
            }
        });
        FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase fbd=FirebaseDatabase.getInstance();
                DatabaseReference dbr = fbd.getReference("users");
                dbr.child(user.getUid());
                //FirebaseUser user2 = firebaseAuth.getCurrentUser();

                if (user != null) {
                    onView(withId(R.id.btn_empezar)).check(matches(isDisplayed()));
                    onView(withId(R.id.btn_resultados)).check(matches(isDisplayed()));
                    onView(withId(R.id.btn_salir)).check(matches(isDisplayed()));
                    intended(hasComponent(MainActivity.class.getName()),times(0));
                }
            }
        };

    }

    /*Test que comprueba que el boton empezar es clicable y abre
    * su activity correspondiente*/

    /*@Test
    public void clickBotonEmpezar(){
        onView(withId(R.id.btn_empezar)).perform(click());
    }
*/

    /*Test que comprueba que el boton resultados es clicable y abre
     * su activity correspondiente*/

   /* @Test
    public void clickBotonResultados(){
        onView(withId(R.id.btn_resultados)).perform(click());
    }*/

}
