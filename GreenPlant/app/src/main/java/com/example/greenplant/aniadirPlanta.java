package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class aniadirPlanta extends AppCompatActivity {
    private Spinner spinOp;
    private TextView apodo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_planta);
        spinOp=findViewById(R.id.opcionesPlanta);
        apodo = findViewById(R.id.etNombreComun);
        // Array con Opciones
        String[] options = {"Árbol de Jade - Moraceae", "Árbol Lira- Moraceae", "Aloe Vera - Xanthorrhoeaceae", "Anturio Rojo - Araceae"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        spinOp.setAdapter(adapter);

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

    public void registrarPlanta (View v){
        Intent z = new Intent(this, misPlantas.class);
        String opc = spinOp.getSelectedItem().toString();
        Planta myPlant = new Planta();


        if (!apodo.getText().toString().isEmpty()){
            myPlant.setName(apodo.getText().toString());
            myPlant.setFamilyName(opc);

            //1z.putExtra("spTipo",opc);
            //2z.putExtra("apodo", apodo.getText().toString());
            Bundle bundle = new Bundle();
            bundle.putSerializable("planta",myPlant);
            z.putExtras(bundle);
            startActivity(z);
        }
    }
}