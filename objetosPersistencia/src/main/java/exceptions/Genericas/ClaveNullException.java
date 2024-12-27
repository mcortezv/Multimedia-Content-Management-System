package exceptions.Genericas;

/**
 * Excepcion que se lanza cuando la Clave que se recibe como parametro es nula.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class ClaveNullException extends Exception {
    public ClaveNullException(String mensaje) {
        super(mensaje);
    }
}

