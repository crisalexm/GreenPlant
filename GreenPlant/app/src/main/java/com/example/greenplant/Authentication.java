package com.example.greenplant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Authentication extends AppCompatActivity {

    private EditText email, password1, password2;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.etCorreoAuth);
        password1 = findViewById(R.id.etPasswordAuth1);
        password2 = findViewById(R.id.etPasswordAuth2);
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

    public void registrarEmailPassword(View v){
        if (!email.getText().toString().isEmpty() && !password1.getText().toString().isEmpty() && !password2.getText().toString().isEmpty())
        {
            try {

                String email1 = email.getText().toString().trim();
                String pass1 = password1.getText().toString().trim();
                String pass2 = password2.getText().toString().trim();

                if (password1.getText().toString().equals(password2.getText().toString())) {
                    registerUser(email1, pass1);
                    Intent t = new Intent(this, Registro.class);
                    t.putExtra("emailFirebase", email.getText().toString());
                    t.putExtra("passwordFirebase", password1.getText().toString());
                    startActivity(t);
                }  else {
                    Toast.makeText(this, "Las contraseñas no coiciden, Intentelo Nuevamente!!!", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(this, "Problemas", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Los campos de email y password no pueden estar vacíos", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerUser(String email1, String pass1) {
        mAuth.createUserWithEmailAndPassword(email1, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("email", email1);
                map.put("password", pass1);

                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        startActivity(new Intent(Authentication.this, Registro.class));
                        Toast.makeText(Authentication.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Authentication.this, "Error al Guardar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Authentication.this, "Error al Registrar en BD", Toast.LENGTH_SHORT).show();
            }
        });
    }
}