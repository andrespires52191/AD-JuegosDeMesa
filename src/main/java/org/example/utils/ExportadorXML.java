package org.example.utils;

import com.thoughtworks.xstream.XStream;
import org.example.models.Listado;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportadorXML {
    static XStream xstream = new XStream();

    static public <T> boolean exportar(String filename, List<T> elementos, Class<T> claseT) {
        try {
            // cambiar de nombre a las etiquetas XML
            xstream.alias("lista", Listado.class);
            xstream.alias("objeto", claseT);

            // colección por defecto
            xstream.addImplicitCollection(Listado.class, "lista");

            // asegurarse de que existe carpeta de salida
            File carpeta = new File("datos_exportados");
            carpeta.mkdirs();
            if (!carpeta.exists()) {
                System.out.println("Error: fallo al crear carpeta de salida");
                return false;
            }

            // guardar
            FileOutputStream fos = new FileOutputStream(new File(carpeta, filename));
            xstream.toXML(elementos, fos);
            fos.close();

            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }
}
