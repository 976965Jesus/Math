package com.example.math;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AlertActivity extends AppCompatActivity {

    ImageView close_alert;
    TextView message, title;
    String mensaje, titulo, tipo, accion;
    RelativeLayout ll;

    MediaPlayer mp;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        Bundle bundleRecibido = getIntent().getExtras();
        mensaje = (String) bundleRecibido.getSerializable("Mensaje");
        titulo = (String) bundleRecibido.getSerializable("Titulo");
        tipo = (String) bundleRecibido.getSerializable("Tipo");
        accion = (String) bundleRecibido.getSerializable("Accion");


        mp = MediaPlayer.create(this,R.raw.cuack);



        close_alert = (ImageView) findViewById(R.id.cerrar_alert);
        message = (TextView) findViewById(R.id.text_mensaje_alert);
        title = (TextView) findViewById(R.id.text_title_alert);
        ll = (RelativeLayout) findViewById(R.id.relative_alert);


        if (tipo.equals("exito")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ll.setBackground(getDrawable(R.drawable.button_green));
            }
            close_alert.setBackgroundColor(Color.rgb(69,188,98));
            message.setBackgroundColor(Color.rgb(69,188,98));
            title.setBackgroundColor(Color.rgb(69,188,98));
        }
        if (tipo.equals("alerta")){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                ll.setBackground(getDrawable(R.drawable.button_yellow));
                close_alert.setBackgroundColor(Color.rgb(242,226,5));
                message.setBackgroundColor(Color.rgb(242,226,5));
                title.setBackgroundColor(Color.rgb(242,226,5));
            }
        }

        if (tipo.equals("error")){
            mp.start();
        }


        message.setText(mensaje);
        title.setText(titulo);

        close_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accion.equals("salir")){
                    Intent intent = new Intent(AlertActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (accion.equals("continua")){
                    finish();
                }

            }
        });
    }

}
