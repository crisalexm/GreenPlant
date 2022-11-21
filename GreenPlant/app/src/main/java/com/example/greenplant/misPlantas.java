package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class misPlantas extends AppCompatActivity {

    private TextView mostrar, mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_plantas);

        Bundle bundle = getIntent().getExtras();

        String apodo = bundle.getString("apodo");
        String opcPlanta = bundle.getString("spTipo");
        mostrar = findViewById(R.id.tvResultado);
        mensaje = findViewById(R.id.tvMensaje);

        mostrarDatosPlanta(opcPlanta, apodo);
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
                case R.id.mVolver:
                Intent z = new Intent(this, menuSeleccion.class);
                startActivity(z);
                break;

            case R.id.mSalir:
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void mostrarDatosPlanta(String op, String apod){
        double ran = (Math.random()*100+1);
        double tem = Math.random()*38+1;
        if(ran > 60 && tem > 30){
            mensaje.setText("Tu planta no necesita agua aún y la temperatura es la máxima letal es muy calurosa!");
        } else if ( ran > 60 && tem > 12){
            mensaje.setText("Tu planta no necesita agua aún y la temperatura es la óptima");
        } else if (ran > 60 && tem > 0 ){
            mensaje.setText("Tu planta no necesita agua aún y la temperatura es la miníma letal es muy frio");
        } else if (ran > 40 && tem > 30) {
            mensaje.setText("Tu planta NECESITA agua y la temperatura es la máxima letal es muy calurosa!");
        } else if (ran > 40 && tem > 12) {
            mensaje.setText("Tu planta NECESITA agua y la temperatura es la óptima");
        } else if (ran > 40 && tem > 0) {
            mensaje.setText("Tu planta NECESITA agua y la temperatura es la miníma letal es muy frio");
        } else if (ran > 0 && tem > 30) {
            mensaje.setText("Tu planta NECESITA agua URGENTE porque esta seca y la temperatura es la máxima letal es muy calurosa!! Podría secarse tu planta");
        } else if (ran > 0 && tem > 12) {
            mensaje.setText("Tu planta NECESITA agua URGENTE y la temperatura es la óptima");
        } else if (ran > 0 && tem > 0) {
            mensaje.setText("Tu planta NECESITA agua URGENTE y la temperatura es la miníma letal es muy frio");
        }

        mostrar.setText("Apodo: " + apod + " \nNombre: " + op + " \nHumedad: "
                + String.format("%.0f", ran) + "% \nTemperatura: " + String.format("%.0f", tem) + "°c");
    }

}