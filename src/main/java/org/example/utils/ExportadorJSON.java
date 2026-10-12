package org.example.utils;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportadorJSON {
    static Gson gson = new Gson();

    public static <T> void exportar(String filename, List<T> elementos) {
        // convertir lista a json
        String json = gson.toJson(elementos);
        try {
            // abrir archivo
            FileOutputStream fos = new FileOutputStream(filename);

            // escribir json
            fos.write(json.getBytes());

            // cerrar archivo
            fos.close();

            System.out.println("Exportado con éxito.");
            return;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Fallo al exportar.");
    }
}
