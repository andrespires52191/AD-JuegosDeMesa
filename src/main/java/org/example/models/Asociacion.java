package org.example.models;

// con los datos más relevantes de la asociación
// (por ejemplo nombre, fecha de apertura, dirección, CIF, ...)
public class Asociacion {
    String nombre;
    String fechaApertura;
    String direccion;
    String cif;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    @Override
    public String toString() {
        return "Asociación " + nombre;
    }
}
