package com.example.loginsap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class ResContrasena extends AppCompatActivity {
  Button btnrecuperar;
  TextInputEditText correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_contrasena);
        btnrecuperar=(Button)findViewById(R.id.btnrecuperar);
        correo=(TextInputEditText)findViewById(R.id.correo);

        btnrecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valida();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent1 = new Intent(ResContrasena.this, MainActivity.class);
        startActivity(intent1);
        finish();
    }

    public void valida() {
        String email = correo.getText().toString().trim();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            correo.setError("Correo Invalido");
            return;
        }
        //sendEmail(email);
    }
}