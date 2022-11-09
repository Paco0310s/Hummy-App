package com.vantuit.hummy.modelo;

public class Establecimiento {

    int imagen;
    String nombre;
    String descripcion;
    String estado;
    String hora_abierto, hora_cerrado, hora_abierto_sabado, hora_cerrado_sabado, hora_abierto_domingo, hora_cerrado_domingo;
    String calificacion;
    String foto;
    String id;
    String id_usuario;

    public Establecimiento(int santinos, String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8) {

    }

    public Establecimiento() {

    }

    public Establecimiento(int imagen, String nombre, String descripcion, String estado, String hora_abierto, String hora_cerrado, String hora_abierto_sabado, String hora_cerrado_sabado, String hora_abierto_domingo, String hora_cerrado_domingo, String calificacion, String foto, String id, String id_usuario) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.hora_abierto = hora_abierto;
        this.hora_cerrado = hora_cerrado;
        this.hora_abierto_sabado = hora_abierto_sabado;
        this.hora_cerrado_sabado = hora_cerrado_sabado;
        this.hora_abierto_domingo = hora_abierto_domingo;
        this.hora_cerrado_domingo = hora_cerrado_domingo;
        this.calificacion = calificacion;
        this.foto = foto;
        this.id = id;
        this.id_usuario = id_usuario;
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

    public String getHora_abierto() {
        return hora_abierto;
    }

    public void setHora_abierto(String hora_abierto) {
        this.hora_abierto = hora_abierto;
    }

    public String getHora_cerrado() {
        return hora_cerrado;
    }

    public void setHora_cerrado(String hora_cerrado) {
        this.hora_cerrado = hora_cerrado;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getHora_abierto_sabado() {
        return hora_abierto_sabado;
    }

    public void setHora_abierto_sabado(String hora_abierto_sabado) {
        this.hora_abierto_sabado = hora_abierto_sabado;
    }

    public String getHora_cerrado_sabado() {
        return hora_cerrado_sabado;
    }

    public void setHora_cerrado_sabado(String hora_cerrado_sabado) {
        this.hora_cerrado_sabado = hora_cerrado_sabado;
    }

    public String getHora_abierto_domingo() {
        return hora_abierto_domingo;
    }

    public void setHora_abierto_domingo(String hora_abierto_domingo) {
        this.hora_abierto_domingo = hora_abierto_domingo;
    }

    public String getHora_cerrado_domingo() {
        return hora_cerrado_domingo;
    }

    public void setHora_cerrado_domingo(String hora_cerrado_domingo) {
        this.hora_cerrado_domingo = hora_cerrado_domingo;
    }
}
