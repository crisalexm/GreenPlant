package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private EditText nombre, correo, apellido, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.etNombre);
        correo = findViewById(R.id.etCorreo);
        apellido = findViewById(R.id.etApellido);
        password = findViewById(R.id.etPassword);
    }

    public void enviarDatos (View v){
        Intent z = new Intent(this, menuSeleccion.class);
        if (nombre.getText().toString().isEmpty() || correo.getText().toString().isEmpty() ||
                apellido.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
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