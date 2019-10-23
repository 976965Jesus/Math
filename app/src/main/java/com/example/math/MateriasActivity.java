package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.math.preguntas.*;

import com.example.math.preguntas.Exam_2_1Activity;

public class MateriasActivity extends AppCompatActivity {

    //Atributos Externos d ela Clase
    Alumno alumno = null;

    //Atributos internos de la clase


    TextView titulo,algebra,fisica,geometria,trigonometria;
    RelativeLayout ll;
    String tema, indica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        Bundle bundleRecibido = getIntent().getExtras();
        alumno = (Alumno) bundleRecibido.getSerializable("Alumno");

        titulo = (TextView) findViewById(R.id.lbd_title_materias);
        algebra = (TextView) findViewById(R.id.opt_algebra);
        fisica = (TextView) findViewById(R.id.opt_fisica);
        geometria = (TextView) findViewById(R.id.opt_geometria);
        trigonometria = (TextView) findViewById(R.id.opt_trigonometria);
        ll = (RelativeLayout) findViewById(R.id.relative_materias);

        cargarPreferencias();

        indica = getIntent().getStringExtra("tema");
        tema = indica;

        if (indica.equals("on")) {
            ll.setBackgroundColor(Color.rgb(25, 25, 25));
            titulo.setTextColor(Color.rgb(255, 255, 255));
            guardarPreferencias();
        }
        if (indica.equals("off")) {
            ll.setBackgroundColor(Color.rgb(255, 255, 255));
            titulo.setTextColor(Color.rgb(32, 32, 32));
            guardarPreferencias();
        }

        algebra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ingresa_algebra();
                ShowPopup("Actualmente la materia no esta disponible","Materia no cargada","alerta","continua");
            }
        });
        fisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresa_fisica();
            }
        });
        geometria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ingresa();
                ShowPopup("Actualmente la materia no esta disponible","Materia no cargada","alerta","continua");
            }
        });
        trigonometria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ingresa();
                ShowPopup("Actualmente la materia no esta disponible","Materia no cargada","alerta","continua");
            }
        });
    }

    public void ingresa_algebra() {

    }

    public void ingresa_fisica(){
        //Se cambio el nombre de la pantalla de examen antes era Examen_Opt2 ahora es Exam_2_1Activity (_1 es por ser la primera pantalla de las preguntas)
        Intent intent = new Intent(MateriasActivity.this, Exam_2_1Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Alumno", alumno);
        intent.putExtras(bundle);
        intent.putExtra("Materia", 1);
        intent.putExtra("tema", indica);
        startActivity(intent);
    }




        public void cargarPreferencias () {
            SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
            indica = misPreferencias.getString("indicadorMaterias", "");
        }

        public void guardarPreferencias () {
            SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = misPreferencias.edit();

            editor.putString("indicadorMaterias", tema);

            editor.commit();
        }

    //Metodo para mostrar alerta
    public void ShowPopup(String msj, String tit, String tipo, String accion){
        Intent intent = new Intent(MateriasActivity.this,AlertActivity.class);
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

