package org.example.gestion;

import org.example.dao.AsociacionDaoImpl;
import org.example.models.Asociacion;

import java.time.LocalDate;

import static org.example.utils.EntradaDatos.preguntar;

//visualización y modificación de los datos de la asociación (razón social, CIF,
//dirección, información de contacto, ...)
public class GestionAsociacion {
    private final AsociacionDaoImpl asociacionDao;

    public GestionAsociacion(AsociacionDaoImpl asociacionDao) {
        this.asociacionDao = asociacionDao;

        final String MENU = "1) Ver datos"
                + "\n2) Modificar datos"
                + "\n3) Volver al menú principal";

        int accion = -1;
        while (accion != 3) {
            try {
                accion = Integer.parseInt(preguntar("Elija una opción:\n" + MENU + "\n\nAcción: "));
            } catch (NumberFormatException e) {
                accion = -1;
            }

            switch (accion) {
                case 1:
                    ver();
                    break;
                case 2:
                    modificar();
                    break;
                case 3:
                    // condición de fin de bucle
                    break;
                default:
                    System.out.println("Acción no válida");
            }
            System.out.println();
        }
    }

    private void ver() {
        Asociacion asociacion = asociacionDao.getAsociacion();
        System.out.println("cif: " + asociacion.getCif());
        System.out.println("nombre: " + asociacion.getNombre());
        System.out.println("razonSocial: " + asociacion.getRazonSocial());
        System.out.println("direccion: " + asociacion.getDireccion());
        System.out.println("fechaApertura: " + asociacion.getFechaApertura());
    }

    private void modificar() {
        Asociacion asociacion = asociacionDao.getAsociacion();

        String cif = preguntar("cif: ");
        String nombre = preguntar("nombre: ");
        String razonSocial = preguntar("razonSocial: ");
        String direccion = preguntar("direccion: ");
        LocalDate fechaApertura;
        try {
            fechaApertura = LocalDate.parse(preguntar("fechaApertura: "));
        } catch (Exception e) {
            System.out.println("Fallo al leer los datos");
            return;
        }

        asociacion.setCif(cif);
        asociacion.setNombre(nombre);
        asociacion.setRazonSocial(razonSocial);
        asociacion.setDireccion(direccion);
        asociacion.setFechaApertura(fechaApertura);

        if (asociacionDao.update(asociacion)) {
            System.out.println("Acción terminada correctamente.");
        } else {
            System.out.println("Error al completar la acción.");
        }
    }
}
