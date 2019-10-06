package com.example.math;

public class ResultadoRespuesta {



    private int idRespuesta;
    private int materia;
    private int idAlumno;
    private int numPregunta;
    private int estatus;
    private int valor;

    public ResultadoRespuesta(){

    }

    public ResultadoRespuesta(int idRespuesta, int materia, int idAlumno, int numPregunta, int estatus, int valor){
        this.idRespuesta = idRespuesta;
        this.materia = materia;
        this.idAlumno = idAlumno;
        this.numPregunta = numPregunta;
        this.estatus = estatus;
        this.valor = valor;
    }


    public int getIdRespuesta() {
        return this.idRespuesta;
    }
    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public int getMateria() {
        return this.materia;
    }
    public void setMateria(int materia) {
        this.materia = materia;
    }

    public int getIdAlumno() {
        return this.idAlumno;
    }
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getNumPregunta() {
        return this.numPregunta;
    }
    public void setNumPregunta(int numPregunta) {
        this.numPregunta = numPregunta;
    }

    public int getEstatus() {
        return this.estatus;
    }
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getValor() {
        return this.valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }


}
