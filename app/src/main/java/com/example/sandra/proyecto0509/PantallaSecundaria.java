package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PantallaSecundaria extends AppCompatActivity
{
    ImageView informacion;
    Button empezar;
    Button resultados;

    TextView tv1,tv2,tv3,tv4;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_secundaria);

        empezar=findViewById(R.id.btn_empezar);
        informacion=findViewById(R.id.btn_informacion);
        resultados=findViewById(R.id.btn_resultados);

        tv1=findViewById(R.id.tvnombre);
        tv2=findViewById(R.id.tvusername);
        tv3=findViewById(R.id.tvpassword);
        tv4=findViewById(R.id.tvedad);

        //recibimos los diferentes intents
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");
        int age=intent.getIntExtra("age",-1);

        //asignamos los datos a nuestros textviews
        tv1.setText(name);
        tv2.setText(username);
        tv3.setText(password);
        tv4.setText(age+"");


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

        resultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PantallaSecundaria.this, Resultados.class);
                startActivity(intent);
            }
        });





    }
}
