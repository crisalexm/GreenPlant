package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InicioSesion extends AppCompatActivity {

    //private EditText correo, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        //correo = findViewById(R.id.etApellido);
        //password = findViewById(R.id.etPassword);
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
                Intent z = new Intent(this, MainActivity.class);
                startActivity(z);
                break;

            case R.id.mSalir:
                finishAffinity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void iniciandoSesion (View v) {
        Intent z = new Intent(this, menuSeleccion.class);
        //if (correo.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
        //  z.putExtra("correo", correo.getText().toString());
        //z.putExtra("password", password.getText().toString());
        //startActivity(z);
        //} else {
        //  Toast.makeText(this, "Los campos no pueden estar vacíos, favor rellenar la información", Toast.LENGTH_SHORT).show();
        // }
        startActivity(z);
    }
}