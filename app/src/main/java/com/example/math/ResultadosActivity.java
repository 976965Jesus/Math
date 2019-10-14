package com.example.math;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultadosActivity extends AppCompatActivity {

    //Atributos de Abraham
    Alumno alumno = null;
    int Materia = 0;
    String MateriaText = null;
    ArrayList<String> listaInformacion = new ArrayList<String>();
    ArrayList<ResultadoRespuesta> listaResultados = new ArrayList<ResultadoRespuesta>();
    double calAlCien = 0;

    ListView lvPreguntas;

    //Atributos de Jesus
    ProgressBar progreso;
    TextView porcentaje, ic_error,error,ic_done;
    RelativeLayout ll;
    String indica,tema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Bundle bundleRecibido = getIntent().getExtras();
        alumno = (Alumno) bundleRecibido.getSerializable("Alumno");
        Materia = (int) bundleRecibido.getInt("Materia");

        lvPreguntas = (ListView) findViewById(R.id.lvPregunta);

        porcentaje = (TextView) findViewById(R.id.lbd_porcentaje);
        ic_error = (TextView) findViewById(R.id.fail);
        ic_done = (TextView) findViewById(R.id.done);
        error = (TextView) findViewById(R.id.erores);
        ll = (RelativeLayout) findViewById(R.id.relative_resultados);

        cargarPreferencias();
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

        consultarDatos();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        lvPreguntas.setAdapter(adaptador);

        recibirDatos();

        lvPreguntas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                error.setText(""+listaResultados.get(position).getNumPregunta());

                int estatus = listaResultados.get(position).getEstatus();
                if(estatus == 1){
                    error.setText("Pregunta Numero "+listaResultados.get(position).getNumPregunta()+ " Correcta");
                    ic_error.setVisibility(View.INVISIBLE);
                    ic_done.setVisibility(View.VISIBLE);
                }else{
                    error.setText("Pregunta Numero "+listaResultados.get(position).getNumPregunta()+ " Incorrecta");
                    ic_done.setVisibility(View.INVISIBLE);
                    ic_error.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void recibirDatos(){
        progreso = (ProgressBar) findViewById(R.id.progreso);
        int v = (int) (calAlCien);
        progreso.setProgress(v);

        String p = String.valueOf(v);
        porcentaje.setText(p);

        error.setVisibility(View.VISIBLE);

       }


    public void consultarDatos(){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BaseApp", null, 1);
        SQLiteDatabase dataBase =  admin.getWritableDatabase();

        Cursor fila = dataBase.rawQuery("select * from Respuesta where Materia = " + Materia +" and IdAlumno = " + alumno.getNumCuenta(), null);


        while(fila.moveToNext()){
            ResultadoRespuesta resultadoRespuesta = new ResultadoRespuesta();

            resultadoRespuesta.setIdRespuesta(fila.getInt(0));
            resultadoRespuesta.setMateria(fila.getInt(1));
            resultadoRespuesta.setIdAlumno(fila.getInt(2));
            resultadoRespuesta.setNumPregunta(fila.getInt(3));
            resultadoRespuesta.setEstatus(fila.getInt(4));
            resultadoRespuesta.setValor(fila.getInt(5));

            listaResultados.add(resultadoRespuesta);
        }
        obtenerLista();
    }

    public void obtenerLista(){

        for(int i = 0; i<listaResultados.size(); i++){
            listaInformacion.add("Pregunta Numero: " + listaResultados.get(i).getNumPregunta());

            calAlCien = calAlCien + listaResultados.get(i).getValor();
        }
    }



    //Metodos para el tema (Blanco o Negro9
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
