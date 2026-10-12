package org.example.models;

//Listado de juegos de mesa disponibles en la asociación, mostrando datos como el
//número identificador del juego, el nombre, el autor o autores, el número mínimo y
//máximo de jugadores, duración estimada, su temática, dónde se encuentra, estado
//(disponible, prestado, en reparación), a quién pertenece (un juego puede pertenece a
//la propia asociación a una de las socias) y su fecha de alta. Posibilidad de exportar
//dichos datos como fichero XML y JSON.
public class Juego {
    int id;
    String nombre;
    String autores;
    int minJugadores;
    int maxJugadores;
    double estimacionMinutos;
    String tematica;
    String ubicacion;
    JuegoEstado estado;
    Socia propietaria;
    String fechaAlta;

    public Juego(String nombre, String autores, int minJugadores, int maxJugadores, double estimacionMinutos, String tematica, String ubicacion, JuegoEstado estado, Socia propietaria, String fechaAlta) {
        this.nombre = nombre;
        this.autores = autores;
        this.minJugadores = minJugadores;
        this.maxJugadores = maxJugadores;
        this.estimacionMinutos = estimacionMinutos;
        this.tematica = tematica;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.propietaria = propietaria;
        this.fechaAlta = fechaAlta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public int getMinJugadores() {
        return minJugadores;
    }

    public void setMinJugadores(int minJugadores) {
        this.minJugadores = minJugadores;
    }

    public int getMaxJugadores() {
        return maxJugadores;
    }

    public void setMaxJugadores(int maxJugadores) {
        this.maxJugadores = maxJugadores;
    }

    public double getEstimacionMinutos() {
        return estimacionMinutos;
    }

    public void setEstimacionMinutos(double estimacionMinutos) {
        this.estimacionMinutos = estimacionMinutos;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public JuegoEstado getEstado() {
        return estado;
    }

    public void setEstado(JuegoEstado estado) {
        this.estado = estado;
    }

    public Socia getPropietaria() {
        return propietaria;
    }

    public void setPropietaria(Socia propietaria) {
        this.propietaria = propietaria;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
