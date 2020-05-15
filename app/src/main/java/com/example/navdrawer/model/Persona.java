package com.example.navdrawer.model;

public class Persona {
    private int id;
    private String nombre;
    private String apellido;

    public Persona(int id, String col1, String col2) {
        this.id = id;
        this.nombre = col1;
        this.apellido = col2;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }
}
