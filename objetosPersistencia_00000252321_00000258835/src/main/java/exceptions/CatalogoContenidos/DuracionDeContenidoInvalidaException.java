package exceptions.CatalogoContenidos;

/**
 * Excepcion que se lanza cuando la Duracion de un Objeto tipo Contenido no cumple con el rango determinado.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class DuracionDeContenidoInvalidaException extends Exception {
    public DuracionDeContenidoInvalidaException(String mensaje) {
        super(mensaje);
    }
}

