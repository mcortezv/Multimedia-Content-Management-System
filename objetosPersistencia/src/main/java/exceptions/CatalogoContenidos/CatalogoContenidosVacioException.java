package exceptions.CatalogoContenidos;

/**
 * Excepcion que se lanza al consultar el Catalogo de Contenidos cuando este aun no guarda ningun Objeto.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class CatalogoContenidosVacioException extends Exception {
    public CatalogoContenidosVacioException(String mensaje) {
        super(mensaje);
    }
}

