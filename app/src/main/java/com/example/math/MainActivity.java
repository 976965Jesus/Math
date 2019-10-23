package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Atributos externos (objetos de clases, datos de otra pantalla, etc)
    Alumno alumno = new Alumno();

    //Atributos internos del frame (Propios de la pantalla)
    EditText text;
    Button btn;
    TextView registro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();


        btn = (Button) findViewById(R.id.btn_login);
        text = (EditText) findViewById(R.id.edt_usuario);
        registro = (TextView) findViewById(R.id.lbd_registro);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text.getText().toString().isEmpty()){
                    //Toast.makeText(MainActivity.this, "Introduce un numero de cuenta", Toast.LENGTH_SHORT).show();
                    ShowPopup("Por favor ingrese un numero de cuenta valido","Usuario no valido","error","continua");
                }else {
                    Validar();
                }
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar();
            }
        });

    }

    //Metodo para validar y dar acceso a la app
    public void Validar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BaseApp", null, 1);
        SQLiteDatabase dataBase =  admin.getWritableDatabase();

        String stringNumCuenta = text.getText().toString();
        int numCuenta = Integer.parseInt(stringNumCuenta);

        if(!stringNumCuenta.isEmpty()){
            Cursor fila = dataBase.rawQuery("select NumCuenta, Nombre, ApellidoPaterno, ApellidoMaterno from Alumno where NumCuenta = " + numCuenta, null);

            if(fila.moveToFirst()){//los index es por numeracion de la consulta igual que en java
                alumno.setNumCuenta(fila.getInt(0));
                alumno.setNombre(fila.getString(1));
                alumno.setApellidoP(fila.getString(2));
                alumno.setApellidoM(fila.getString(3));
                dataBase.close();
                //Toast.makeText(this, "NumCuenta: " + alumno.getNumCuenta() + " Nombre: " + alumno.getNombre() + " " + alumno.getApellidoP() + " " + alumno.getApellidoM(), Toast.LENGTH_LONG).show();
                Seperacion();
            } else {
                //Toast.makeText(this, "No Existe el Alumno en Nuestra Base", Toast.LENGTH_SHORT).show();
                ShowPopup("Si no tiene un numero de cuenta registrese por favor","Usuario no valido","error","continua");
                dataBase.close();
            }
        }

    }//Fin del metodo validar


    //Metodo para separa los numeros
    public void Seperacion(){
        int numCuenta = alumno.getNumCuenta();

        alumno.setDigito6(numCuenta%10);

        numCuenta = numCuenta/10;
        alumno.setDigito5(numCuenta%10);

        numCuenta = numCuenta/10;
        alumno.setDigito4(numCuenta%10);

        numCuenta = numCuenta/10;
        alumno.setDigito3(numCuenta%10);

        numCuenta = numCuenta/10;
        alumno.setDigito2(numCuenta%10);

        numCuenta = numCuenta/10;
        alumno.setDigito1(numCuenta%10);

        //Toast.makeText(this, "D1: " + alumno.getDigito1() + " D2: " + alumno.getDigito2() + " D3: " + alumno.getDigito3() + " D4: " + alumno.getDigito4() + " D5: " + alumno.getDigito5() + " D6: " + alumno.getDigito6(), Toast.LENGTH_LONG).show();
        Paso();

    }//fin del metodo separa los numeros

    //Metodo para pasar de pantalla
    public void Paso(){
        //vinculacion de esta activity y la siguiente
        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
        //necesario para poder pasar un dato
        Bundle bundle = new Bundle();
        bundle.putSerializable("Alumno", alumno);
        //para empezar la activity siguiente
        intent.putExtras(bundle);
        startActivity(intent);
    }//fin del metodo pasar de pantalla


    //metodo para ingresar usuarios
    public void Registrar(){//View view

        Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(intent);

    }//Fin del metodo registrar

    //Metodo para mostrar alerta
    public void ShowPopup(String msj, String tit, String tipo, String accion){
        Intent intent = new Intent(MainActivity.this,AlertActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Mensaje", msj);
        bundle.putSerializable("Titulo",tit);
        bundle.putSerializable("Tipo",tipo);
        bundle.putSerializable("Accion",accion);
        //para empezar la activity siguiente
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
