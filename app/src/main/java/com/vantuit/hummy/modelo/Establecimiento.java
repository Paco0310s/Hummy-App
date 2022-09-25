package com.vantuit.hummy.modelo;

public class Establecimiento {

    int imagen;
    String nombre;
    String descripcion;
    String estado;
    String calificacion;

    public Establecimiento(int imagen, String nombre, String descripcion, String estado, String calificacion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.calificacion = calificacion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }



}
