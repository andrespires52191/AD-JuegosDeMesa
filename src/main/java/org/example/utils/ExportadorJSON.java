package org.example.utils;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportadorJSON {
    static Gson gson = new Gson();

    public static <T> boolean exportar(String filename, List<T> elementos) {
        // convertir lista a json
        String json = gson.toJson(elementos);
        try {
            File carpeta = new File("datos_exportados");
            carpeta.mkdirs();
            if (!carpeta.exists()) {
                System.out.println("Error: fallo al crear carpeta de salida");
                return false;
            }

            // abrir archivo
            FileOutputStream fos = new FileOutputStream(new File(carpeta, filename));

            // escribir json
            fos.write(json.getBytes());

            // cerrar archivo
            fos.close();

            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }
}
