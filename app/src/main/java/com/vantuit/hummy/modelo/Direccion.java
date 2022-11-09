package com.vantuit.hummy.modelo;

import java.io.Serializable;
import java.util.List;

public class Direccion  implements Serializable {//Serializable sirve para facilitar el paso entre los objetos entre las actividades que creemos

    String calle, codigoPostal, colonia, entreCalle1, entreCalle2, estado, municipio, nombreEncargado, numeroExterior, numeroInterior, numeroTelefono, referencia, tipoDomicilio;

    public Direccion() {

    }

    public Direccion(String calle, String codigoPostal, String colonia, String entreCalle1, String entreCalle2, String estado, String municipio, String nombreEncargado, String numeroExterior, String numeroInterior, String numeroTelefono, String referencia, String tipoDomicilio) {

        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.entreCalle1 = entreCalle1;
        this.entreCalle2 = entreCalle2;
        this.estado = estado;
        this.municipio = municipio;
        this.nombreEncargado = nombreEncargado;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.numeroTelefono = numeroTelefono;
        this.referencia = referencia;
        this.tipoDomicilio = tipoDomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public String getEntreCalle1() {
        return entreCalle1;
    }

    public String getEntreCalle2() {
        return entreCalle2;
    }

    public String getEstado() {
        return estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public String getNumeroInterior() {
        return numeroInterior;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getTipoDomicilio() {
        return tipoDomicilio;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setEntreCalle1(String entreCalle1) {
        this.entreCalle1 = entreCalle1;
    }

    public void setEntreCalle2(String entreCalle2) {
        this.entreCalle2 = entreCalle2;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public void setNumeroInterior(String numeroInterior) {
        this.numeroInterior = numeroInterior;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public void setTipoDomicilio(String tipoDomicilio) {
        this.tipoDomicilio = tipoDomicilio;
    }
}
