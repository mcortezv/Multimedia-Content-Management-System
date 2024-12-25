package exceptions.HistorialReproduccionesCanciones;

/**
 * Excepcion que se lanza al consultar el Historial de Reproducciones cuando este aun no guarda ningun Objeto.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class HistorialReproduccionesCancionesVacioException extends Exception {
    public HistorialReproduccionesCancionesVacioException(String mensaje) {
        super(mensaje);
    }
}

