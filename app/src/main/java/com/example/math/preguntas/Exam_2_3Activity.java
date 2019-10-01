package com.example.math.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.math.R;

public class Exam_2_3Activity extends AppCompatActivity {

    String indica,tema;
    RelativeLayout ll;
    TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_2_3);

        ll = (RelativeLayout) findViewById(R.id.relative_exam2_3);
        question = (TextView) findViewById(R.id.question_3);

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
    }

    public void guardarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        indica = misPreferencias.getString("indicadorExam2_3","");
    }

    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("indicadorExam2_3",tema);
        editor.commit();
    }
}
