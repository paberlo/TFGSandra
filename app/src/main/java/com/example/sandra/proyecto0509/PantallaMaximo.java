package com.example.sandra.proyecto0509;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.sandra.proyecto0509.VariablesGlobales.maximodb;

public class PantallaMaximo extends AppCompatActivity {

    private SeekBar barra1;
    private TextView mostrarPorcentaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_maximo);


        Button siguiente= findViewById(R.id.btn_siguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PantallaMaximo.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Asignamos el seekbar al layout
        barra1=(SeekBar)findViewById(R.id.seekBar1);
        //Asignamos el textveiw al layout
        mostrarPorcentaje=(TextView)findViewById(R.id.txtview_progreso);


        //Inicializo el valor inicial del progreso
        barra1.setProgress(0);

        mostrarPorcentaje.setText(barra1.getProgress() + "db" + "/" + barra1.getMax() + "db");
        barra1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                mostrarPorcentaje.setText(i + "db" + "/" + barra1.getMax() + "db");
                if(maximodb>=i){
                    Toast.makeText(getApplicationContext(),"probando probando ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(getApplicationContext(),"Arrastrar ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(getApplicationContext(),"Establecido el limite " + mostrarPorcentaje.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }





}
