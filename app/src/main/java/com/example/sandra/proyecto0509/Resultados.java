
package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Resultados extends AppCompatActivity {


    TableLayout tabla;
    TableRow cabecera;
    TableRow separador_cabecera;
    TableRow fila;
    HashMap<String, Integer> meses=new HashMap<String, Integer>();
    Button jefe;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        //FirebaseApp.initializeApp(this);

         final Tabla tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        /*jefe=findViewById(R.id.btn_jefe);
        jefe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://console.firebase.google.com/project/ag21tfg/database/ag21tfg/data/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });*/

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase fbd=FirebaseDatabase.getInstance();
        DatabaseReference dbr= (DatabaseReference) fbd.getReference("users").child(user.getUid());
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              // for(DataSnapshot datas: dataSnapshot.getChildren()){
                    //List <DataSnapshot> aux= (List<DataSnapshot>) datas.getChildren();
                    //Log.d("I","aqui empiezan datos");
                    //Log.d("I",datas.getValue().toString());
               // DataSnapshot children = null;
                   for(DataSnapshot  children: dataSnapshot.getChildren()){
                        Log.d("I", children.child("contadores").getValue().toString());
                        Log.d("I", children.getKey());
                        if(meses.containsKey(children.getKey())){
                            meses.put(children.getKey(), meses.get(children.getKey()) + Integer.parseInt(children.child("contadores").getValue().toString()));
                        }
                        else{
                            //Log.d("I", children.getValue().toString());
                            meses.put(children.getKey(), Integer.parseInt(children.child("contadores").getValue().toString()));
                        }
                    }
            //   }
                Log.d("I",meses.toString());
                /*ArrayList<String> elementos=new ArrayList<String>();
                elementos.add("Mayo 2019" );
                elementos.add(meses.get("052019").toString());
                tabla.agregarFilaTabla(elementos);*/
                for (String key : meses.keySet()){
                    ArrayList<String> elementos=new ArrayList<String>();
                    Log.d("I","caca");
                    Log.d("I",key);
                    Log.d("I",key.substring(0,2));
                    if(key.substring(0,2).equals("01")){
                        elementos.add("Enero " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("02")){
                        elementos.add("Febrero " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("03")){
                        elementos.add("Marzo " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("04")){
                        elementos.add("Abril " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("05")) {
                        elementos.add("Mayo " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("06")){
                        elementos.add("Junio " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("07")){
                        elementos.add("Julio " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("08")){
                        elementos.add("Agosto " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("09")){
                        elementos.add("Septiembre " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("10")){
                        elementos.add("Octubre " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("11")){
                        elementos.add("Noviembre " + key.substring(2,6));
                    }
                    if(key.substring(0,2).equals("12")){
                        elementos.add("Diciembre " + key.substring(2,6));
                    }
                    elementos.add(meses.get(key).toString());
                    tabla.agregarFilaTabla(elementos);
                        /*case "05":
                            elementos.add("Mayo " + key.substring(2,6));
                            break;
                        default:
                            elementos.add(meses.get(key).toString());
                            tabla.agregarFilaTabla(elementos);*/
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}




