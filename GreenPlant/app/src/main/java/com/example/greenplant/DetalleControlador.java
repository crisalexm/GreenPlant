package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetalleControlador extends AppCompatActivity {

    TextView resultado;
    String hum;
    String tem;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_controlador);

        resultado = findViewById(R.id.tvResultadoContr);
        Bundle bundle = getIntent().getExtras();

        iniciarFireBase();

        String humd = bundle.getString("humedad");
        String temp = bundle.getString("temperatura");
        hum = humd;
        tem = temp;
        DatosControlador(hum, tem);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.mVolver:
                Intent z = new Intent(this, DetallePlanta.class);
                startActivity(z);
                break;

            case R.id.mSalir:
                finishAffinity();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void DatosControlador(String hum, String tmp){
        resultado.setText("Humedad: " + hum + " \nTemperatura: " + tmp);
    }

    private void iniciarFireBase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}