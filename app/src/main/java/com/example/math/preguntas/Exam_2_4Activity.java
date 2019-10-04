package com.example.math.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
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
import com.example.math.R;

import java.text.DecimalFormat;

public class Exam_2_4Activity extends AppCompatActivity {


    //Atributos Externos del frame (Abraham)
    Alumno alumno = null;
    int Materia = 0;
    int numPregunta = 4;


    Button btn_continuar;
    EditText textArea;
    //Atributos internos del frame (Jesus)
    String indica,tema;
    RelativeLayout ll;
    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_2_4);

        Bundle bundleRecibido = getIntent().getExtras();
        alumno = (Alumno) bundleRecibido.getSerializable("Alumno");
        Materia = (int) bundleRecibido.getInt("Materia");

        btn_continuar = (Button) findViewById(R.id.btn_continua2_4);
        textArea = (EditText) findViewById(R.id.input_res4);

        ll = (RelativeLayout) findViewById(R.id.relative_exam2_4);
        question = (TextView) findViewById(R.id.question_4);

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


    //Metodos externos del frame

    //Metodo Mostrar Pregunta
    public void Pregunta(){
        if(Materia == 1){

            question.setText("Calcule la velocidad del sonido en el aire, cuando este se encuentra a una temperatura de (" + alumno.getDigito1() + ") [°C]");


        }else if(Materia == 2){

            question.setText("cuantos lados tiene un triangulo?");
        }else if(Materia == 3){

            question.setText("si juan tiene 2 manzanas y se come 3, cuantas le quedan?");

        }
    }



    //metodo para activar el boton
    public void respuestaContinuar(View view){

        if(Materia == 1){

            double resDouble = RespuestaMeteria4();
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
    public double RespuestaMeteria4(){

        //Texto de Pregunta
        //TVPregunta.setText("Calcule la velocidad del sonido en el aire, cuando este se encuentra a una temperatura de (" + alumno.getDigito1() + ") [°C]");

        //parte Simplificada y optimizada
        double temperatura = alumno.getDigito1() + 273;
        double respuesta = 331 * Math.sqrt(temperatura/273);

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

        Intent intent = new Intent(Exam_2_4Activity.this, Exam_2_5Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Alumno", alumno);
        intent.putExtras(bundle);
        intent.putExtra("Materia", Materia);
        intent.putExtra("tema",indica);
        startActivity(intent);

    }


    public void guardarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        indica = misPreferencias.getString("indicadorExam2_4","");
    }

    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("indicadorExam2_4",tema);
        editor.commit();
    }
}
