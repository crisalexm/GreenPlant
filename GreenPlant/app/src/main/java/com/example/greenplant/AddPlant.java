package com.example.greenplant;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.greenplant.Model.Planta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AddPlant extends AppCompatActivity {

    private Spinner spinOp;
    private EditText apodo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference  databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);


        spinOp = findViewById(R.id.opcionesPlanta1);
        apodo = findViewById(R.id.etNombre);
        // Array con Opciones
        String[] options = {"Árbol de Jade - Moraceae", "Árbol Lira- Moraceae", "Aloe Vera - Xanthorrhoeaceae", "Anturio Rojo - Araceae"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        spinOp.setAdapter(adapter);

        iniciarFireBase();
        cargarPlanta();
    }


    private void iniciarFireBase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void cargarPlanta() {

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            String apodoB = bundle.getString("nombre");
            String spinnerPlantasB = spinOp.getSelectedItem().toString();
            spinnerPlantasB = bundle.getString("nombreFamilia");
            String idPlantaB = bundle.getString("idPlanta");

            apodo.setText(apodoB);
        }
    }

    public void agregar(View v) {

        try {
            String apd = apodo.getText().toString();
            String spinnerPl = spinOp.getSelectedItem().toString();

            Planta pl = new Planta();

            pl.setName(apd);
            pl.setFamilyName(spinnerPl);
            pl.setId(UUID.randomUUID().toString());

            databaseReference.child("Planta").child(pl.getId()).setValue(pl);
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
        } catch (Exception e)
        {
            Toast.makeText(this, "Ocurre un problema", Toast.LENGTH_SHORT).show();
        }

    }
    public void verLista(View v){
        Intent i = new Intent(this, MyPlants.class);
        startActivity(i);
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
}