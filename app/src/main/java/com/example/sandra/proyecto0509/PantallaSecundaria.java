package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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


public class PantallaSecundaria extends AppCompatActivity
{
    //ImageView informacion;
    Button empezar;
    Button resultados;

    //TextView tv1,tv2;
    public static final String user="names";

    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_secundaria);

        empezar=findViewById(R.id.btn_empezar);
        //informacion=findViewById(R.id.btn_informacion);
        resultados=findViewById(R.id.btn_resultados);

        //tv1=findViewById(R.id.tvnombre);
       // tv2=findViewById(R.id.tvname);




        /*FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Usuarios de la APP").child("Usuarios").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usu=dataSnapshot.getValue(Usuario.class);
                //obtenermos los valores que queramos
                String email=usu.getEmail();
                int contador=usu.getContador();
                contador= Integer.parseInt(numero_contador.getText().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


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
