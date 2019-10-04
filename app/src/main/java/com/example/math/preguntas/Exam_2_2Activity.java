package com.example.math.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.math.R;

public class Exam_2_2Activity extends AppCompatActivity {

    String indica,tema;
    RelativeLayout ll;
    TextView question;
    Button btn_continua2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_2_2);

        ll = (RelativeLayout) findViewById(R.id.relative_exam2_2);
        question = (TextView) findViewById(R.id.question_2);
        btn_continua2 = (Button) findViewById(R.id.btn_continua2_2);


        indica = getIntent().getStringExtra("tema");
        tema = indica;
        cargarPreferencias();
        //indica = "on";
        //tema = "on";
        if(indica.equals("on")){
            ll.setBackgroundColor(Color.rgb(25,25,25));
            question.setTextColor(Color.rgb(255,255,255));
            guardarPreferencias();
        }if(indica.equals("off")){
            ll.setBackgroundColor(Color.rgb(255,255,255));
            question.setTextColor(Color.rgb(32,32,32));
            guardarPreferencias();
        }

        System.out.println(tema);

        //confirmar que se esta en la segunda pregunta
        question.setText("Segunda pregunta");

        btn_continua2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirSiguientePregunta();
            }
        });

    }

    //metodo para abrir la siguiente pregunta
    public void abrirSiguientePregunta(){
        Intent intent = new Intent(Exam_2_2Activity.this, Exam_2_3Activity.class);
        intent.putExtra("tema", indica);
        startActivity(intent);
    }

    public void guardarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        indica = misPreferencias.getString("examenDos","");
    }

    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("examenDos",tema);
        editor.commit();
    }
}
