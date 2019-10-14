package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AlertActivity extends AppCompatActivity {

    ImageView close_alert;
    TextView message;
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        Bundle bundleRecibido = getIntent().getExtras();
        mensaje = (String) bundleRecibido.getSerializable("Mensaje");


        close_alert = (ImageView) findViewById(R.id.cerrar_alert);
        message = (TextView) findViewById(R.id.text_mensaje_alert);

        message.setText(mensaje);

        close_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
