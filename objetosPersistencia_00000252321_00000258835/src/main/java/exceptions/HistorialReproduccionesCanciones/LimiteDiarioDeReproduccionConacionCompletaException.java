package exceptions.HistorialReproduccionesCanciones;

/**
 * Excepcion que se lanza cuando el limite diario de Reproducciones Canciones Completas guardadas ha sido
 * superado. No se pueden realizar más de 5 reproducciones completas de una misma cancion por día.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class LimiteDiarioDeReproduccionConacionCompletaException extends Exception {
    public LimiteDiarioDeReproduccionConacionCompletaException(String mensaje) {
        super(mensaje);
    }
}

