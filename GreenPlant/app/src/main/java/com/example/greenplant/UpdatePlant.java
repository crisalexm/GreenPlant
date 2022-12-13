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
        spinOpUdp = findViewById(R.id.opPlant);
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
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        try {
            Toast.makeText(this, "id Planta" + idPlant, Toast.LENGTH_SHORT).show();
            Planta planta12 = new Planta();
            planta12.setId(idPlant);
            planta12.setName(apodoUdp.getText().toString());
            planta12.setFamilyName(spinOpUdp.getSelectedItem().toString());

            databaseReference.child("Planta").child(idPlant).setValue(planta12);
        } catch (Exception e) {
            Toast.makeText(this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Planta Modificada", Toast.LENGTH_SHORT).show();
    }
}
