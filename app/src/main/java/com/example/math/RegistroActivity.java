package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    //Atributos para vas variables de los editText
    EditText etNombre, etApellidoP, etApallidoM, etNumCuenta;

    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = (EditText) findViewById(R.id.edt_nombre);
        etApellidoP = (EditText) findViewById(R.id.edt_apellido_p);
        etApallidoM = (EditText) findViewById(R.id.edt_apellido_m);
        etNumCuenta = (EditText) findViewById(R.id.edt_cuenta);

        //Flecha de retorno
        back = (TextView) findViewById(R.id.lbd_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //Metodo par avalidar los caompos no esten vacios
    public  void  Validar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BaseApp", null, 1);
        SQLiteDatabase dataBase =  admin.getWritableDatabase();

        String nombre = etNombre.getText().toString();
        String apellidoP = etApellidoP.getText().toString();
        String apellidoM = etApallidoM.getText().toString();
        String numCuenta = etNumCuenta.getText().toString();

        if(numCuenta.isEmpty()){
            Toast.makeText(this, "Debes llenar el Numero de Cuenta", Toast.LENGTH_SHORT).show();
        }else{

            Cursor fila = dataBase.rawQuery("select NumCuenta, Nombre, ApellidoPaterno, ApellidoMaterno from Alumno where NumCuenta = " + numCuenta, null);

            if(fila.moveToFirst()){
                Toast.makeText(this, "El Numero de Cuenta ya ha sido Registrado", Toast.LENGTH_SHORT).show();
            }else{

                if(nombre.isEmpty() | apellidoP.isEmpty() | apellidoM.isEmpty() | numCuenta.isEmpty()){
                    Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    Registrar(numCuenta, nombre, apellidoP, apellidoM);
                }

            }
        }





    }

    //metodo para ingresar usuarios
    public void Registrar(String numCuenta, String nombre, String apellidoP, String apellidoM){//View view

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BaseApp", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();


        ContentValues registro =  new ContentValues();

        registro.put("NumCuenta", numCuenta);
        registro.put("Nombre", nombre);
        registro.put("ApellidoPaterno", apellidoP);
        registro.put("ApellidoMaterno", apellidoM);

        dataBase.insert("Alumno", null, registro);
        dataBase.close();
        Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
    }//Fin del metodo registrar


}
