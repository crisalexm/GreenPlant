package com.example.greenplant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.greenplant.Model.Controlador;
import com.example.greenplant.Model.Planta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.FirebaseApp;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetallePlanta extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    private ValueEventListener eventListener;

    TextView resultado, mensaje;
    String idPlant, humedad1, temp;
    String nombrePlant;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_planta);

        resultado = findViewById(R.id.tvResultado);
        mensaje = findViewById(R.id.tvMensaje);

        iniciarFireBase();

        Bundle bundle = getIntent().getExtras();

        String nombrePlanta = bundle.getString("nombre");
        String nombreFamilia = bundle.getString("nombreFamilia");
        String idPlanta = bundle.getString("idPlanta");
        idPlant = idPlanta;
        nombrePlant = nombrePlanta;

        mostrarDatosPlanta(nombreFamilia, nombrePlanta);


    }
    private void iniciarFireBase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
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
                Intent z = new Intent(this, MyPlants.class);
                startActivity(z);
                break;

            case R.id.mSalir:
                finishAffinity();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    public void mostrarDatosPlanta(String op, String apod){
        Controlador cont = new Controlador();
        cont.setHumStr(String.valueOf(mDatabase.child("Humidity").child("Humidity").get()));
        cont.setTemStr(String.valueOf(mDatabase.child("Temperature").child("Temperature").get()));
        double ran = cont.getHumedadTierra();
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

        // Disparar la funcion mostrandola en el textView de registro
        resultado.setText("Apodo: " + apod + " \nNombre: " + op + " \nHumedad: "
                + String.format("%.0f", ran) + "% \nTemperatura: " + String.format("%.0f", tem) + "°c");
    }
    public void updatePlant(View v){
        Intent i = new Intent(this, UpdatePlant.class);
        i.putExtra("idPlanta", idPlant);
        i.putExtra("nombre", nombrePlant);
        startActivity(i);
    }


    public void deletePlanta(View v) {
        try {
            Bundle bundle = getIntent().getExtras();
            String idPlantaDelete = bundle.getString("idPlanta");
            mDatabase.child("Planta").child(idPlantaDelete).removeValue();
            Toast.makeText(this, "Planta eliminada con exito", Toast.LENGTH_SHORT).show();
            Intent u = new Intent(this, MyPlants.class);
            startActivity(u);
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }




}