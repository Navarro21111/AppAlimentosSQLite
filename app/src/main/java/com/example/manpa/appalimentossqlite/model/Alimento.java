package com.example.manpa.appalimentossqlite.model;

import java.io.Serializable;

public class Alimento implements Serializable {
    private int id;
    private String nombre;
    private String tipo;
    private String origen;
    private String nutrientes;
    private String funcion;

    public Alimento(int id, String nombre, String tipo, String origen, String nutrientes, String funcion) {
        this.id = -1;
        this.nombre = nombre;
        this.tipo = tipo;
        this.origen = origen;
        this.nutrientes = nutrientes;
        this.funcion = funcion;
    }


    public Alimento(String nombre, String tipo, String origen, String nutrientes, String funcion) {
        this.id = -1;
        this.nombre = nombre;
        this.tipo = tipo;
        this.origen = origen;
        this.nutrientes = nutrientes;
        this.funcion = funcion;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getNutrientes() {
        return nutrientes;
    }

    public void setNutrientes(String nutrientes) {
        this.nutrientes = nutrientes;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }
}
