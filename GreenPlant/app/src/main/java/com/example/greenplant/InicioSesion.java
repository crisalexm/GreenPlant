package com.example.greenplant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InicioSesion extends AppCompatActivity {

    private EditText correo, password;

    // FB
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        correo = findViewById(R.id.etCorreo);
        password = findViewById(R.id.etPasswordRegistro);

        //FB
        mAuth = FirebaseAuth.getInstance();
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
    //FB
    public void userLogin(View v){
        String mailL = correo.getText().toString().trim();
        String passwordL = password.getText().toString().trim();

        if (mailL.isEmpty() || passwordL.isEmpty()){
            Toast.makeText(this, "Favor ingresar los datos de Ingreso", Toast.LENGTH_SHORT).show();
        } else {
            loginUser(mailL, passwordL);
        }
    }

    private void loginUser(String mailL, String passwordL) {
        mAuth.signInWithEmailAndPassword(mailL, passwordL).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(InicioSesion.this, menuSeleccion.class));
                    Toast.makeText(InicioSesion.this, "Bienvenido a su sesión!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InicioSesion.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(InicioSesion.this, "Error al Iniciar Sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cerrarSesion(View v){
        mAuth.signOut();
    }

    public void iniciandoSesion (View v) {
        Intent z = new Intent(this, menuSeleccion.class);
        if (!correo.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
          z.putExtra("correo", correo.getText().toString());
          z.putExtra("password", password.getText().toString());
          startActivity(z);
        } else {
             Toast.makeText(this, "Los campos no pueden estar vacíos, favor rellenar la información", Toast.LENGTH_SHORT).show();
        }
    }
}