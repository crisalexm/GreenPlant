package com.example.greenplant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.greenplant.Model.Planta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyPlants extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    private List<Planta> listaPlantas = new ArrayList<Planta>();
    ArrayAdapter<Planta> arrayAdapterPlanta;
    Planta selectPlanta;
    ListView lvPlanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plants);

        lvPlanta = findViewById(R.id.lvPlantas);
        iniciarFireBase();
        cargarLista();


        lvPlanta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyPlants.this, DetallePlanta.class);
                selectPlanta = (Planta)adapterView.getItemAtPosition(i);

                intent.putExtra("nombre", selectPlanta.getName());
                intent.putExtra("nombreFamilia", selectPlanta.getFamilyName());
                intent.putExtra("idPlanta", selectPlanta.getId());

                startActivity(intent);
            }
        });

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

    private void iniciarFireBase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void cargarLista() {
        databaseReference.child("Planta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaPlantas.clear();

                for (DataSnapshot dataSnap : snapshot.getChildren()) {
                    Planta pl = dataSnap.getValue(Planta.class);
                    listaPlantas.add(pl);
                }

                arrayAdapterPlanta = new ArrayAdapter<Planta>(MyPlants.this, android.R.layout.simple_list_item_1, listaPlantas);
                lvPlanta.setAdapter(arrayAdapterPlanta);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}