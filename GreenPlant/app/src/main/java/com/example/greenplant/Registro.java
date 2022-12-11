package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greenplant.MainActivity;
import com.example.greenplant.Model.Planta;
import com.example.greenplant.Model.Usuario;
import com.example.greenplant.R;
import com.example.greenplant.menuSeleccion;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

public class Registro extends AppCompatActivity {

    private EditText nombre, correo, apellido, password;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.etNombreComun1);
        correo = findViewById(R.id.etCorreo);
        apellido = findViewById(R.id.etNombreCientifico);
        password = findViewById(R.id.etPassword);

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
        if (!nombre.getText().toString().isEmpty() && !correo.getText().toString().isEmpty() &&
                !apellido.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
            z.putExtra("nombre", nombre.getText().toString());
            z.putExtra("correo", correo.getText().toString());
            z.putExtra("apellido", apellido.getText().toString());
            z.putExtra("password", password.getText().toString());
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
            String correoUsr = bundle.getString("correoUsr");
            String passwordUsr = bundle.getString("passwordUsr");

            nombre.setText(nombreUsr);
            apellido.setText(apellidoUsr);
            correo.setText(correoUsr);
            password.setText(passwordUsr);
        }
    }

    public void agregarUsuario(View v) {

       try{
           String nombreB = nombre.getText().toString();
           String correoB = correo.getText().toString();
           String apellidoB = apellido.getText().toString();
           String passwordB = password.getText().toString();

           Usuario usr = new Usuario();

           usr.setName(nombreB);
           usr.setLastName(apellidoB);
           usr.setEmail(correoB);
           usr.setPassword(passwordB);

           Intent z = new Intent(this, menuSeleccion.class);
           if (!nombre.getText().toString().isEmpty() && !correo.getText().toString().isEmpty() &&
                   !apellido.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
               z.putExtra("nombre", nombre.getText().toString());
               z.putExtra("correo", correo.getText().toString());
               z.putExtra("apellido", apellido.getText().toString());
               z.putExtra("password", password.getText().toString());
               databaseReference.child("Usuarios").child(usr.getEmail()).setValue(usr);
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