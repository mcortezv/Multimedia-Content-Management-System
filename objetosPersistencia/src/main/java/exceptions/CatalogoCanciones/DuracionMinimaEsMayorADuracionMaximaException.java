package exceptions.CatalogoCanciones;

/**
 * Excepcion que se lanza cuando la Duracion Minima recibida como parametro es Mayor a la Duracion  Maxima.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class DuracionMinimaEsMayorADuracionMaximaException extends Exception {
    public DuracionMinimaEsMayorADuracionMaximaException(String mensaje) {
        super(mensaje);
    }
}

