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
            ShowPopup("Ingresa un numero de cuenta valido","Numero de  cuenta vacio","error","continua");
        }else{

            Cursor fila = dataBase.rawQuery("select NumCuenta, Nombre, ApellidoPaterno, ApellidoMaterno from Alumno where NumCuenta = " + numCuenta, null);

            if(fila.moveToFirst()){
                ShowPopup("El numero de cuenta ya ha sido registrado","Numero de cuenta no valido","error","continua");
            }else{

                if(nombre.isEmpty() | apellidoP.isEmpty() | apellidoM.isEmpty() | numCuenta.isEmpty()){
                    ShowPopup("Llena todos los campos por favor","Campos vacios","error","continua");
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
        ShowPopup("Felicidades! ya estas registrado","Registro exitoso","exito","salir");
    }//Fin del metodo registrar


    public void ShowPopup(String msj, String tit, String tipo, String accion){
        Intent intent = new Intent(RegistroActivity.this,AlertActivity.class);
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
