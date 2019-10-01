package com.example.math;

import java.io.Serializable;

public class Alumno implements Serializable {

    private int numCuenta = 0;
    private String nombre = null;
    private String ApellidoP = null, ApellidoM = null;
    private int Digito1 = 0, Digito2 = 0, Digito3 = 0, Digito4 = 0, Digito5 = 0, Digito6 = 0;

    public  Alumno(){

    }

    public Alumno(int numCuenta, String nombre, String apellidoP, String apellidoM){
        this.numCuenta = numCuenta;
        this.nombre = nombre;
        this.ApellidoP = apellidoP;
        this.ApellidoM = apellidoM;
    }

    public int getNumCuenta(){
        return this.numCuenta;
    }
    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return this.ApellidoP;
    }
    public void setApellidoP(String apellidoP) {
        this.ApellidoP = apellidoP;
    }

    public String getApellidoM() {
        return this.ApellidoM;
    }
    public void setApellidoM(String apellidoM) {
        this.ApellidoM = apellidoM;
    }

    public int getDigito1() {
        return this.Digito1;
    }
    public void setDigito1(int digito){
        this.Digito1 = digito;
    }

    public int getDigito2() {
        return this.Digito2;
    }
    public void setDigito2(int digito){
        this.Digito2 = digito;
    }

    public int getDigito3() {
        return this.Digito3;
    }
    public void setDigito3(int digito){
        this.Digito3 = digito;
    }

    public int getDigito4() {
        return this.Digito4;
    }
    public void setDigito4(int digito){
        this.Digito4 = digito;
    }

    public int getDigito5() {
        return this.Digito5;
    }
    public void setDigito5(int digito){
        this.Digito5 = digito;
    }

    public int getDigito6() {
        return this.Digito6;
    }
    public void setDigito6(int digito){
        this.Digito6 = digito;
    }


}
