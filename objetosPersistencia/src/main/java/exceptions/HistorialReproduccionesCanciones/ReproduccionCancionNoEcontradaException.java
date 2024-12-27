package exceptions.HistorialReproduccionesCanciones;

/**
 * Excepcion que se lanza cuando al buscar un Objeto tipo Reproduccion Cancion en el Historial de Reproducciones
 *  no se encuentra coincidencia.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class ReproduccionCancionNoEcontradaException extends Exception {
    public ReproduccionCancionNoEcontradaException(String mensaje) {
        super(mensaje);
    }
}

