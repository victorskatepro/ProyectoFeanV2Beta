package com.example.jarvis.proyectofean.Objetos;

/**
 * Created by JARVIS on 14/07/2017.
 */

public class Asistencias {
    private String id;
    private String curso;
    private String nombrealumno;
    private String hora;
    private String fecha;


    public Asistencias(){

    }

    public Asistencias(String id, String curso, String nombrealumno, String hora, String fecha) {
        this.id = id;
        this.curso = curso;
        this.nombrealumno = nombrealumno;
        this.hora = hora;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNombrealumno() {
        return nombrealumno;
    }

    public void setNombrealumno(String nombrealumno) {
        this.nombrealumno = nombrealumno;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
