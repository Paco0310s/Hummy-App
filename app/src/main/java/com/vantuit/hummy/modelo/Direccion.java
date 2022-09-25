package com.vantuit.hummy.modelo;

public class Direccion {

    String nombre;
    int codigopostal;
    String estado;
    String municipio;
    String colonia;
    String calle;
    int numerointerior;
    int numeroexterior;
    String calle1;
    String calle2;
    String tipodevivienda;
    String numerodetelefono;
    String descripciondedireccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(int codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumerointerior() {
        return numerointerior;
    }

    public void setNumerointerior(int numerointerior) {
        this.numerointerior = numerointerior;
    }

    public int getNumeroexterior() {
        return numeroexterior;
    }

    public void setNumeroexterior(int numeroexterior) {
        this.numeroexterior = numeroexterior;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getTipodevivienda() {
        return tipodevivienda;
    }

    public void setTipodevivienda(String tipodevivienda) {
        this.tipodevivienda = tipodevivienda;
    }

    public String getNumerodetelefono() {
        return numerodetelefono;
    }

    public void setNumerodetelefono(String numerodetelefono) {
        this.numerodetelefono = numerodetelefono;
    }

    public String getDescripciondedireccion() {
        return descripciondedireccion;
    }

    public void setDescripciondedireccion(String descripciondedireccion) {
        this.descripciondedireccion = descripciondedireccion;
    }

    public Direccion(String nombre, int codigopostal, String estado, String municipio, String colonia, String calle, int numerointerior, int numeroexterior, String calle1, String calle2, String tipodevivienda, String numerodetelefono, String descripciondedireccion) {
        this.nombre = nombre;
        this.codigopostal = codigopostal;
        this.estado = estado;
        this.municipio = municipio;
        this.colonia = colonia;
        this.calle = calle;
        this.numerointerior = numerointerior;
        this.numeroexterior = numeroexterior;
        this.calle1 = calle1;
        this.calle2 = calle2;
        this.tipodevivienda = tipodevivienda;
        this.numerodetelefono = numerodetelefono;
        this.descripciondedireccion = descripciondedireccion;
    }

}
