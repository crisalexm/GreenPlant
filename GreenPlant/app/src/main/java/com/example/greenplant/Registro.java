package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {

    private EditText nombre, correo, apellido, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.etNombreComun1);
        correo = findViewById(R.id.etCorreo);
        apellido = findViewById(R.id.etNombreCientifico);
        password = findViewById(R.id.etPassword);
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
                // Crear objeto usuario y asignarle varibles
            Usuario usuario = new Usuario();
            usuario.setName(nombre.getText().toString());
            usuario.setEmail(correo.getText().toString());
            usuario.setPassword(password.getText().toString());
                // Añadir usuario a la lista
            myUsuarioList.add(usuario);

            z.putExtra("nombre", nombre.getText().toString());
            z.putExtra("correo", correo.getText().toString());
            z.putExtra("apellido", apellido.getText().toString());
            z.putExtra("password", password.getText().toString());
            startActivity(z);
        } else {
            Toast.makeText(this, "Los campos no pueden estar vacíos, favor rellenar la información", Toast.LENGTH_SHORT).show();
        }

    }
}