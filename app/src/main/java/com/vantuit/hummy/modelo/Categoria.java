package com.vantuit.hummy.modelo;

public class Categoria {

    private String categoria;
    private int imagen;

    public Categoria(String categoria, int imagen) {
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
