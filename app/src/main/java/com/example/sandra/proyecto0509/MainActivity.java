package com.example.sandra.proyecto0509;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.sandra.proyecto0509.VariablesGlobales.contador;
import static com.example.sandra.proyecto0509.VariablesGlobales.maximodb;
import static com.example.sandra.proyecto0509.VariablesGlobales.minimodb;


public class MainActivity extends AppCompatActivity
{

    Button start;
    Button stop;

    float umbral=-1;
    ConstraintLayout layout,constraint;
    LineChart miChart;
    TextView minimoValor;
    TextView maximoValor;
    TextView medioValor;
    TextView curvaValor;
    boolean esChart=false;
    boolean refresh=false;

    private SeekBar barra1;
    private TextView mostrarPorcentaje;

    long temporizador=0;

    MediaPlayer mediaplayer;

    ImageView mas;
    ImageView menos;
    Button reiniciar;





    private boolean listening=true;
    private boolean hilo=true;
    private Thread thread;
    float volumen=10000;
    long tiempo=0;
    long tiempoconcurrente=0;
    ArrayList<Entry> yVals;


    private Grabadora migrabador;

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            DecimalFormat df1 = new DecimalFormat("####.0");
            if(msg.what == 1){
                if(!esChart){
                    InicializamosGrafico();
                    return;
                }
                minimoValor.setText(df1.format(VariablesGlobales.minimodb));
                medioValor.setText(df1.format((VariablesGlobales.minimodb+ maximodb)/2));
                maximoValor.setText(df1.format(maximodb));
                curvaValor.setText(df1.format(contador));
                ActualizarDatos(contador,0);
                if(Calendar.getInstance().getTimeInMillis()-temporizador>3000) {
                    if (contador > umbral) {
                        //Toast.makeText(MainActivity.this, "nivel de ruido muy alto", Toast.LENGTH_SHORT).show();
                        mediaplayer.start();
                        constraint.setBackgroundColor(Color.RED);

                    }
                    temporizador=Calendar.getInstance().getTimeInMillis();
                    if(contador<umbral){
                        constraint.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        }
    };


    public void PararGrafico(){
       // layout=findViewById(R.id.layout_DB);
        layout.setVisibility(View.INVISIBLE);
        miChart.setVisibility(View.INVISIBLE);

    }
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //temporizador
        temporizador=Calendar.getInstance().getTimeInMillis();
        //Permisos necesarios para poder grabar
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }
        layout=findViewById(R.id.layout_DB);
        constraint=findViewById(R.id.constraint);

        mediaplayer = MediaPlayer.create(this, R.raw.music);

        start=(Button)findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    File file = Archivo.createFile("temp.amr");
                    if (file != null) {
                        Grabar(file);
                        listening = true;
                        refresh=true;
                        VariablesGlobales.minimodb=100;
                        VariablesGlobales.ultimovalor=0;
                        //InicializamosGrafico();
                       // layout.setVisibility(View.VISIBLE);
                       // miChart.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getApplicationContext(), "error error error", Toast.LENGTH_LONG).show();
                    }

            }
        });

        stop=(Button)findViewById(R.id.btn_stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miChart.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.INVISIBLE);
                hilo=false;
                constraint.setBackgroundColor(Color.WHITE);
               //migrabador.PararGrabacion();
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
                umbral=(float)i;
                mostrarPorcentaje.setText(i + "db" + "/" + barra1.getMax() + "db");
                /*if(contador>=i){
                    Toast.makeText(getApplicationContext(),"probando probando ", Toast.LENGTH_LONG).show();
                }*/


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
               // Toast.makeText(getApplicationContext(),"Arrastrar ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                Toast.makeText(getApplicationContext(),"Establecido el limite " + mostrarPorcentaje.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        mas=findViewById(R.id.btn_mas);
        menos=findViewById(R.id.btn_menos);
        reiniciar=findViewById(R.id.btn_reiniciar);

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("¿Esta seguro que desea reiniciar el contador?")
                                .setTitle("Importante")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                        builder.show();
            }
        });







        //Declaramos la grabadora
        migrabador= new Grabadora();


        //Declaramos donde se van a almacenar los datos
        minimoValor=(TextView)findViewById(R.id.minval);
        medioValor=(TextView)findViewById(R.id.mmval);
        maximoValor=(TextView)findViewById(R.id.maxval);
        curvaValor=(TextView)findViewById(R.id.curval);
    }




    private void ActualizarDatos(float val, long time) {
        if(miChart==null){
            return;
        }
        if (miChart.getData() != null &&
                miChart.getData().getDataSetCount() > 0) {
            LineDataSet set1 = (LineDataSet)miChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals);
            Entry entry=new Entry(tiempo,val);
            set1.addEntry(entry);
            if(set1.getEntryCount()>200){
                set1.removeFirst();
                set1.setDrawFilled(false);
            }
            miChart.getData().notifyDataChanged();
            miChart.notifyDataSetChanged();
            miChart.invalidate();
            tiempo++;
        }
    }

    private void InicializamosGrafico()
    {
        if(miChart!=null){
            if (miChart.getData() != null &&
                    miChart.getData().getDataSetCount() > 0) {
                tiempo++;
                esChart=true;
            }
        }else{
            tiempoconcurrente=new Date().getTime();
            miChart = (LineChart) findViewById(R.id.grafico);
            miChart.setViewPortOffsets(100, 20, 5, 60);
            // no description text
            miChart.setDescription(null);
            // enable touch gestures
            miChart.setTouchEnabled(false);
            // enable scaling and dragging
            miChart.setDragEnabled(false);
            miChart.setScaleEnabled(true);
            // if disabled, scaling can be done on x- and y-axis separately
            miChart.setPinchZoom(false);
            miChart.setDrawGridBackground(false);
            //mChart.setMaxHighlightDistance(400);
            XAxis x = miChart.getXAxis();
            x.setLabelCount(10, false);
            x.setEnabled(true);
            x.setTextColor(Color.BLACK);
            x.setPosition(XAxis.XAxisPosition.BOTTOM);
            x.setDrawGridLines(true);
            x.setAxisLineColor(Color.CYAN);
            YAxis y = miChart.getAxisLeft();
            y.setLabelCount(4, false);
            y.setTextColor(Color.BLACK);
            y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            y.setDrawGridLines(false);
            y.setAxisLineColor(Color.CYAN);
            y.setAxisMinValue(25);
            y.setAxisMaxValue(200);
            miChart.getAxisRight().setEnabled(true);
            yVals = new ArrayList<Entry>();
            yVals.add(new Entry(20,0));
            LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.02f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setCircleColor(Color.YELLOW);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.CYAN);
            set1.setFillColor(Color.MAGENTA);
            set1.setFillAlpha(300);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return -10;
                }
            });
            LineData data;
            if (miChart.getData() != null &&
                    miChart.getData().getDataSetCount() > 0) {
                data =  miChart.getLineData();
                data.clearValues();
                data.removeDataSet(0);
                data.addDataSet(set1);
            }else {
                data = new LineData(set1);
            }

            data.setValueTextSize(9f);
            data.setDrawValues(false);
            miChart.setData(data);
            miChart.getLegend().setEnabled(false);
            miChart.animateXY(1000, 1000);
            // dont forget to refresh the drawing
            miChart.invalidate();
            esChart=true;
        }

    }

    private void EscucharAudio() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (hilo) {
                    try {
                        if (listening) {
                            volumen = migrabador.ObtenerAmplitudMax();  //Get the sound pressure value
                            if (volumen > 0 && volumen < 1000000) {
                                VariablesGlobales.PonerContadorDB(20 * (float) (Math.log10(volumen)));  //Change the sound pressure value to the decibel value
                                // Update with thread
                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                            }
                        }
                        if (refresh) {

                            Thread.sleep(1200);
                            refresh = false;
                        } else {
                            Thread.sleep(200);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        listening = false;
                    }
                }
                return;
            }
        });
        thread.start();
    }
    /**
     * Start recording
     * @param fFile
     */
    public void Grabar(File fFile){
        try{
            migrabador.AsignarArchivoAudio(fFile);
            if (migrabador.EmpezarGrabar()) {
                EscucharAudio();
            }else{
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, "error error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        /*File file = Archivo.createFile("temp.amr");
        if (file != null) {
            Grabar(file);
        } else {
            Toast.makeText(getApplicationContext(), "error error error", Toast.LENGTH_LONG).show();
        }
        listening = true;*/
    }

    /**
     * Stop recording
     */
    @Override
    protected void onPause() {
        super.onPause();
        listening = false;
        migrabador.BorrarAudio(); //Stop recording and delete the recording file
        thread = null;
        esChart=false;
    }

    @Override
    protected void onDestroy() {
        if (thread != null) {
            hilo = false;
            thread = null;
        }
        migrabador.BorrarAudio();
        super.onDestroy();
    }
}


