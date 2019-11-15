package com.example.sandra.proyecto0509;


import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import androidx.annotation.NonNull;

import static org.junit.Assert.*;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    private String username="conchita2@hotmail.com";
    private String password="conchita";

    final String FAKE_STRING="Sesion iniciada con email " + username;

    @Mock
    Context mMockContext;

    @Test
    public void inicio_sesion_ok(){

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        CrearUsuarios nuevo_usuario=new CrearUsuarios();
        assertEquals(user.getUid(),nuevo_usuario.iniciarsesion(username,password));
    }
    @Test
    public void addition_isCorrect() {
       MainActivity aux= new MainActivity();


    }
}