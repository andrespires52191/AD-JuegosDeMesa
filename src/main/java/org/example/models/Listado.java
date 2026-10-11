package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Listado<T> {
    List<T> lista = new ArrayList<>();

    public Listado(List<T> lista) {
        this.lista = lista;
    }

    public List<T> getLista() {
        return lista;
    }
}
