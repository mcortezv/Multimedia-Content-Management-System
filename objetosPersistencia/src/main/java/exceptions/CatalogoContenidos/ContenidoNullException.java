package exceptions.CatalogoContenidos;

/**
 * Excepcion que se lanza cuando el Objeto tipo Contenido que se recibe como parametro es nulo.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class ContenidoNullException extends Exception {
    public ContenidoNullException(String mensaje) {
        super(mensaje);
    }
}

