package com.example.greenplant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Authentication extends AppCompatActivity {

    private EditText email, password1, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

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

                String pass1 = password1.getText().toString();
                String pass2 = password2.getText().toString();

                Toast.makeText(this, "password1: " + pass1, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "password2: " + pass2, Toast.LENGTH_SHORT).show();

                if (password1.getText().toString().equals(password2.getText().toString())) {

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
}