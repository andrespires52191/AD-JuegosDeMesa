package org.example.models;

// con los datos más relevantes de la asociación
// (por ejemplo nombre, fecha de apertura, dirección, CIF, ...)
//Asociación (visualización y modificación de los datos de la asociación (razón social, CIF,
//dirección, información de contacto, ...)).
public class Asociacion {
    String cif;
    String nombre;
    String razonSocial;
    String direccion;
    String fechaApertura;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    @Override
    public String toString() {
        return "Asociación " + nombre;
    }
}
