package exceptions.CatalogoCanciones;

/**
 * Excepcion que se lanza cuando al buscar un Objeto tipo Cancion en el Catalogo de Canciones
 * no se encuentra coincidencia.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class CancionNoEncontradaException extends Exception {
    public CancionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}

