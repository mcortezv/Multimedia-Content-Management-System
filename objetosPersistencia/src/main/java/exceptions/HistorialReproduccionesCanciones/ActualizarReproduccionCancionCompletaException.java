package exceptions.HistorialReproduccionesCanciones;

/**
 * Excepcion que se lanza cuando el Objeto tipo Reproduccion Cancion que se intenta actualizar ya ha sido completada.
 * Unicamente pueden actualizarse reproducciones incompletas.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class ActualizarReproduccionCancionCompletaException extends Exception {
    public ActualizarReproduccionCancionCompletaException(String mensaje) {
        super(mensaje);
    }
}

