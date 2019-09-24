package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandra.proyecto0509.Objetos.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;


public class PantallaSecundaria extends AppCompatActivity
{
    //ImageView informacion;
    Button empezar;
    Button resultados;
    Button salir;

    //TextView tv1,tv2;
    public static final String user="names";

    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_secundaria);

        empezar= (Button) findViewById(R.id.btn_empezar);
        //informacion=findViewById(R.id.btn_informacion);
        resultados= (Button) findViewById(R.id.btn_resultados);



        salir= (Button) findViewById(R.id.btn_salir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent=new Intent(PantallaSecundaria.this,CrearUsuarios.class);
                startActivity(intent);
            }
        });
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

//        informacion.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Intent intent= new Intent(PantallaSecundaria.this, Informacion.class);
//                startActivity(intent);
//            }
//        });

        resultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PantallaSecundaria.this, Resultados.class);
                startActivity(intent);
            }
        });





    }
}
