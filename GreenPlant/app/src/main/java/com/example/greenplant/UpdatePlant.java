package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.greenplant.Model.Planta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdatePlant extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Spinner spinOpUdp;
    private EditText apodoUdp;
    String idPl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_plant);
        spinOpUdp = findViewById(R.id.opcPlantUdp);
        apodoUdp = findViewById(R.id.etApodoUpd);
        iniciarFireBase();

    }

    private void iniciarFireBase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void updatePlantas(View v){
        Bundle bundle = getIntent().getExtras();
        String idPlant = bundle.getString("idPlantaUdp");
        Toast.makeText(this, "id Planta" + idPlant, Toast.LENGTH_SHORT).show();
        Planta planta = new Planta();
        planta.setId(idPlant);
        planta.setName(apodoUdp.getText().toString());
        planta.setFamilyName(spinOpUdp.getSelectedItem().toString());
        databaseReference.child("Planta").child(planta.getId()).setValue(planta);

        Toast.makeText(this, "Planta Modificada", Toast.LENGTH_SHORT).show();
    }
}