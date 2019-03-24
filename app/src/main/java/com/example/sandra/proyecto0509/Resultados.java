
package com.example.sandra.proyecto0509;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Resultados extends AppCompatActivity {


    TableLayout tabla;
    TableRow cabecera;
    TableRow separador_cabecera;
    TableRow fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Tabla tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);

            /*ArrayList<String> elemento1 = new ArrayList<String>();
            elemento1.add("Septiembre");
            tabla.agregarFilaTabla(elemento1);

            ArrayList<String> elemento2=new ArrayList<String>();
            elemento2.add("Octubre");
            tabla.agregarFilaTabla(elemento2);

            ArrayList<String> elemento3=new ArrayList<String>();
            elemento3.add("Noviembre");
            tabla.agregarFilaTabla(elemento3);

            ArrayList<String> elemento4=new ArrayList<String>();
            elemento4.add("Diciembre");
            tabla.agregarFilaTabla(elemento4);

            ArrayList<String> elemento5=new ArrayList<String>();
            elemento5.add("Enero");
            tabla.agregarFilaTabla(elemento5);

            ArrayList<String> elemento6=new ArrayList<String>();
            elemento6.add("Febrero");
            tabla.agregarFilaTabla(elemento6);

            ArrayList<String> elemento7=new ArrayList<String>();
            elemento7.add("Marzo");
            tabla.agregarFilaTabla(elemento7);

            ArrayList<String> elemento8=new ArrayList<String>();
            elemento8.add("Abril");
            tabla.agregarFilaTabla(elemento8);

            ArrayList<String> elemento9=new ArrayList<String>();
            elemento9.add("Mayo");
            tabla.agregarFilaTabla(elemento9);

            ArrayList<String> elemento10=new ArrayList<String>();
            elemento10.add("Junio");
            tabla.agregarFilaTabla(elemento10);*/

          /*String[]meses={"Septiembre","Octubre","Noviembre","Diciembre","Enero","Febrero","Marzo","Abril","Mayo","Junio"};


          for(int i = 0; i < meses.length; i++)
          {
                ArrayList<String> elementos = new ArrayList<String>();
                elementos.add();
                elementos.add("Casilla [" + i + ", 0]");
                elementos.add("Casilla [" + i + ", 1]");
                elementos.add("Casilla [" + i + ", 2]");
                elementos.add("Casilla [" + i + ", 3]");
                tabla.agregarFilaTabla(elementos);
          }*/
        /*ArrayList<String>meses=new ArrayList<>();
        String a="Septiembre";
        String b="Octubre";
        String c="Noviembre";
        meses.add(a);
        meses.add(b);
        meses.add(c);
        tabla.agregarFilaTabla(a);
        tabla.agregarFilaTabla(b);
        tabla.agregarFilaTabla(c);*/


        /*Tabla tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);*/
        String[]meses={"Septiembre","Octubre","Noviembre","Diciembre","Enero","Febrero","Marzo","Abril","Mayo","Junio"};
        for(int i = 0; i < meses.length; i++)
        {
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add(meses[i]);
            elementos.add("0");
            tabla.agregarFilaTabla(elementos);
        }

    }
}

    /*    String cabeceras[] = {"Meses", "Contadores Totales"};
        String datos[][] = {{"Septiembre"},
                {"Octubre"},
                {"Noviembre"},
                {"Diciembre"},
                {"Enero"},
                {"Febrero"},
                {"Marzo"},
                {"Abril"},
                {"Mayo"},
                {"Junio"}};

        // TableLayout (diseño principal de la actividad)
        tabla = new TableLayout(this);
        tabla.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.FILL_PARENT));
        tabla.setColumnStretchable(1, true);

        // Cabecera de la tabla
        cabecera = new TableRow(this);
        cabecera.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        tabla.addView(cabecera);

        // Textos de la cabecera
            TextView columna = new TextView(this);
            columna.setLayoutParams(new TableRow.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
            columna.setText(cabeceras[0]);
            columna.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            columna.setGravity(Gravity.CENTER_HORIZONTAL);
            columna.setPadding(150,0,0,0);
            cabecera.addView(columna);

        TextView columna2 = new TextView(this);
        columna.setLayoutParams(new TableRow.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        columna.setText(cabeceras[1]);
        columna.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        columna.setGravity(Gravity.CENTER_HORIZONTAL);
        columna.setPadding(80,0,0,0);
        cabecera.addView(columna2);


        // Línea que separa la cabecera de los datos
        separador_cabecera = new TableRow(this);
        separador_cabecera.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        FrameLayout linea_cabecera = new FrameLayout(this);
        TableRow.LayoutParams linea_cabecera_params =
                new TableRow.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, 20);
        linea_cabecera_params.span = 6;
        linea_cabecera.setBackgroundColor(Color.parseColor("#FFFFFF"));
        separador_cabecera.addView(linea_cabecera, linea_cabecera_params);
        tabla.addView(separador_cabecera);

        // Array para los totales
        int valores_totales[] = {0};
        // Filas de datos
        for (int f = 0; f < 9; f++)
        {
             fila = new TableRow(this);
            for (int c = 0; c=2) valores_totales[c - 2] += Integer.parseInt(datos[f][c]);

            tabla.addView(fila);
        }
        setContentView(tabla);
        }
        }*/


