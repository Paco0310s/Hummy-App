package com.vantuit.hummy.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Producto implements Serializable {
    String id;
    String imagen;
    String nombre;
    String vendedor;
    String precio;
    String calificacion;
    String descripcion;
    String disponible;
    String categoria;
    ArrayList<String> ingredientes = new ArrayList<>();

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Producto(String imagen, String nombre, String vendedor, String precio, String calificacion) {

        this.imagen = imagen;
        this.nombre = nombre;
        this.vendedor = vendedor;
        this.precio = precio;
        this.calificacion = calificacion;
    }

    public Producto(String imagen, String nombre, String vendedor, String precio, String calificacion, String descripcion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.vendedor = vendedor;
        this.precio = precio;
        this.calificacion = calificacion;
        this.descripcion = descripcion;
    }

    public Producto() {
    }
}
