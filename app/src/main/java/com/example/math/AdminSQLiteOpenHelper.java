package com.example.math;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private String sqlTableAlumno = "create table Alumno(NumCuenta int primary key, Nombre text, ApellidoPaterno text, ApellidoMaterno text)";
    private String sqlTableRespuesta = "create table Respuesta(IdRespuesta INTEGER primary key autoincrement, Materia int, IdAlumno int, NumPregunta int, Estatus int, Valor double, ResUsuario String, ResSystem String)";


    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }


    public void onCreate(SQLiteDatabase db){
        db.execSQL(sqlTableAlumno);
        db.execSQL(sqlTableRespuesta);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
