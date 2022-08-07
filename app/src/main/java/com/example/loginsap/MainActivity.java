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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    EditText correo, contraseña;
    Button btnentrar, btnolvidaste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo=(EditText)findViewById(R.id.correo);
        contraseña=(EditText)findViewById(R.id.contraseña);
        btnentrar=(Button)findViewById(R.id.btnentrar);
        btnolvidaste=(Button)findViewById(R.id.btnolvidaste);
       btnentrar.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
            validarLogin();
           }
        });

        Button button1=findViewById(R.id.btnolvidaste);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(MainActivity.this,ResContrasena.class);
                startActivity(intent2);
            }
        });
    }

    //Conexion con SQL Server
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


  //Metodo que valida el login
    public void validarLogin(){
        String usuario=correo.getText().toString();
        String clave=contraseña.getText().toString();
        String usua, pass;
        //int numeroIntentos=0, max=3;
        try{
            Statement stm=conexionBD().createStatement();
            //ResultSet rs=stm.executeQuery("IniciarSesionOUSR '"+usuario+"' '"+clave+"'");
           ResultSet rs=stm.executeQuery("select USER_CODE,U_NAME from OUSR where U_NAME='"+
                    usuario+"' and USER_CODE='"+clave+"'");
            //capturamos los valores del cursor y lo almacenamos en variable
            if (rs.next()) {
                usua = rs.getString(1);
                pass = rs.getString(2);
                //preguntamos si los datos ingresados son iguales
                    if (usuario.equals(usua) && clave.equals(pass)) {
                        //si son iguales entonces vamos a otra ventana
                        //Menu es una nueva actividad empty
                        Toast toast = Toast.makeText(this, "BIENVENIDO", Toast.LENGTH_LONG);
                        //mostramos el toast
                        toast.show();
                        Intent ab = new Intent(MainActivity.this, Opciones.class);
                        startActivity(ab);
                        //limpiamos las las cajas de texto
                        correo.setText("");
                        contraseña.setText("");
                    }
                    }else {
                Toast toast=Toast.makeText(this,"DATOS INCORRECTOS",Toast.LENGTH_LONG);
                //mostramos el toast
                toast.show();
                correo.setText("");
                contraseña.setText("");
                }
                       //si la primera condicion no cumple entonces que envie un mensaje toast

        } catch (Exception e) {//capturamos los errores que hubieran
            Toast toast=Toast.makeText(this,"Error" + e.getMessage(),Toast.LENGTH_LONG);
            //mostramos el mensaje
            toast.show();
        }
    }
}
