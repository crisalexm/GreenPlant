package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Iniciar Sesion
    public void iniciarSesion (View v){
        Intent x = new Intent(this, InicioSesion.class);
        startActivity(x);
    }

    // Registrarse
    public void registrarse (View v){
        Intent x = new Intent(this, Authentication.class);
        startActivity(x);
    }
}