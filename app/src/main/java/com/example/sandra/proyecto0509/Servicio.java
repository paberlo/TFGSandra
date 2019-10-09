package com.example.sandra.proyecto0509;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

import androidx.annotation.Nullable;

import static com.example.sandra.proyecto0509.VariablesGlobales.contador;


public class Servicio extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int id_process){

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy(){

    }
}
