package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menuSeleccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_seleccion);
    }

    public void aniadirPlanta (View v){
        Intent x = new Intent(this, aniadirPlanta.class);
        startActivity(x);
    }

    public void verMisPlantas (View v){
        Intent x = new Intent(this, misPlantas.class);
        startActivity(x);
    }
}