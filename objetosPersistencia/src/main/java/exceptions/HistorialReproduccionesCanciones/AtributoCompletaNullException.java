package exceptions.HistorialReproduccionesCanciones;

/**
 * Excepcion que se lanza cuando el Atributo de Cancion Completa que se recibe como parametro es nulo.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class AtributoCompletaNullException extends Exception {
    public AtributoCompletaNullException(String mensaje) {
        super(mensaje);
    }
}

