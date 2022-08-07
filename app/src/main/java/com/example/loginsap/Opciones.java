package com.example.loginsap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Opciones extends AppCompatActivity {
    Button btnagregar, btnbuscar, btnborrar, btnmodificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        btnagregar=(Button)findViewById(R.id.btnagregar);
        btnbuscar=(Button) findViewById(R.id.btnbuscar);
        btnborrar=(Button) findViewById(R.id.btnborrar);
        btnmodificar=(Button) findViewById(R.id.btnmodificar);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Opciones.this,Bienvenida.class);
                startActivity(intent1);
            }
        });

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Opciones.this,Restablecer.class);
                startActivity(intent2);
            }
        });

        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(Opciones.this,Borrar.class);
                startActivity(intent3);
            }
        });
        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(Opciones.this,Modificar.class);
                startActivity(intent4);
            }
        });
    }
}