package exceptions.HistorialReproduccionesCanciones;

/**
 * Excepcion que se lanza cuando el Objeto tipo Fecha que se recibe como parametro no cumple con los requisitos del sistema.
 * El Objeto tipo Fecha que se recibe debe estar dentro del mes actual pero no despues de la Fecha actual.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class FechaInvalidaException extends Exception {
    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }
}

