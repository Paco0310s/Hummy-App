package com.vantuit.hummy.modelo;

public class Opinion {

    String nombre;
    String opinion;

    int imagen;

    public Opinion(String nombre, String opinion, int imagen) {
        this.nombre = nombre;
        this.opinion = opinion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOinion() {
        return opinion;
    }

    public void setOinion(String oinion) {
        this.opinion = oinion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

}
