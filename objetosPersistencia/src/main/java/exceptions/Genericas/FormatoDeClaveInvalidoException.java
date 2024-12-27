package exceptions.Genericas;

/**
 * Excepcion que se lanza cuando la Clave que se recibe como parametro no cumple con los requisitos del sistema.
 * La clave del contenido se debe componer de tres caracteres y tres dígitos.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class FormatoDeClaveInvalidoException extends Exception {
    public FormatoDeClaveInvalidoException(String mensaje) {
        super(mensaje);
    }
}

