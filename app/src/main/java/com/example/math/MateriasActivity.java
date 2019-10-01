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

public class MateriasActivity extends AppCompatActivity {

    //Atributos Externos d ela Clase
    Alumno alumno = null;

    //Atributos internos de la clase
    TextView titulo,algebra;
    RelativeLayout ll;
    String tema,indica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        Bundle bundleRecibido = getIntent().getExtras();
        alumno = (Alumno) bundleRecibido.getSerializable("Alumno");

        titulo = (TextView) findViewById(R.id.lbd_title_materias);
        algebra = (TextView) findViewById(R.id.opt_algebra);
        ll = (RelativeLayout) findViewById(R.id.relative_materias);

        cargarPreferencias();

        indica = getIntent().getStringExtra("tema");
        tema = indica;

        if (indica.equals("on")){
            ll.setBackgroundColor(Color.rgb(25, 25, 25));
            titulo.setTextColor(Color.rgb(255,255,255));
            guardarPreferencias();
        }if (indica.equals("off")){
            ll.setBackgroundColor(Color.rgb(255,255,255));
            titulo.setTextColor(Color.rgb(32,32,32));
            guardarPreferencias();
        }

        algebra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresa_algebra();
            }
        });
    }

    public void ingresa_algebra(){
        //Intent intent = new Intent(MateriasActivity.this,ExamOpt2Activity.class);
        //intent.putExtra("tema",indica);
        //startActivity(intent);

        Intent intent = new Intent(MateriasActivity.this, ExamOpt2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Alumno", alumno);
        intent.putExtras(bundle);
        intent.putExtra("Materia", 1);
        intent.putExtra("tema",indica);
        startActivity(intent);

    }

    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        indica = misPreferencias.getString("indicadorMaterias","");
    }

    public void guardarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("indicadorMaterias",tema);

        editor.commit();
    }


}