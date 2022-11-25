package com.vantuit.hummy.modelo;

public class Pedido {
    private float monto;
    private int cantidad;
    private Producto producto;
    private Usuario cliente;
    private Establecimiento establecimiento;
    private String fecha;
    private String estado;

    public Pedido(float monto, int cantidad, Producto producto, Usuario cliente, Establecimiento establecimiento, String fecha, String estado) {
        this.monto = monto;
        this.cantidad = cantidad;
        this.producto = producto;
        this.cliente = cliente;
        this.establecimiento = establecimiento;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Pedido() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
