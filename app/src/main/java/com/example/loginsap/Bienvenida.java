package com.example.loginsap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bienvenida extends AppCompatActivity {
    EditText articulo, precio, manager;
    Button btnagregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        articulo=(EditText)findViewById(R.id.articulo);
        precio=(EditText)findViewById(R.id.precio);
        manager=(EditText)findViewById(R.id.manager);
        btnagregar=(Button)findViewById(R.id.btnagregar);


        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AgregarUsuario();
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

    public void AgregarUsuario(){
        try{
            PreparedStatement pst= conexionBD().prepareStatement("INSERT INTO OQUE(queueID, descript, manager) VALUES (?,?,?)");
            pst.setString(1,articulo.getText().toString());
            pst.setString(2,precio.getText().toString());
            pst.setString(3,manager.getText().toString());
            pst.executeUpdate();

            Toast.makeText(getApplicationContext(), "ARTICULO AGREGADO A LA BASE DE DATOS",Toast.LENGTH_SHORT).show();

        }catch (SQLException e){
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}