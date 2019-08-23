package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    Switch sw;
    RelativeLayout ll;
    TextView califica;
    String temas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ll = (RelativeLayout) findViewById(R.id.relative);
        sw = (Switch) findViewById(R.id.swBackground);
        califica = (TextView) findViewById(R.id.calificanos);


        cargarPreferencias();
        //color = Color.parseColor(ll.getBackground().toString());

        if (sw.isChecked()) {
            ll.setBackgroundColor(Color.rgb(25, 25, 25));
            sw.setTextColor(Color.rgb(255,255,255));
            califica.setTextColor(Color.rgb(255,255,255));

        }else {
            ll.setBackgroundColor(Color.rgb(255,255,255));
            sw.setTextColor(Color.rgb(32,32,32));
            califica.setTextColor(Color.rgb(32,32,32));
        }



    }


    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        sw.setChecked(misPreferencias.getBoolean("switch",false));
        //ll.setBackgroundColor(Color.rgb(255,255,255));
        //color = Color.parseColor("#fff");
    }

    public void guardarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();
        boolean valor = sw.isChecked();
        editor.putBoolean("switch",valor);

        //int col = Color.parseColor(ll.getBackground().toString());
        //editor.putInt("color",col);
        editor.commit();
    }


    public void cambio(View view) {
        if (sw.isChecked()) {
            ll.setBackgroundColor(Color.rgb(25, 25, 25));
            sw.setTextColor(Color.rgb(255,255,255));
            califica.setTextColor(Color.rgb(255,255,255));
            guardarPreferencias();

            temas = "on";

            Intent data = new Intent();
            //data.putExtra("Tema",temas);
            data.setData(Uri.parse(temas));
            setResult(RESULT_OK,data);

        }else {
            ll.setBackgroundColor(Color.rgb(255,255,255));
            sw.setTextColor(Color.rgb(32,32,32));
            califica.setTextColor(Color.rgb(32,32,32));
            guardarPreferencias();

            temas = "off";

            Intent data = new Intent();
            //data.putExtra("Tema",temas);
            data.setData(Uri.parse(temas));
            setResult(RESULT_OK,data);
        }
    }

}
