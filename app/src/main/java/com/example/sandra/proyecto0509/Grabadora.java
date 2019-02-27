package com.example.sandra.proyecto0509;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;

public class Grabadora
{
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
                    //grabacion.release();
                }catch (Exception e){}
            }
        }
       // grabacion=null;
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
}
