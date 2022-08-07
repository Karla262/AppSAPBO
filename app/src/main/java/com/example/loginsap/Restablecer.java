package com.example.loginsap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Restablecer extends AppCompatActivity {
    EditText editbuscar, articulo, de;
    Button buscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer);

        editbuscar=(EditText) findViewById(R.id.editbuscar);
        articulo=(EditText) findViewById(R.id.articulo);
        de=(EditText) findViewById(R.id.de);
        buscar=(Button)findViewById(R.id.buscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ConsultarID();
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

    public void ConsultarID(){
        try{
            Statement stm=conexionBD().createStatement();
            ResultSet rs=stm.executeQuery("Select * From OQUE where manager='"+editbuscar.getText().toString()+"'");
            if (rs.next()) {
                    articulo.setText(rs.getString(1));
                    de.setText(rs.getString(2));
            }
            editbuscar.setText("");

            //Toast.makeText(getApplicationContext(), "Articulo Encontrado",Toast.LENGTH_SHORT).show();

        }catch (SQLException e){
            //Toast.makeText(getApplicationContext(), "Sin Registros",Toast.LENGTH_SHORT).show();
        }
    }
}