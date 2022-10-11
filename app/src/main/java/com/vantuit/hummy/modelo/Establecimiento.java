package com.vantuit.hummy.modelo;

public class Establecimiento {

    int imagen;
    String nombre;
    String descripcion;
    String estado;
    String calificacion;
    String foto;
    String id;

    public Establecimiento(int imagen, String nombre, String descripcion, String estado, String calificacion, String foto, String id) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.calificacion = calificacion;
        this.foto = foto;
        this.id = id;
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

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
}
