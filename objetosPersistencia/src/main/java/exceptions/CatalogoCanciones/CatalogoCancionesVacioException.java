package exceptions.CatalogoCanciones;

/**
 * Excepcion que se lanza al consultar el Catalogo de Canciones cuando este aun no guarda ningun Objeto.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class CatalogoCancionesVacioException extends Exception {
    public CatalogoCancionesVacioException(String mensaje) {
        super(mensaje);
    }
}

