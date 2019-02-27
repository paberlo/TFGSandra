package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PantallaSecundaria extends AppCompatActivity
{
    ImageView informacion;
    ImageView empezar;
    ImageView help;

    private final int DURACION_SPLASH=8000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_secundaria);

        empezar=findViewById(R.id.btn_empezar);
        informacion=findViewById(R.id.btn_informacion);
        help=findViewById(R.id.ayuda);

        empezar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(PantallaSecundaria.this, MainActivity.class);
                startActivity(intent);
            }
        });

        informacion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(PantallaSecundaria.this, Informacion.class);
                startActivity(intent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PantallaSecundaria.this, Instrucciones.class);
                startActivity(intent);
            }
        });
    }
}
