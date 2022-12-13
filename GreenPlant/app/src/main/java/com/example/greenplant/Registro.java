package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greenplant.Model.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

public class Registro extends AppCompatActivity {

    private EditText nombre, apellido;
    private TextView correoR, passwordR;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.etNombreUpd);
        apellido = findViewById(R.id.etApellido);
        correoR = findViewById(R.id.tvCorreoR);
        passwordR = findViewById(R.id.tvPasswordR);

        Bundle bundle = getIntent().getExtras();

        String correoFB = bundle.getString("emailFirebase");
        String passwordFB = bundle.getString("passwordFirebase");

        correoR.setText(correoFB);
        passwordR.setText(passwordFB);

        iniciarFireBase();
        cargarUsuario();
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

    public void enviarDatos (View v){
        Intent z = new Intent(this, menuSeleccion.class);
        //Array con usuarios
        ArrayList<Usuario> myUsuarioList = new ArrayList<Usuario>();
        if (!nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty()){
            z.putExtra("nombre", nombre.getText().toString());
            z.putExtra("apellido", apellido.getText().toString());
            startActivity(z);
        } else {
            Toast.makeText(this, "Los campos no pueden estar vacíos, favor rellenar la información", Toast.LENGTH_SHORT).show();
        }

    }

    private void iniciarFireBase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void cargarUsuario() {

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            String nombreUsr = bundle.getString("nombreUsr");
            String apellidoUsr = bundle.getString("apellidoUsr");
            String correoUsr = bundle.getString("emailFirebase");
            String passwordUsr = bundle.getString("passwordFirebase");

            nombre.setText(nombreUsr);
            apellido.setText(apellidoUsr);
            correoR.setText(correoUsr);
            passwordR.setText(passwordUsr);
        }
    }

    public void agregarUsuario(View v) {

       try{

           if (!nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty()){
               String nombreB = nombre.getText().toString();
               String correoB = correoR.getText().toString();
               String apellidoB = apellido.getText().toString();
               String passwordB = passwordR.getText().toString();

               Usuario usr = new Usuario();
               usr.setId(UUID.randomUUID().toString());
               usr.setName(nombreB);
               usr.setLastName(apellidoB);
               usr.setEmail(correoB);
               usr.setPassword(passwordB);

               Intent z = new Intent(this, menuSeleccion.class);

               databaseReference.child("Usuarios").child(usr.getId()).setValue(usr);
               Toast.makeText(this, "Usuario Cargado", Toast.LENGTH_SHORT).show();
               startActivity(z);
           } else {
               Toast.makeText(this, "Los campos no pueden estar vacíos, favor rellenar la información", Toast.LENGTH_SHORT).show();
           }
        } catch (Exception e)
        {
            Toast.makeText(this, "Ocurre un problema", Toast.LENGTH_SHORT).show();
        }

    }


}