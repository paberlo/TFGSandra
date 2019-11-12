
package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
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



@RunWith(AndroidJUnit4.class)
public class SMainActivityTest extends LoginTest {

    private String username="conchita2@hotmail.com";
    private String password="conchita";

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    /*Test que comprueba que el boton star y brillo son visibles
     * y al hacer click realizan su funcion correspondiente*/

    @Test
    public void mostrar_clickBotones() throws Exception {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {}
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
                    onView(withId(R.id.btn_start))
                            .perform(click())
                            .check(matches(isDisplayed()));
                    onView(withId(R.id.btn_brillo))
                            .perform(click())
                            .check(matches(isDisplayed()));

                }
            }
        };
    }

    /*Test que comprueba la visilibilidad de los botones mas y menos
     * y su funcionalidad al hacer click*/


    /*@Test
    public void btn_masymenos() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {}
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
                    onView(withId(R.id.btn_mas)).perform(click()).check(matches(isDisplayed()));
                    onView(withId(R.id.btn_menos)).perform(click()).check(matches(isDisplayed()));
                }
            }
        };
    }*/

    /*Test que comprueba que el seekbar llega al limite correspondiente*/

    @Test
    public void moverSeekbar() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {}
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
                    onView(withId(R.id.seekBar1)).perform(clickSeekBar(51));
                    onView(withId(R.id.seekBar1)).perform(clickSeekBar(200));

                }
            }
        };

    }


    public static ViewAction clickSeekBar(final int pos) {
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {
                        SeekBar seekBar = (SeekBar) view;
                        final int[] screenPos = new int[2];
                        seekBar.getLocationOnScreen(screenPos);

                        // get the width of the actual bar area
                        // by removing padding
                        int trueWidth = seekBar.getWidth()
                                - seekBar.getPaddingLeft() - seekBar.getPaddingRight();

                        // what is the position on a 0-1 scale
                        //  add 0.3f to avoid roundoff to the next smaller position
                        float relativePos = (0.3f + pos) / (float) seekBar.getMax();
                        if (relativePos > 1.0f)
                            relativePos = 1.0f;

                        // determine where to click
                        final float screenX = trueWidth * relativePos + screenPos[0]
                                + seekBar.getPaddingLeft();
                        final float screenY = seekBar.getHeight() / 2f + screenPos[1];
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }
}




