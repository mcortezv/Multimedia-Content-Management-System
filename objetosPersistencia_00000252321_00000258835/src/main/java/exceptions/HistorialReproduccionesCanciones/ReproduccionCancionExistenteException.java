package exceptions.HistorialReproduccionesCanciones;

/**
 * Excepcion que se lanza al intenta guardar un Objeto tipo Reproduccion Cancion que ya ha sido previamente
 *  guardado en el Historial de Reproducciones.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class ReproduccionCancionExistenteException extends Exception {
    public ReproduccionCancionExistenteException(String mensaje) {
        super(mensaje);
    }
}

