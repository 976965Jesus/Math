package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    ImageView mat, resultados, confi;
    RelativeLayout ll;
    int request_code = 1;
    String tema, indica;
    TextView conf,res,mate,alumno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_materias);

        mat = (ImageView) findViewById(R.id.img_meteria);
        resultados = (ImageView) findViewById(R.id.img_resultados);
        confi = (ImageView) findViewById(R.id.img_settings);
        ll = (RelativeLayout)findViewById(R.id.relativeMenu);

        conf = (TextView) findViewById(R.id.lbd_configuracion);
        res = (TextView) findViewById(R.id.lbd_resultados);
        mate = (TextView) findViewById(R.id.lbd_materias);
        alumno = (TextView) findViewById(R.id.alumno);

        cargarPreferencias();

        if(indica.equals("on")){
            ll.setBackgroundColor(Color.rgb(25, 25, 25));
            conf.setTextColor(Color.rgb(255,255,255));
            res.setTextColor(Color.rgb(255,255,255));
            mate.setTextColor(Color.rgb(255,255,255));
            alumno.setTextColor(Color.rgb(255,255,255));

        }if(indica.equals("off")){
            ll.setBackgroundColor(Color.rgb(255,255,255));
            conf.setTextColor(Color.rgb(32,32,32));
            res.setTextColor(Color.rgb(32,32,32));
            mate.setTextColor(Color.rgb(32,32,32));
            alumno.setTextColor(Color.rgb(32,32,32));

        }


        mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ver_materias();

                //if(indica.equals("on")){
                  //  Intent paso = new Intent();
                    //paso.setData(Uri.parse(indica));
                    //setResult(RESULT_OK,paso);

                //}
            }
        });
        resultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ver_resultados();
            }
        });
        confi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Configuracion();
            }
        });
    }

    public void Ver_materias(){
        Intent intent = new Intent(MenuActivity.this,MateriasActivity.class);
        intent.putExtra("tema",indica);
        startActivity(intent);

    }

    public void Ver_resultados(){
        Intent intent = new Intent(MenuActivity.this,SelectResActivity.class);
        intent.putExtra("tema",indica);
        startActivity(intent);
    }

    public void Configuracion(){
        Intent intent = new Intent(MenuActivity.this,SettingsActivity.class);
        startActivityForResult(intent,request_code);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);
        if ((requestCode == request_code) && (resultCode == RESULT_OK)){
            //String t = data.getStringExtra("tema");
            tema = data.getDataString();

            if (tema.equals("on")){
                ll.setBackgroundColor(Color.rgb(25, 25, 25));
                conf.setTextColor(Color.rgb(255,255,255));
                res.setTextColor(Color.rgb(255,255,255));
                mate.setTextColor(Color.rgb(255,255,255));
                alumno.setTextColor(Color.rgb(255,255,255));
                guardarPreferencias();

            }if (tema.equals("off")){
                ll.setBackgroundColor(Color.rgb(255,255,255));
                conf.setTextColor(Color.rgb(32,32,32));
                res.setTextColor(Color.rgb(32,32,32));
                mate.setTextColor(Color.rgb(32,32,32));
                alumno.setTextColor(Color.rgb(32,32,32));
                guardarPreferencias();


            }

        }
        cargarPreferencias();
    }


    public void cargarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        //sw.setChecked(misPreferencias.getBoolean("switch",false));
        indica = misPreferencias.getString("indicadorMenu","");
    }

    public void guardarPreferencias(){
        SharedPreferences misPreferencias = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("indicadorMenu",tema);

        editor.commit();
    }

}
