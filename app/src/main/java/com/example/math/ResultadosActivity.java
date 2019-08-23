package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultadosActivity extends AppCompatActivity {

    ProgressBar progreso;
    TextView porcentaje, ic_error,error;
    RelativeLayout ll;
    String indica,tema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        porcentaje = (TextView) findViewById(R.id.lbd_porcentaje);
        ic_error = (TextView) findViewById(R.id.fail);
        error = (TextView) findViewById(R.id.erores);
        ll = (RelativeLayout) findViewById(R.id.relative_resultados);

        cargarPreferencias();
        recivirDatos();


    }

    private void recivirDatos(){
        Bundle bundle = getIntent().getExtras();
        double resultado = bundle.getDouble("Valor");
        progreso = (ProgressBar) findViewById(R.id.progreso);
        int v = (int) (resultado * 10);
        progreso.setProgress(v);

        String p = String.valueOf(v);
        porcentaje.setText(p + "%");

        if (v < 100){
            errores();
        }

        indica = getIntent().getStringExtra("tema");
        tema = indica;

        if (indica.equals("on")){
            ll.setBackgroundColor(Color.rgb(25,25,25));
            porcentaje.setTextColor(Color.rgb(255,255,255));
            error.setTextColor(Color.rgb(255,255,255));
            guardarPreferencias();
        }if (indica.equals("off")){
            ll.setBackgroundColor(Color.rgb(255,255,255));
            porcentaje.setTextColor(Color.rgb(32,32,32));
            error.setTextColor(Color.rgb(32,32,32));
            guardarPreferencias();
        }


    }


    private void errores(){
        ic_error.setVisibility(View.VISIBLE);
        error.setVisibility(View.VISIBLE);
        error.setText("Error en las preguntas 5,6,7");
    }

    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        indica = misPreferencias.getString("indicadorResultados","");
    }

    public void guardarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("indicadorResultados",tema);
        editor.commit();
    }

}
