package com.example.sandra.proyecto0509;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {

    private String username="conchita2@hotmail.com";
    private String password="conchita";

    final String FAKE_STRING="Sesion iniciada con email " + username;

    @Test
    public void addition_isCorrect() {
        CrearUsuarios aux= new CrearUsuarios();

    }
}