package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;

public class AddPlant extends AppCompatActivity {

    private Spinner spinOp;
    private TextView apodo;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference  databaseReference;
    String mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        spinOp=findViewById(R.id.opcionesPlanta1);
        apodo = findViewById(R.id.etNombreComun1);
        // Array con Opciones
        String[] options = {"Árbol de Jade - Moraceae", "Árbol Lira- Moraceae", "Aloe Vera - Xanthorrhoeaceae", "Anturio Rojo - Araceae"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        spinOp.setAdapter(adapter);

        iniciarFireBase();
        cargarPlanta();
    }

    private void cargarPlanta() {

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            String apodo = bundle.getString("apodo");
            String spinnerPlantas = spinOp.getSelectedItem().toString();
            spinnerPlantas = bundle.getString("spinnerPlantas");
            //databaseReference.child("Alumno").child()
        }
    }

    private void iniciarFireBase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void agregar(View v) {

        String apd = apodo.getText().toString();
        String spinnerPl = spinOp.getSelectedItem().toString();


        Planta p = new Planta();
        p.setName(apd);
        p.setFamilyName(spinnerPl);

        databaseReference.child("Planta").child(p.getName()).setValue(p);
        Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
    }
    public void verLista(View v){

        Intent i = new Intent(this, MyPlants.class);
        startActivity(i);
    }

    public void editar(View view){
        Planta p = new Planta();
        p.setName(apodo.getText().toString());
        p.setFamilyName(spinOp.getSelectedItem().toString());

        databaseReference.child("Planta").child(p.getName()).setValue(p);
        Toast.makeText(this, "Planta Modificada", Toast.LENGTH_SHORT).show();
    }
}