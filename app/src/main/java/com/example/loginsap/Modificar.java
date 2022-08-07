package com.example.loginsap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modificar extends AppCompatActivity {
    EditText editupdate, editarticulo, editdescripcion;
    Button modificar, btncomprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        editupdate=(EditText)findViewById(R.id.editupdate);
        editarticulo=(EditText)findViewById(R.id.editarticulo);
        editdescripcion=(EditText)findViewById(R.id.editdescripcion);
        modificar=(Button)findViewById(R.id.modificar);
        btncomprobar=(Button)findViewById(R.id.btncomprobar);

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarArticulo();
            }
        });

        btncomprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten8=new Intent(Modificar.this,Restablecer.class);
                startActivity(inten8);
            }
        });

    }

    public Connection conexionBD() {
        Connection conexion=null;
        try{
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //Conexion a SQL SERVER
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion= DriverManager.getConnection("jdbc:jtds:sqlserver://10.168.100.10;databaseName=SBODemoMX;user=sa;password=sasa");
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }

    public void modificarArticulo(){
        try{
            Statement stm=conexionBD().createStatement();
            ResultSet rs=stm.executeQuery("UPDATE OQUE SET queueID='"+editarticulo.getText().toString()+"', descript='"+editdescripcion.getText().toString()+"' WHERE manager='"+editupdate.getText().toString()+"'");
           editupdate.setText("");
            Toast.makeText(getApplicationContext(), "MODIFICADO CON EXITO",Toast.LENGTH_SHORT).show();
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(), "MODIFICADO EXITO",Toast.LENGTH_SHORT).show();
        }
    }
}