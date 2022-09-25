package com.vantuit.hummy.modelo;

public class Producto {

    int imagen;
    String nombre;
    String vendedor;
    String precio;
    String calificacion;

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

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Producto(int imagen, String nombre, String vendedor, String precio, String calificacion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.vendedor = vendedor;
        this.precio = precio;
        this.calificacion = calificacion;
    }
}
