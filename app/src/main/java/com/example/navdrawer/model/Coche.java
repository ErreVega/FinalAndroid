package com.example.navdrawer.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "coche")
public class Coche {

    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "puertas")
    private int puertas;



    @ColumnInfo(name = "descripcion")
    private String desc;

    public Coche(String nombre, String color, int puertas) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.color = color;
        this.puertas = puertas;
        this.desc =  "Descripcion hardcodeada\n "+
                "Descripcion hardcodeada\n "+
                "Descripcion hardcodeada\n "+
                "Descripcion hardcodeada\n ";
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", puertas=" + puertas +
                '}';
    }
}
