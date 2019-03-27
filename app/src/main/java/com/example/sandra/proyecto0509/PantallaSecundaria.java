package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class PantallaSecundaria extends AppCompatActivity
{
    ImageView informacion;
    Button empezar;
    Button resultados;

    TextView tv1,tv2;
    public static final String user="names";

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_secundaria);

        empezar=findViewById(R.id.btn_empezar);
        informacion=findViewById(R.id.btn_informacion);
        resultados=findViewById(R.id.btn_resultados);

        tv1=findViewById(R.id.tvnombre);
        tv2=findViewById(R.id.tvname);
        String user = getIntent().getStringExtra("names");
        tv1.setText(user);


        empezar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /*String userE=tv1.getText().toString();
                BaseDatos user=new BaseDatos(userE);
                databaseReference.child(tv2.getText().toString()).child(userE).setValue(user);*/
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
