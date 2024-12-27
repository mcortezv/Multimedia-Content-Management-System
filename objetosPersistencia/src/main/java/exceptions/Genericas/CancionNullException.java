package exceptions.Genericas;

/**
 * Excepcion que se lanza cuando el Objeto tipo Cancion que se recibe como parametro es nulo.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class CancionNullException extends Exception {
    public CancionNullException(String mensaje) {
        super(mensaje);
    }
}

