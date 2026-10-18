package org.example.models;

//Listado de socias que forman parte de la asociación. De cada socia se debe registrar
//el DNI/NIE/Pasaporte, el nombre, los apellidos, un teléfono de contacto, la edad y su
//email. Además, por cada socia se debe poder consultar sus juegos y sus partidas.
//Posibilidad de exportar dichos datos como fichero XML y JSON.
public class Socia {
    int id;
    String dni;
    String nombre;
    String apellidos;
    String fechaNac;
    String telefono;
    String email;

    public Socia(String dni, String nombre, String apellidos, String fechaNac, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "#" + id + " - " + nombre + " " + apellidos;
    }
}
