package exceptions.HistorialReproduccionesCanciones;

/**
 * Excepcion que se lanza cuando el Objeto tipo Fecha que se recibe como parametro es nulo.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class FechaNullException extends Exception {
    public FechaNullException(String mensaje) {
        super(mensaje);
    }
}

