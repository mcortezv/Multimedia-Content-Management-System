package exceptions.CatalogoContenidos;

/**
 * Excepcion que se lanza cuando el Año de Creacion de un Objeto tipo Contenido no cumple con los requisitos
 * del sistema.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class AnioCreacionDeContenidoInvalidoException extends Exception {
    public AnioCreacionDeContenidoInvalidoException(String mensaje) {
        super(mensaje);
    }
}

