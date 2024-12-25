package exceptions.CatalogoContenidos;

/**
 * Excepcion que se lanza al intenta guardar un Objeto tipo Contenido que ya ha sido previamente
 * guardado en el Catalogo de Contenidos.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class ContenidoExistenteException extends Exception {
    public ContenidoExistenteException(String mensaje) {
        super(mensaje);
    }
}

