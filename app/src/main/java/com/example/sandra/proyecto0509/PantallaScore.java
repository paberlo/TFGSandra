package com.example.sandra.proyecto0509;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class PantallaScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_score);

        Toast.makeText(getApplicationContext(), "Otra aplicacion", Toast.LENGTH_LONG).show();

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Metodo para volver a la pantalla anterior
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
