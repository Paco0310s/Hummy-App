package com.vantuit.hummy.modelo;

public class Carrito {

    int imagen;
    String nombre;
    String vendedor;
    String precio;
    int cantidad;

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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Carrito(int imagen, String nombre, String vendedor, String precio, int cantidad) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.vendedor = vendedor;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public Carrito() {

    }

}
