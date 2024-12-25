package exceptions.CatalogoContenidos;

/**
 * Excepcion que se lanza cuando al buscar un Objeto tipo Contenido en el Catalogo de Contenidos
 * no se encuentra coincidencia.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class ContenidoNoEncontradoException extends Exception {
    public ContenidoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

