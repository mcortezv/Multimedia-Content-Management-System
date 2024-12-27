package exceptions.CatalogoCanciones;

/**
 * Excepcion que se lanza cuando la Duracion Minima recibida como parametro es Igual a la Duracion  Maxima.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class DuracionMinimaEsIgualADuracionMaximaException extends Exception {
    public DuracionMinimaEsIgualADuracionMaximaException(String mensaje) {
        super(mensaje);
    }
}

