package exceptions.CatalogoCanciones;

/**
 * Excepcion que se lanza cuando el Autor de un Objeto tipo Cancion no cumple con los requisitos del sistema.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class NombreAutorInvalidoException extends Exception {
    public NombreAutorInvalidoException(String mensaje) {
        super(mensaje);
    }
}

