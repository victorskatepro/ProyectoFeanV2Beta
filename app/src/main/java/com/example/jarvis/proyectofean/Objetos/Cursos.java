package com.example.jarvis.proyectofean.Objetos;

/**
 * Created by JARVIS on 4/07/2017.
 */

public class Cursos {
    private String aula;
    private String nombre;
    private String profesor;
    private String horario;
    private String fechas;
    private String longitud;
    private String latitud;
   // private String email;

    public  Cursos(){

    }

    public Cursos(String aula, String nombre, String profesor, String horario, String fechas, String longitud, String latitud) {
        this.aula = aula;
        this.nombre = nombre;
        this.profesor = profesor;
        this.horario = horario;
        this.fechas = fechas;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
}
