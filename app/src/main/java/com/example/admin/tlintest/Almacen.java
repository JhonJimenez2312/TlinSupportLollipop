package com.example.admin.tlintest;

/**
 * Created by Admin on 09/12/2017.
 */

public class Almacen {
    private String id;
    private String nombre;
    private String foto;

    public Almacen() {
    }

    public Almacen(String id, String nombre, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
    }
    public Almacen( String nombre, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
    }
    public Almacen(String nombre) {

        this.nombre = nombre;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}