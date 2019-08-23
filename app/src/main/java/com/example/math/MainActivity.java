package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text;
    Button btn;
    TextView registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();


        btn = (Button) findViewById(R.id.btn_login);
        text = (EditText) findViewById(R.id.edt_usuario);
        registro = (TextView) findViewById(R.id.lbd_registro);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
            }
        });
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });

    }

    public void ingresar(){
        if (text.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "Ingrese el usuario", Toast.LENGTH_LONG).show();
        }

        if (text.getText().toString().equals("332558")){
            Toast.makeText(MainActivity.this, "Acceso permitido", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(intent);

        }else {
            Toast.makeText(MainActivity.this, "Acceso denegado :(", Toast.LENGTH_SHORT).show();
        }
    }

    public void registro(){
        Intent intent = new Intent(MainActivity.this,RegistroActivity.class);
        startActivity(intent);
    }
}
