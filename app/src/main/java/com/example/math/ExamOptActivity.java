package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.math.preguntas.Exam_2_1Activity;

public class ExamOptActivity extends AppCompatActivity {
    Button continua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_opt);

        continua = (Button) findViewById(R.id.btn_continua);
        continua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continuar();
            }
        });
    }

    private void continuar(){
        Intent intent = new Intent(ExamOptActivity.this, Exam_2_1Activity.class);
        startActivity(intent);
    }
}
