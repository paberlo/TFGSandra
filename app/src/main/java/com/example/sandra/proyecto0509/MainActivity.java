package com.example.sandra.proyecto0509;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.MediaRecorder;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;



public class MainActivity extends AppCompatActivity
{

    private Button empezar;
    private Button parar;
    private SeekBar barra1;
    private TextView mostrarPorcentaje;
    private ProgressBar barracircular;


    private Grabadora migrabador;






    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permisos necesarios para poder grabar
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }

        //Asignamos los botones al layout
        empezar=(Button)findViewById(R.id.btn_empezar);
        parar=(Button)findViewById(R.id.btn_parar);

        //Asignamos el seekbar al layout
        barra1=(SeekBar)findViewById(R.id.seekBar1);


        //Asignamos el textveiw al layout
        mostrarPorcentaje=(TextView)findViewById(R.id.txtview_progreso);

        //Declaramos un maximo de hasta donde el seekbar puede llegar
        barra1.setMax(200);


        //Inicializo el valor inicial del progreso
        barra1.setProgress(0);

        barra1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                mostrarPorcentaje.setText(String.valueOf(i)+"db");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(getApplicationContext(),"Establecido el limite " + mostrarPorcentaje.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //Declaramos la barra de progreso
        barracircular=(ProgressBar)findViewById(R.id.progressBar);
        barracircular.setVisibility(View.INVISIBLE);

        empezar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                barracircular.setVisibility(View.VISIBLE);
            }
        });

        //Declaramos la grabadora
        migrabador= new Grabadora();
    }



    /*
    //GRABADORA
    //Declaramos el archivo para guardar la grabacion
    public File archivo;

    //Declaramos una variable de tipo MediaRecorder
    public MediaRecorder grabacion;

    //Declaramos una variable que "escucha" el audio
    public boolean escucha=false;

    //Metodo para obtener el maximo valor
    public float ObtenerAmplitudMax()
    {
        if(grabacion!=null)
        {
            try
            {
                return grabacion.getMaxAmplitude();
            }catch (IllegalArgumentException e){}
            return 0;
        }
        else
            return 5;
    }

    //Metodo que devuelve el archivo grabado
    public File ObtenerArchivoAudio()
    {
        return archivo;
    }

    //Metodo que asigna el audio
    public void AsignarArchivoAudio(File archivo)
    {
        this.archivo=archivo;
    }

    //Metodo utilizado para empezar a grabar
    public boolean EmpezarGrabar()
    {
        if(archivo==null)
            return false;
        try
        {
            /*Inicializamos la variable grabacion para poder grabar
             * pero antes de poder hacerlo hemos tenido que poner
             * algunos permisos en el android manifest*/
    /*
            grabacion=new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            grabacion.setOutputFile(archivo.getAbsolutePath());

            //Comenzamos la grabacion
            grabacion.prepare();
            grabacion.start();
            escucha=true;
            return true;
        }catch (IOException e)
        {
            grabacion.reset();
            grabacion.release();
            grabacion=null;
            escucha=false;
        }catch (IllegalStateException e)
        {
            PararGrabacion();
            escucha=false;
        }
        return false;
    }

    //Metodo que detiene la grabacion
    public void PararGrabacion()
    {
        if(grabacion!=null)
        {
            while(escucha)
            {
                try
                {
                    grabacion.stop();
                    grabacion.release();
                }catch (Exception e){}
            }
        }
        grabacion=null;
        escucha=false;
    }

    //Metodo que elimina el audio
    public void BorrarAudio()
    {
        PararGrabacion();
        while(archivo!=null)
        {
            archivo.delete();
            archivo=null;
        }
    }


    //MEDIDOR
    KdGaugeView speedView;
*/

}
