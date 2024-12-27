package exceptions.CatalogoCanciones;

/**
 * Excepcion que se lanza al intenta guardar un Objeto tipo Cancion que ya ha sido previamente
 * guardado en el Catalogo de Canciones.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class CancionExistenteException extends Exception {
    public CancionExistenteException(String mensaje) {
        super(mensaje);
    }
}

