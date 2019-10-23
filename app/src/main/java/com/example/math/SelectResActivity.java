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

public class SelectResActivity extends AppCompatActivity {

    Alumno alumno = null;


    TextView algebra,fisica, geometria, trigonometria, titulo;
    double valor;
    String indica,tema;
    RelativeLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_res);

        Bundle bundleRecibido = getIntent().getExtras();
        alumno = (Alumno) bundleRecibido.getSerializable("Alumno");

        algebra = (TextView) findViewById(R.id.opt_algebra);
        fisica = (TextView) findViewById(R.id.opt_fisica);
        geometria = (TextView) findViewById(R.id.opt_geometria);
        trigonometria = (TextView) findViewById(R.id.opt_trigonometria);
        ll = (RelativeLayout) findViewById(R.id.relative_select_res);
        titulo = (TextView) findViewById(R.id.lbd_muestra_res);

        cargarPreferencias();
        indica = getIntent().getStringExtra("tema");
        tema = indica;
        if (indica.equals("on")){
            ll.setBackgroundColor(Color.rgb(25,25,25));
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
                //muestra_resultadoAlgebra();
                ShowPopup("Actualmente la materia no esta disponible","Materia no cargada","alerta","continua");
            }
        });

        fisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                muestra_resultadoFisica();
            }
        });

        geometria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup("Actualmente la materia no esta disponible","Materia no cargada","alerta","continua");
            }
        });

        trigonometria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowPopup("Actualmente la materia no esta disponible","Materia no cargada","alerta","continua");
            }
        });
    }

    public void muestra_resultadoFisica(){
        Intent intent = new Intent(SelectResActivity.this, ResultadosActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Alumno", alumno);
        intent.putExtras(bundle);
        intent.putExtra("Materia", 1);
        intent.putExtra("tema",indica);
        startActivity(intent);
    }

    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        indica = misPreferencias.getString("indicadorSelecRes","");
    }

    public void guardarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("indicadorSelectRes",tema);
        editor.commit();
    }

    //Metodo para mostrar alerta
    public void ShowPopup(String msj, String tit, String tipo, String accion){
        Intent intent = new Intent(SelectResActivity.this,AlertActivity.class);
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

