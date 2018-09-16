package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PantallaPrincipal extends AppCompatActivity
{

    //Tiempo que la pantalla se esta mostrando
    private final int DURACION_SPLASH=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                /*Mostramos la pantalla que queremos mostrar cuando
                finalice el tiempo.*/
                Intent intent= new Intent(PantallaPrincipal.this, PantallaSecundaria.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
