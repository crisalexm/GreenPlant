package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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

    String idPlant;
    String nombrePlanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_plant);
        spinOpUdp = findViewById(R.id.opPlant);
        apodoUdp = findViewById(R.id.etApodoUpd);
        iniciarFireBase();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null)
        {
            idPlant = bundle.getString("idPlanta");
            nombrePlanta = bundle.getString("nombre");
            apodoUdp.setText(nombrePlanta);
        }


    }

    private void iniciarFireBase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void updatePlantas(View v)
    {
        String nuevoApodo = apodoUdp.getText().toString();
        if(nuevoApodo != null && nuevoApodo.isEmpty())
        {
            Log.e("UpdatePlant", "Bundle vacio");
            return;
        }

        //Validar los campos previo a la operacion
        if(!validarCampos(spinOpUdp.getSelectedItem(), idPlant, nombrePlanta))
        {
            Toast.makeText(this, "Campos invalidos", Toast.LENGTH_SHORT).show();

            return;
        }

        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        try {
            Toast.makeText(this, "id Planta" + idPlant, Toast.LENGTH_SHORT).show();
            Planta planta12 = new Planta();
            planta12.setId(idPlant);
            planta12.setName(nuevoApodo);
            planta12.setFamilyName("");

            if(spinOpUdp.getSelectedItem() != null)
            {
                planta12.setFamilyName(spinOpUdp.getSelectedItem().toString());
            }

            databaseReference.child("Planta").child(idPlant).setValue(planta12);
        } catch (Exception e) {
            Log.e("error",e.toString());
            Toast.makeText(this, "No se pudo actualizar", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Planta Modificada", Toast.LENGTH_SHORT).show();
    }

    private boolean validarCampos(Object selectedItem, String idPlant, String nombrePlanta)
    {
//selectedItem == null ||
        if(idPlant == null || idPlant.isEmpty() || nombrePlanta == null || nombrePlanta.isEmpty())
        {
            return false;
        }
        return true;
    }
}
