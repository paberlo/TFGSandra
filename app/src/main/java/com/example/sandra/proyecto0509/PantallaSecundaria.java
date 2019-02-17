package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaSecundaria extends AppCompatActivity
{
    Button informacion;
    Button empezar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_secundaria);

        empezar=(Button)findViewById(R.id.btn_empezar);
        informacion=(Button)findViewById(R.id.btn_informacion);

        empezar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(PantallaSecundaria.this, PantallaMaximo.class);
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
    }
}
