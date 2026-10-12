package org.example.utils;

import com.thoughtworks.xstream.XStream;
import org.example.models.Listado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportadorXML {
    static XStream xstream = new XStream();

    static public <T> void exportar(String filename, List<T> elementos, Class<T> claseT) {
        try {
            // cambiar de nombre a las etiquetas XML
            xstream.alias("lista", Listado.class);
            xstream.alias("objeto", claseT);

            // colección por defecto
            xstream.addImplicitCollection(Listado.class, "lista");

            // guardar
            xstream.toXML(elementos, new FileOutputStream(filename));

            System.out.println("Exportado con éxito.");
            return;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Fallo al exportar.");
    }
}
