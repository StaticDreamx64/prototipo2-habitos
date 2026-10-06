package com.example.s7_programapruebas8;

import java.io.Serializable;

public class Habit implements Serializable {
    private String nombre;
    private String descripcion;
    private int diasRacha;
    private String telefono;

    public Habit(String nombre, String descripcion, int diasRacha, String telefono) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.diasRacha = diasRacha;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getDiasRacha() { return diasRacha; }
    public void setDiasRacha(int diasRacha) { this.diasRacha = diasRacha; }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}