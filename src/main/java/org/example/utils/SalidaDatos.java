package org.example.utils;

import java.util.List;

public class SalidaDatos {
    public static <T> void mostrarLista(List<T> lista) {
        System.out.println("Resultados:");
        if (lista.isEmpty()) {
            System.out.println("(no hay elementos)");
        } else {
            for (T el : lista) {
                System.out.println(el);
            }
        }
    }
}
