package com.example.loginsap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Borrar extends AppCompatActivity {
    EditText idelete;
    Button btnborrar, comp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);
        idelete=(EditText) findViewById(R.id.idelete);
        btnborrar=(Button)findViewById(R.id.btnborrar);
        comp=(Button)findViewById(R.id.comp);
        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarId();}
        });
        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Borrar.this,Restablecer.class);
                startActivity(intent); }
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
        return conexion; }

    public void borrarId(){
        try{
            PreparedStatement pst= conexionBD().prepareStatement("DELETE FROM OQUE WHERE manager="+idelete.getText().toString()+"");
             pst.executeUpdate();
            idelete.setText("");
            Toast.makeText(getApplicationContext(), "ELIMINADO ",Toast.LENGTH_SHORT).show();
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(), "FALLO",Toast.LENGTH_SHORT).show(); }
    }
}