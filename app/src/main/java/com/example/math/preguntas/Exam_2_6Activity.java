package com.example.math.preguntas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math.AdminSQLiteOpenHelper;
import com.example.math.Alumno;
import com.example.math.MenuActivity;
import com.example.math.R;

import java.text.DecimalFormat;

public class Exam_2_6Activity extends AppCompatActivity {


    //Atributos Externos del frame (Abraham)
    Alumno alumno = null;
    int Materia = 0;
    int numPregunta = 6;


    Button btn_continuar;
    EditText textArea;
    //Atributos internos del frame (Jesus)
    String indica,tema;
    RelativeLayout ll;
    TextView question;
    Button btn_continua6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_2_6);

        Bundle bundleRecibido = getIntent().getExtras();
        alumno = (Alumno) bundleRecibido.getSerializable("Alumno");
        Materia = (int) bundleRecibido.getInt("Materia");

        btn_continuar = (Button) findViewById(R.id.btn_continua2_6);
        textArea = (EditText) findViewById(R.id.input_res6);

        ll = (RelativeLayout) findViewById(R.id.relative_exam2_6);
        question = (TextView) findViewById(R.id.question_6);
        btn_continua6 = (Button) findViewById(R.id.btn_continua2_6);

        cargarPreferencias();
        indica = getIntent().getStringExtra("tema");
        tema = indica;
        if(indica.equals("on")){
            ll.setBackgroundColor(Color.rgb(25,25,25));
            question.setTextColor(Color.rgb(255,255,255));
            guardarPreferencias();
        }if(indica.equals("off")){
            ll.setBackgroundColor(Color.rgb(255,255,255));
            question.setTextColor(Color.rgb(32,32,32));
            guardarPreferencias();
        }

        Pregunta();

    }

    //Comportamiento regresar

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(this);
        alertOpciones.setMessage("En verdad desea salir del examen");
        alertOpciones.setTitle("Salir");
        alertOpciones.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Exam_2_6Activity.this, MenuActivity.class);
                //pasamos los datos necesarios para poder ejecutar el activity
                Bundle bundle = new Bundle();
                bundle.putSerializable("Alumno", alumno);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        alertOpciones.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = alertOpciones.create();
        dialog.show();
    }


    //Metodos externos del frame

    //Metodo Mostrar Pregunta
    public void Pregunta(){
        if(Materia == 1){

            question.setText("RETOMANDO LA PREGUNTA ANTERIOR - Un día en que la rapidez del sonido es de 340 m/s, un tren emite un sonido de " + alumno.getDigito1() + " Hz de frecuencia, ¿Cuál es la frecuencia del sonido escuchado por un observador inmóvil cuando el tren se mueve hacia él con una velocidad de 20 m/s? - AHORA CONTESTA: ¿Cuál es la frecuencia que se escucha cuando el tren se mueve alejándose del oyente a esa velocidad?");


        }else if(Materia == 2){

            question.setText("cuantos lados tiene un triangulo?");
        }else if(Materia == 3){

            question.setText("si juan tiene 2 manzanas y se come 3, cuantas le quedan?");

        }
    }



    //metodo para activar el boton
    public void respuestaContinuar(View view){

        if(Materia == 1){

            double resDouble = RespuestaMeteria6();
            //damos formato de solo 12 decimales
            DecimalFormat formato = new DecimalFormat("#.##");
            String res = formato.format(resDouble);

            String resUser = textArea.getText().toString();
            Toast.makeText(this, "Respuesta dada por el sistema: " + res, Toast.LENGTH_SHORT).show();

            if(res.equals(resUser)){
                validarTest(1, 10, resUser, res);
                Toast.makeText(this, "Respuesta Correcta: " + res, Toast.LENGTH_SHORT).show();
            }else{
                validarTest(0, 0, resUser, res);
            }

        }else if(Materia == 2){

        }else if(Materia == 3){

        }
    }


    //Metodo Respuesta 1
    public double RespuestaMeteria6(){

        //Texto de Pregunta
        //TVPregunta.setText("RETOMANDO LA PREGUNTA ANTERIOR - Un día en que la rapidez del sonido es de 340 m/s, un tren emite un sonido de " + alumno.getDigito1() + " Hz de frecuencia, ¿Cuál es la frecuencia del sonido escuchado por un observador inmóvil cuando el tren se mueve hacia él con una velocidad de 20 m/s? - AHORA CONTESTA: ¿Cuál es la frecuencia que se escucha cuando el tren se mueve alejándose del oyente a esa velocidad?");

        //parte Simplificada y optimizada
        double fs = alumno.getDigito1();
        double respuesta = fs * (340+0)/(340-(-20));

        //parte Primera toda culera
        //double potencia = (double) Math.pow(10,10);
        //double young = alumno.getDigito1() * potencia;

        //double densidadSuma = alumno.getDigito6()+10;
        //double densidadfinal = densidadSuma * (Math.pow(100,3)/1000);

        //double respuestaPenultima = young/densidadfinal;
        //double respuestaUltima = Math.sqrt(respuestaPenultima);

        return respuesta;
    }


    //Metodos para validar, guarda y continuar a la siguiente pregunta
    //Metodo para validar si es la primer aves que hace el test
    public void validarTest(int estatus, double valor, String resUser, String resSystem){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BaseApp", null, 1);
        SQLiteDatabase dataBase =  admin.getWritableDatabase();

        Cursor fila = dataBase.rawQuery("select * from Respuesta where Materia = " + Materia +" and IdAlumno = " + alumno.getNumCuenta() + "  and NumPregunta = " + numPregunta, null);

        if(fila.moveToFirst()){
            Toast.makeText(this, "La Pregunta ya fue contestada", Toast.LENGTH_SHORT).show();
            abrirSiguientePregunta();
        }else{
            guardar(estatus, valor, resUser, resSystem);
        }

    }

    //Metodo para guardar en la base
    public void guardar(int estatus, double valor, String resUser, String resSystem){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BaseApp", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();

        ContentValues registro =  new ContentValues();

        registro.put("Materia", Materia);
        registro.put("IdAlumno", alumno.getNumCuenta());
        registro.put("NumPregunta", numPregunta);
        registro.put("Estatus", estatus);
        registro.put("Valor", valor);
        registro.put("ResUsuario", resUser);
        registro.put("ResSystem", resSystem);

        database.insert("Respuesta", null, registro);
        database.close();

        abrirSiguientePregunta();
    }

    //metodo para abrir la siguiente pregunta
    public void abrirSiguientePregunta(){
        Intent intent = new Intent(Exam_2_6Activity.this, Exam_2_7Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Alumno", alumno);
        intent.putExtras(bundle);
        intent.putExtra("Materia", Materia);
        intent.putExtra("tema", tema);
        startActivity(intent);
    }

    public void guardarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        indica = misPreferencias.getString("indicadorExam2_6","");
    }

    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("indicadorExam2_6",tema);
        editor.commit();
    }
}
