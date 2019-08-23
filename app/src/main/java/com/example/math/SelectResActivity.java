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
    TextView algebra, geometria, trigonometria, titulo;
    double valor;
    String indica,tema;
    RelativeLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_res);

        algebra = (TextView) findViewById(R.id.opt_algebra);
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
                valor = 8.5;
                muestra_resultado(valor);
            }
        });

        geometria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 7.0;
                muestra_resultado(valor);
            }
        });

        trigonometria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valor = 9.0;
                muestra_resultado(valor);
            }
        });
    }

    public void muestra_resultado(double valor_resultado){
        Intent intent = new Intent(SelectResActivity.this,ResultadosActivity.class);
        intent.putExtra("Valor",valor_resultado);
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
}

